package com.cred.interview.scheduler;

import com.cred.interview.node.Node;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.MasterTaskStatus;
import org.apache.commons.lang3.NotImplementedException;

import java.util.List;

public class InMemoryScheduler implements Scheduler {

    private Registry registry;

    public InMemoryScheduler(final Registry registry) {
        this.registry = registry;
    }

    @Override
    public MasterTaskStatus schedule(AbstractMasterTask masterTask, String cron) {
        throw new NotImplementedException("Not implemented as an example");
    }

    @Override
    public MasterTaskStatus scheduleImmediately(AbstractMasterTask masterTask) {
        Node master = registry.getNextAvailableMaster();
        return master.executeMasterTask(masterTask, this.registry);
    }
}
