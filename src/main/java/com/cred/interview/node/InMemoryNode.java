package com.cred.interview.node;

import com.cred.interview.node.exception.MasterTaskExecutionException;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.exception.TaskSubmissionException;

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
