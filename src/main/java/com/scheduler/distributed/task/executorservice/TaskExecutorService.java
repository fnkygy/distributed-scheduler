package com.scheduler.distributed.task.executorservice;

import com.scheduler.distributed.task.AbstractChildTask;
import com.scheduler.distributed.task.ChildTaskStatus;
import com.scheduler.distributed.task.exception.TaskExecutionException;

import java.util.Map;
import java.util.Set;

public interface TaskExecutorService {

    Map<AbstractChildTask, ChildTaskStatus> executeChildTasks(Set<AbstractChildTask> childTasks)
            throws TaskExecutionException;
}
