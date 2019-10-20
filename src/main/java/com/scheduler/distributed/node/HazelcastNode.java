package com.scheduler.distributed.node;

import com.scheduler.distributed.node.exception.MasterTaskExecutionException;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;
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
