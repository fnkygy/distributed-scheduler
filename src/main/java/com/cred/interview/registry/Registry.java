package com.cred.interview.registry;

import com.cred.interview.node.Node;
import com.cred.interview.registry.exception.ChildTaskExecutionException;
import com.cred.interview.registry.exception.ExecutorUnavailableException;
import com.cred.interview.registry.exception.MasterUnavailableException;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;

import java.util.List;

public interface Registry {

    Node getNextAvailableMaster() throws MasterUnavailableException;

    List<Node> getAvailableExecutors() throws ExecutorUnavailableException;

    ChildTaskStatus executeTaskOnNode(Node node, AbstractChildTask task) throws ChildTaskExecutionException;
}
