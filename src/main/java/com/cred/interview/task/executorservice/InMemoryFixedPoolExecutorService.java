package com.cred.interview.task.executorservice;

import com.cred.interview.task.AbstractChildTask;
import com.cred.interview.task.ChildTaskStatus;
import com.cred.interview.task.dao.TaskDao;
import com.cred.interview.task.exception.TaskExecutionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class InMemoryFixedPoolExecutorService implements TaskExecutorService {

    private TaskDao taskDao;

    public InMemoryFixedPoolExecutorService(final TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Map<AbstractChildTask, ChildTaskStatus> executeChildTasks(Set<AbstractChildTask> childTasks) throws TaskExecutionException {
        Map<AbstractChildTask, ChildTaskStatus> statusMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(childTasks.size());
        for (AbstractChildTask childTask : childTasks) {
            ChildTaskStatus status = this.taskDao.saveChildTask(childTask);
            Future<ChildTaskStatus> future = executorService.submit(
                    () -> childTask.getChildNode().executeChildTask(childTask)
            );
            try {
                status = future.get();
            } catch (InterruptedException | ExecutionException e) {
                status = ChildTaskStatus.FAILED;
            }
            this.taskDao.updateChildTaskStatus(childTask, status);
            statusMap.put(childTask, status);
        }
        return statusMap;
    }
}
