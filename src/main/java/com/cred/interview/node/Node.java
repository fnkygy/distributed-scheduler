package com.cred.interview.node;

import com.cred.interview.node.exception.ChildTaskExecutionException;
import com.cred.interview.node.exception.MasterTaskExecutionException;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;

public interface Node {

    MasterTaskStatus executeMasterTask(final AbstractMasterTask masterTask,
                                       final Registry registry) throws MasterTaskExecutionException;

    ChildTaskStatus executeChildTask(final AbstractChildTask childTask) throws ChildTaskExecutionException;
}
