package com.cred.interview.task.dao;

import com.cred.interview.node.Node;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.dao.exception.DuplicateTaskException;
import com.cred.interview.task.dao.exception.TaskSaveException;
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
