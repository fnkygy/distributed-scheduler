package com.scheduler.distributed.task.dao;

import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;
import com.scheduler.distributed.task.dao.exception.DuplicateTaskException;
import com.scheduler.distributed.task.dao.exception.TaskSaveException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DummyTaskDao implements TaskDao {

    @Override
    public MasterTaskStatus saveMasterTask(AbstractMasterTask masterTask)
            throws TaskSaveException, DuplicateTaskException {
        return MasterTaskStatus.SUBMITTED;
    }

    @Override
    public MasterTaskStatus updateMasterTaskStatus(AbstractMasterTask masterTask, MasterTaskStatus status)
            throws TaskSaveException {
        return status;
    }

    @Override
    public ChildTaskStatus saveChildTask(AbstractChildTask childTask) throws TaskSaveException {
        return ChildTaskStatus.NOT_STARTED;
    }

    @Override
    public ChildTaskStatus updateChildTaskStatus(AbstractChildTask childTask, ChildTaskStatus status)
            throws TaskSaveException {
        return status;
    }
}
