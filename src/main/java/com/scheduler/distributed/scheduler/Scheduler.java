package com.scheduler.distributed.scheduler;

import com.scheduler.distributed.scheduler.exception.ScheduleException;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.MasterTaskStatus;

public interface Scheduler {

    MasterTaskStatus schedule(AbstractMasterTask masterTask, String cron) throws ScheduleException;

    MasterTaskStatus scheduleImmediately(AbstractMasterTask masterTask) throws ScheduleException;
}
