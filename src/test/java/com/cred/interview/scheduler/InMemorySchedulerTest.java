package com.cred.interview.scheduler;

import com.cred.interview.node.InMemoryNode;
import com.cred.interview.node.Node;
import com.cred.interview.node.elector.RandomMasterElector;
import com.cred.interview.registry.InMemoryRegistry;
import com.cred.interview.registry.Registry;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.lib.integeriterate.IntegerIterateMasterTask;
import com.cred.interview.task.lib.integeriteratefailure.IntegerIterateFailureMasterTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InMemorySchedulerTest {

    private static List<Node> createNodes(final Integer numNodes) {
        List<Node> nodes = new ArrayList<>();
        for (Integer iter = 0; iter < numNodes; iter++) {
            nodes.add(new InMemoryNode(iter));
        }
        return nodes;
    }

    @Test
    public void testSuccessfulIteration() {
        System.out.println("Testing scheduler with 20 numbers and 6 nodes");
        List<Node> nodes = createNodes(6);
        Registry registry = new InMemoryRegistry(nodes, new RandomMasterElector(nodes));
        Scheduler scheduler = new InMemoryScheduler(registry);
        IntegerIterateMasterTask masterTask = new IntegerIterateMasterTask(1, 20);
        MasterTaskStatus status = scheduler.scheduleImmediately(masterTask);
        System.out.println("\nMaster task status is: " + status.toString());
        assertEquals(status, MasterTaskStatus.COMPLETED);
    }

    @Test
    public void testFailureIteration() {
        System.out.println("Testing scheduler with 20 numbers and 6 nodes");
        List<Node> nodes = createNodes(6);
        Registry registry = new InMemoryRegistry(nodes, new RandomMasterElector(nodes));
        Scheduler scheduler = new InMemoryScheduler(registry);
        IntegerIterateFailureMasterTask masterTask = new IntegerIterateFailureMasterTask(1, 20);
        MasterTaskStatus status = scheduler.scheduleImmediately(masterTask);
        System.out.println("\nMaster task status is: " + status.toString());
        assertEquals(status, MasterTaskStatus.FAILED);
    }
}