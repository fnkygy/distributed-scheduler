package com.cred.interview.task.dao;

import com.cred.interview.node.Node;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.dao.exception.DuplicateTaskException;
import com.cred.interview.task.dao.exception.TaskSaveException;

public interface TaskDao {

    MasterTaskStatus saveMasterTask(AbstractMasterTask masterTask) throws TaskSaveException, DuplicateTaskException;

    MasterTaskStatus updateMasterTaskStatus(AbstractMasterTask masterTask, MasterTaskStatus status)
        throws TaskSaveException;

    ChildTaskStatus saveChildTask(AbstractChildTask childTask) throws TaskSaveException;

    ChildTaskStatus updateChildTaskStatus(AbstractChildTask childTask, ChildTaskStatus status)
            throws TaskSaveException;
}
