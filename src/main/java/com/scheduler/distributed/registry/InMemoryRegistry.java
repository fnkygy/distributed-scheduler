package com.scheduler.distributed.registry;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.node.elector.MasterElector;
import com.scheduler.distributed.registry.exception.ChildTaskExecutionException;
import com.scheduler.distributed.registry.exception.ExecutorUnavailableException;
import com.scheduler.distributed.registry.exception.MasterUnavailableException;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.ChildTaskStatus;

import java.util.List;

public class InMemoryRegistry implements Registry {

    private List<Node> activeNodes;
    private MasterElector masterElector;

    public InMemoryRegistry(final List<Node> nodes,
                            final MasterElector masterElector) {
        this.activeNodes = nodes;
        this.masterElector = masterElector;
    }

    @Override
    public Node getNextAvailableMaster() throws MasterUnavailableException {
        return masterElector.getMaster();
    }

    @Override
    public List<Node> getAvailableExecutors() throws ExecutorUnavailableException {
        return this.activeNodes;
    }

    @Override
    public ChildTaskStatus executeTaskOnNode(final Node node, final AbstractChildTask task)
            throws ChildTaskExecutionException {
        return node.executeChildTask(task);
    }
}
