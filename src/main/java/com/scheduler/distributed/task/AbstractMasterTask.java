package com.scheduler.distributed.task;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.task.dao.TaskDao;
import com.scheduler.distributed.task.dao.exception.DuplicateTaskException;
import com.scheduler.distributed.task.dao.exception.TaskSaveException;
import com.scheduler.distributed.task.exception.TaskDistributeException;
import com.scheduler.distributed.task.exception.TaskExecutionException;
import com.scheduler.distributed.task.exception.TaskSubmissionException;
import com.scheduler.distributed.task.executorservice.TaskExecutorService;
import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AbstractMasterTask extends Task {
    private Long taskId;
    private Map<AbstractChildTask, ChildTaskStatus> childTasks;
    private MasterTaskStatus masterTaskStatus;
    private TaskDao taskDao;
    private TaskExecutorService taskExecutorService;

    public AbstractMasterTask(final OperationType operationType,
                              final TaskDao taskDao,
                              final TaskExecutorService taskExecutorService) {
        super(operationType);
        this.taskDao = taskDao;
        this.taskExecutorService = taskExecutorService;
        this.childTasks = new HashMap<>();
    }

    public abstract List<AbstractChildTask> distribute(final List<Node> nodes) throws TaskDistributeException;

    public void doDistribute(final List<Node> nodes) throws TaskDistributeException {
        List<AbstractChildTask> childTasks = this.distribute(nodes);
        for (AbstractChildTask childTask : childTasks) {
            this.childTasks.put(childTask, ChildTaskStatus.NOT_STARTED);
        }
    }

    protected void notifyCheckpoint(AbstractChildTask childTask) {
        throw new NotImplementedException("Not implemented in the demo");
    }

    public abstract void rebalance(List<Node> nodes);

    public MasterTaskStatus submit(final Registry registry) throws TaskSubmissionException {

        try {
            /* Try saving the master task in the database, this should ensure idempotency of a master task */
            this.masterTaskStatus = this.taskDao.saveMasterTask(this);
        } catch (DuplicateTaskException | TaskSaveException e) {
            /* Task has already been submitted! */
            /* TODO: Log this */
            throw e;
        }

        try {
            /* First step - distribute the tasks among the nodes */
            this.doDistribute(registry.getAvailableExecutors());
            this.masterTaskStatus = this.taskDao.updateMasterTaskStatus(this, MasterTaskStatus.IN_PROGRESS);
        } catch (TaskDistributeException | TaskSaveException e) {
            /* TODO: Log this */
            throw e;
        }

        /* Second step - execute the tasks in parallel */
        Map<AbstractChildTask, ChildTaskStatus> statusMap;
        try {
            statusMap = this.taskExecutorService.executeChildTasks(this.childTasks.keySet());
        } catch (TaskExecutionException e) {
            /* TODO: Log this */
            throw e;
        }

        /* Third step - collate the statuses, save it, and return the appropriate status to caller */
        MasterTaskStatus masterTaskStatus = MasterTaskStatus.COMPLETED;
        Iterator statusMapIterator = statusMap.entrySet().iterator();
        while (statusMapIterator.hasNext()) {
            Map.Entry element = (Map.Entry)statusMapIterator.next();
            if (((ChildTaskStatus)element.getValue()).equals(ChildTaskStatus.FAILED)) {
                masterTaskStatus = MasterTaskStatus.FAILED;
                break;
            } else if (
                    masterTaskStatus == MasterTaskStatus.COMPLETED &&
                            element.getValue().equals(ChildTaskStatus.IN_PROGRESS)) {
                masterTaskStatus = MasterTaskStatus.IN_PROGRESS;
            }
        }
        try {
            return this.taskDao.updateMasterTaskStatus(this, masterTaskStatus);
        } catch (TaskSaveException e) {
            /* TODO: Log this */
            throw e;
        }
    }
}
