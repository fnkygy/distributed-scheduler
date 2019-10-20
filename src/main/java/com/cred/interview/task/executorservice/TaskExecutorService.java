package com.cred.interview.task.executorservice;

import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.exception.TaskExecutionException;

import java.util.Map;
import java.util.Set;

public interface TaskExecutorService {

    Map<AbstractChildTask, ChildTaskStatus> executeChildTasks(Set<AbstractChildTask> childTasks)
            throws TaskExecutionException;
}
