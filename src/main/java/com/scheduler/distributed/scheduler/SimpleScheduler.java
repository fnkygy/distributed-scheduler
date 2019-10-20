package com.scheduler.distributed.scheduler;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.node.exception.MasterTaskExecutionException;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.scheduler.exception.ScheduleException;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.MasterTaskStatus;
import org.apache.commons.lang3.NotImplementedException;

public class SimpleScheduler implements Scheduler {

    private Registry registry;

    public SimpleScheduler(final Registry registry) {
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