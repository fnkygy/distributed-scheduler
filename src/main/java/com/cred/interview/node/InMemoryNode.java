package com.cred.interview.node;

import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;

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
                                              final Registry registry) {
        return masterTask.submit(registry);
    }

    @Override
    public ChildTaskStatus executeChildTask(final AbstractChildTask childTask) {
        return childTask.execute();
    }
}
