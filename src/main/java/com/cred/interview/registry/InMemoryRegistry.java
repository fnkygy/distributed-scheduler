package com.cred.interview.registry;

import com.cred.interview.node.Node;
import com.cred.interview.node.elector.MasterElector;
import com.cred.interview.registry.exception.ChildTaskExecutionException;
import com.cred.interview.registry.exception.ExecutorUnavailableException;
import com.cred.interview.registry.exception.MasterUnavailableException;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;

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
