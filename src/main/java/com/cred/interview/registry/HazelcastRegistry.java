package com.cred.interview.registry;

import com.cred.interview.node.Node;
import com.cred.interview.node.elector.MasterElector;
import com.cred.interview.registry.exception.ChildTaskExecutionException;
import com.cred.interview.registry.exception.ExecutorUnavailableException;
import com.cred.interview.registry.exception.MasterUnavailableException;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;
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
