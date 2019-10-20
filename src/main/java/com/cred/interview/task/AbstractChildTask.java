package com.cred.interview.task;

import com.cred.interview.node.Node;
import lombok.Getter;

public abstract class AbstractChildTask extends Task {

    private AbstractMasterTask masterTask;
    private int childTaskId;

    @Getter
    private Node childNode;

    @Override
    public int hashCode() {
        return this.childTaskId;
    }

    public AbstractChildTask(final OperationType operationType,
                             final AbstractMasterTask masterTask,
                             Node childNode) {
        super(operationType);
        this.masterTask = masterTask;
        this.childNode = childNode;
    }

    public abstract ChildTaskStatus execute();

    public abstract ChildTaskStatus stop();
}
