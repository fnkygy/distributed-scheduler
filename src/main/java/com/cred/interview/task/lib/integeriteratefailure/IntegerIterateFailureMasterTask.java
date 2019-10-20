package com.cred.interview.task.lib.integeriteratefailure;

import com.cred.interview.node.Node;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.OperationType;
import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class IntegerIterateFailureMasterTask extends AbstractMasterTask {

    private OperationType operationType;
    private Integer start;
    private Integer end;

    public IntegerIterateFailureMasterTask(final Integer start,
                                           final Integer end) {
        super(OperationType.INTEGER_ITERATE);
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
