package com.scheduler.distributed.task.lib.integeriteratefailure;

import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.AbstractMasterTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.OperationType;
import lombok.Getter;

public class IntegerIterateFailureChildTask extends AbstractChildTask {

    @Getter
    private Integer start;

    @Getter
    private Integer end;

    public IntegerIterateFailureChildTask(final OperationType operationType,
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
        if (this.start == 15) return ChildTaskStatus.FAILED;
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
