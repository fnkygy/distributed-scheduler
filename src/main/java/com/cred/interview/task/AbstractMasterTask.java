package com.cred.interview.task;

import com.cred.interview.node.Node;
import com.cred.interview.registry.Registry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public abstract class AbstractMasterTask extends Task {
    private Long taskId;
    private Map<AbstractChildTask, ChildTaskStatus> childTasks;
    private MasterTaskStatus masterTaskStatus;

    public AbstractMasterTask(final OperationType operationType) {
        super(operationType);
        this.childTasks = new HashMap<>();
    }

    public abstract List<AbstractChildTask> distribute(final List<Node> nodes);

    public void doDistribute(final List<Node> nodes) {
        List<AbstractChildTask> childTasks = this.distribute(nodes);
        for (AbstractChildTask childTask : childTasks) {
            this.childTasks.put(childTask, ChildTaskStatus.NOT_STARTED);
        }
    }

    public abstract void rebalance(List<Node> nodes);

    public MasterTaskStatus submit(final Registry registry) {

        this.masterTaskStatus = MasterTaskStatus.SUBMITTED;

        /* First step - distribute the tasks among the nodes */
        this.doDistribute(registry.getAvailableExecutors());

        this.masterTaskStatus = MasterTaskStatus.IN_PROGRESS;

        /* Second step - execute the tasks in parallel */
        Integer numTasksSuccessful = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(this.childTasks.size());
        for (AbstractChildTask childTask : this.childTasks.keySet()) {
            Future<ChildTaskStatus> future = executorService.submit(
                    () -> registry.executeTaskOnNode(childTask.getChildNode(), childTask)
            );
            ChildTaskStatus status;
            try {
                status = future.get();
            } catch (InterruptedException | ExecutionException e) {
                status = ChildTaskStatus.FAILED;
            }
            this.childTasks.put(childTask, status);
            if (status == ChildTaskStatus.FAILED) this.masterTaskStatus = MasterTaskStatus.FAILED;
            if (status == ChildTaskStatus.COMPLETED) numTasksSuccessful++;
        }
        if (masterTaskStatus == MasterTaskStatus.IN_PROGRESS &&
                numTasksSuccessful == this.childTasks.size()) {
            this.masterTaskStatus = MasterTaskStatus.COMPLETED;
        }
        return this.masterTaskStatus;
    }
}
