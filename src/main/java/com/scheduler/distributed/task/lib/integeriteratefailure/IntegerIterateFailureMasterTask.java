package com.scheduler.distributed.task.lib.integeriteratefailure;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.OperationType;
import com.scheduler.distributed.task.dao.TaskDao;
import com.scheduler.distributed.task.executorservice.TaskExecutorService;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class IntegerIterateFailureMasterTask extends AbstractMasterTask {

    private OperationType operationType;
    private Integer start;
    private Integer end;

    public IntegerIterateFailureMasterTask(final Integer start,
                                           final Integer end,
                                           final TaskDao taskDao,
                                           final TaskExecutorService taskExecutorService) {
        super(OperationType.INTEGER_ITERATE, taskDao, taskExecutorService);
        this.operationType = OperationType.INTEGER_ITERATE;
        this.start = start;
        this.end = end;
    }

    @Override
    public void rebalance(List<Node> nodes) {
        throw new NotImplementedException("Not implemented");
    }

    @Override
    public List<AbstractChildTask> distribute(final List<Node> nodes) {
        List<AbstractChildTask> childTasks = new ArrayList<>();
        Integer numNodes = nodes.size();
        Integer numIntegersPerNode = (end-start)/numNodes;
        Integer remainingIntegers = (end-start)%numNodes;
        Integer nodeStart = this.start;
        for (Integer iter = 0; iter < numNodes; iter++) {
            Integer extra = (iter <= remainingIntegers) ? 1 : 0;
            Integer numIntegersForNode = numIntegersPerNode + extra;
            IntegerIterateFailureChildTask childTask = new IntegerIterateFailureChildTask(
                    this.operationType,
                    this,
                    nodes.get(iter),
                    nodeStart,
                    nodeStart + numIntegersForNode - 1
            );
            childTasks.add(childTask);
            nodeStart += numIntegersForNode;
        }
        return childTasks;
    }

}
