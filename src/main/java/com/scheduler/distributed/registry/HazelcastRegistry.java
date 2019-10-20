package com.scheduler.distributed.registry;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.registry.exception.ChildTaskExecutionException;
import com.scheduler.distributed.registry.exception.ExecutorUnavailableException;
import com.scheduler.distributed.registry.exception.MasterUnavailableException;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

@NoArgsConstructor
public class HazelcastRegistry implements Registry {

    @Override
    public Node getNextAvailableMaster() throws MasterUnavailableException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public List<Node> getAvailableExecutors() throws ExecutorUnavailableException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public ChildTaskStatus executeTaskOnNode(final Node node, final AbstractChildTask task)
            throws ChildTaskExecutionException {
        throw new NotImplementedException("Unimplemented in the demo");
    }
}
