package com.cred.interview.scheduler;

import com.cred.interview.registry.Registry;
import com.cred.interview.scheduler.exception.ScheduleException;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.MasterTaskStatus;

public interface Scheduler {

    MasterTaskStatus schedule(AbstractMasterTask masterTask, String cron) throws ScheduleException;

    MasterTaskStatus scheduleImmediately(AbstractMasterTask masterTask) throws ScheduleException;
}
