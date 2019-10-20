package com.scheduler.distributed.task;

public abstract class Task {
    private final OperationType operationType;

    public Task(final OperationType operationType) {
        this.operationType = operationType;
    }
}
