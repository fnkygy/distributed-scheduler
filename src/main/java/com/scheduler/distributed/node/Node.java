package com.scheduler.distributed.node;

import com.scheduler.distributed.node.exception.ChildTaskExecutionException;
import com.scheduler.distributed.node.exception.MasterTaskExecutionException;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;

public interface Node {

    MasterTaskStatus executeMasterTask(final AbstractMasterTask masterTask,
                                       final Registry registry) throws MasterTaskExecutionException;

    ChildTaskStatus executeChildTask(final AbstractChildTask childTask) throws ChildTaskExecutionException;
}
