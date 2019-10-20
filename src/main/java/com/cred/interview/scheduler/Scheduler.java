package com.cred.interview.scheduler;

import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.MasterTaskStatus;

public interface Scheduler {

    MasterTaskStatus schedule(AbstractMasterTask masterTask, String cron);

    MasterTaskStatus scheduleImmediately(AbstractMasterTask masterTask);
}
