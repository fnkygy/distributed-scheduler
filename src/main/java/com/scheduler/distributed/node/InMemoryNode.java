package com.scheduler.distributed.node;

import com.scheduler.distributed.node.exception.MasterTaskExecutionException;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;
import com.scheduler.distributed.task.exception.TaskSubmissionException;

public class InMemoryNode implements Node {

    private final Integer nodeId;

    public InMemoryNode(final Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int hashCode() {
        return this.nodeId;
    }

    @Override
    public MasterTaskStatus executeMasterTask(final AbstractMasterTask masterTask,
                                              final Registry registry) throws MasterTaskExecutionException {
        try {
            return masterTask.submit(registry);
        } catch (TaskSubmissionException e) {
            /* TODO: Log this */
            throw e;
        }
    }

    @Override
    public ChildTaskStatus executeChildTask(final AbstractChildTask childTask) throws MasterTaskExecutionException {
        return childTask.execute();
    }
}
