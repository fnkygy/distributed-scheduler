package com.cred.interview.scheduler;

import com.cred.interview.node.InMemoryNode;
import com.cred.interview.node.Node;
import com.cred.interview.node.elector.RandomMasterElector;
import com.cred.interview.registry.InMemoryRegistry;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.dao.DummyTaskDao;
import com.cred.interview.task.dao.TaskDao;
import com.cred.interview.task.executorservice.InMemoryFixedPoolExecutorService;
import com.cred.interview.task.executorservice.TaskExecutorService;
import com.cred.interview.task.lib.integeriterate.IntegerIterateMasterTask;
import com.cred.interview.task.lib.integeriteratefailure.IntegerIterateFailureMasterTask;
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