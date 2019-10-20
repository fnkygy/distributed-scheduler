package com.cred.interview.task.lib.integeriterate;

import com.cred.interview.node.Node;
import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.AbstractMasterTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.OperationType;
import lombok.Getter;

public class IntegerIterateChildTask extends AbstractChildTask {

    @Getter
    private Integer start;

    @Getter
    private Integer end;

    public IntegerIterateChildTask(final OperationType operationType,
                                   final AbstractMasterTask masterTask,
                                   Node childNode,
                                   final Integer start,
                                   final Integer end) {
        super(operationType, masterTask, childNode);
        this.start = start;
        this.end = end;
    }

    @Override
    public ChildTaskStatus execute() {
        StringBuilder printStr = new StringBuilder();
        for (int iter = start; iter <= end; iter++) {
            printStr.append(iter + " ");
        }
        System.out.print(printStr.toString());
        return ChildTaskStatus.COMPLETED;
    }

    @Override
    public ChildTaskStatus stop() {
        return ChildTaskStatus.INTERRUPTED;
    }
}
