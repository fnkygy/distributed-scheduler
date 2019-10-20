package com.scheduler.distributed.task.dao;

import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;
import com.scheduler.distributed.task.dao.exception.DuplicateTaskException;
import com.scheduler.distributed.task.dao.exception.TaskSaveException;

public interface TaskDao {

    MasterTaskStatus saveMasterTask(AbstractMasterTask masterTask) throws TaskSaveException, DuplicateTaskException;

    MasterTaskStatus updateMasterTaskStatus(AbstractMasterTask masterTask, MasterTaskStatus status)
        throws TaskSaveException;

    ChildTaskStatus saveChildTask(AbstractChildTask childTask) throws TaskSaveException;

    ChildTaskStatus updateChildTaskStatus(AbstractChildTask childTask, ChildTaskStatus status)
            throws TaskSaveException;
}
