package com.scheduler.distributed.registry;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.registry.exception.ChildTaskExecutionException;
import com.scheduler.distributed.registry.exception.ExecutorUnavailableException;
import com.scheduler.distributed.registry.exception.MasterUnavailableException;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.ChildTaskStatus;

import java.util.List;

public interface Registry {

    Node getNextAvailableMaster() throws MasterUnavailableException;

    List<Node> getAvailableExecutors() throws ExecutorUnavailableException;

    ChildTaskStatus executeTaskOnNode(Node node, AbstractChildTask task) throws ChildTaskExecutionException;
}
