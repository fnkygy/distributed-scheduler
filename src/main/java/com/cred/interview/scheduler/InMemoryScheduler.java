package com.cred.interview.scheduler;

import com.cred.interview.node.Node;
import com.cred.interview.node.exception.MasterTaskExecutionException;
import com.cred.interview.registry.Registry;
import com.cred.interview.scheduler.exception.ScheduleException;
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
    public MasterTaskStatus schedule(AbstractMasterTask masterTask, String cron) throws ScheduleException {
        throw new NotImplementedException("Not implemented as an example");
    }

    @Override
    public MasterTaskStatus scheduleImmediately(AbstractMasterTask masterTask) throws ScheduleException {
        try {
            Node master = this.registry.getNextAvailableMaster();
            return master.executeMasterTask(masterTask, this.registry);
        } catch (MasterTaskExecutionException e) {
            /* TODO: Log this */
            throw e;
        }
    }
}