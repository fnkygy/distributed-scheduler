package com.cred.interview.task.dao;

import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.dao.exception.DuplicateTaskException;
import com.cred.interview.task.dao.exception.TaskSaveException;
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
