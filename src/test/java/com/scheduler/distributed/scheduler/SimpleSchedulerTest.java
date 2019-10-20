package com.scheduler.distributed.scheduler;

import com.scheduler.distributed.node.InMemoryNode;
import com.scheduler.distributed.node.Node;
import com.scheduler.distributed.node.elector.RandomMasterElector;
import com.scheduler.distributed.registry.InMemoryRegistry;
import com.scheduler.distributed.registry.Registry;
import com.scheduler.distributed.task.MasterTaskStatus;
import com.scheduler.distributed.task.dao.DummyTaskDao;
import com.scheduler.distributed.task.dao.TaskDao;
import com.scheduler.distributed.task.executorservice.InMemoryFixedPoolExecutorService;
import com.scheduler.distributed.task.executorservice.TaskExecutorService;
import com.scheduler.distributed.task.lib.integeriterate.IntegerIterateMasterTask;
import com.scheduler.distributed.task.lib.integeriteratefailure.IntegerIterateFailureMasterTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SimpleSchedulerTest {

    private static List<Node> createNodes(final Integer numNodes) {
        List<Node> nodes = new ArrayList<>();
        for (Integer iter = 0; iter < numNodes; iter++) {
            nodes.add(new InMemoryNode(iter));
        }
        return nodes;
    }

    @Test
    public void testSuccessfulIteration() {
        System.out.println("Testing Successful scheduler with 20 numbers and 6 nodes");
        List<Node> nodes = createNodes(6);
        Registry registry = new InMemoryRegistry(nodes, new RandomMasterElector(nodes));
        Scheduler scheduler = new SimpleScheduler(registry);
        TaskDao taskDao = new DummyTaskDao();
        TaskExecutorService taskExecutorService = new InMemoryFixedPoolExecutorService(taskDao);

        /* Use the success task to test out the successful case */
        IntegerIterateMasterTask masterTask = new IntegerIterateMasterTask(1, 20, taskDao, taskExecutorService);

        MasterTaskStatus status = scheduler.scheduleImmediately(masterTask);
        System.out.println("\nMaster task status is: " + status.toString());
        assertEquals(status, MasterTaskStatus.COMPLETED);
    }

    @Test
    public void testFailureIteration() {
        System.out.println("Testing Failed scheduler with 20 numbers and 6 nodes");
        List<Node> nodes = createNodes(6);
        Registry registry = new InMemoryRegistry(nodes, new RandomMasterElector(nodes));
        Scheduler scheduler = new SimpleScheduler(registry);
        TaskDao taskDao = new DummyTaskDao();
        TaskExecutorService taskExecutorService = new InMemoryFixedPoolExecutorService(taskDao);

        /* Use the failure task to test out the failure case */
        IntegerIterateFailureMasterTask masterTask = new IntegerIterateFailureMasterTask(1, 20, taskDao, taskExecutorService);

        MasterTaskStatus status = scheduler.scheduleImmediately(masterTask);
        System.out.println("\nMaster task status is: " + status.toString());
        assertEquals(status, MasterTaskStatus.FAILED);
    }
}