package com.cred.interview.registry;

import com.cred.interview.node.Node;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;

import java.util.List;

public interface Registry {

    Node getNextAvailableMaster();

    List<Node> getAvailableExecutors();

    ChildTaskStatus executeTaskOnNode(Node node, AbstractChildTask task);
}
