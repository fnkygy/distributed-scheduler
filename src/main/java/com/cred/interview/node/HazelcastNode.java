package com.cred.interview.node;

import com.cred.interview.node.exception.MasterTaskExecutionException;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;
import org.apache.commons.lang3.NotImplementedException;

public class HazelcastNode implements Node {

    private final Integer nodeId;

    public HazelcastNode(final Integer nodeId) {
        this.nodeId = nodeId;
    }

    @Override
    public int hashCode() {
        return this.nodeId;
    }

    @Override
    public MasterTaskStatus executeMasterTask(final AbstractMasterTask masterTask,
                                              final Registry registry) throws MasterTaskExecutionException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public ChildTaskStatus executeChildTask(final AbstractChildTask childTask) throws MasterTaskExecutionException {
        throw new NotImplementedException("Unimplemented in the demo");
    }
}
