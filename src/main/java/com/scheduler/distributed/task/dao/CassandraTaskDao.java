package com.scheduler.distributed.task.dao;

import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.MasterTaskStatus;
import com.scheduler.distributed.task.dao.exception.DuplicateTaskException;
import com.scheduler.distributed.task.dao.exception.TaskSaveException;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

@NoArgsConstructor
public class CassandraTaskDao implements TaskDao {

    @Override
    public MasterTaskStatus saveMasterTask(AbstractMasterTask masterTask)
            throws TaskSaveException, DuplicateTaskException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public MasterTaskStatus updateMasterTaskStatus(AbstractMasterTask masterTask, MasterTaskStatus status)
            throws TaskSaveException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public ChildTaskStatus saveChildTask(AbstractChildTask childTask) throws TaskSaveException {
        throw new NotImplementedException("Unimplemented in the demo");
    }

    @Override
    public ChildTaskStatus updateChildTaskStatus(AbstractChildTask childTask, ChildTaskStatus status)
            throws TaskSaveException {
        throw new NotImplementedException("Unimplemented in the demo");
    }
}
