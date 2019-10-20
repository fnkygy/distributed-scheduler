package com.cred.interview.example;

import com.cred.interview.node.InMemoryNode;
import com.cred.interview.node.Node;
import com.cred.interview.node.elector.RandomMasterElector;
import com.cred.interview.registry.InMemoryRegistry;
import com.cred.interview.registry.Registry;
import com.cred.interview.scheduler.InMemoryScheduler;
import com.cred.interview.scheduler.Scheduler;
import com.cred.interview.task.MasterTaskStatus;
import com.cred.interview.task.lib.integeriterate.IntegerIterateMasterTask;
import com.cred.interview.task.lib.integeriteratefailure.IntegerIterateFailureMasterTask;

import java.util.ArrayList;
import java.util.List;

public class InMemorySchedulerExample {

    private static List<Node> createNodes(final Integer numNodes) {
        List<Node> nodes = new ArrayList<>();
        for (Integer iter = 0; iter < numNodes; iter++) {
            nodes.add(new InMemoryNode(iter));
        }
        return nodes;
    }

    public static void main(String[] args) {
        List<Node> nodes = createNodes(6);
        Registry registry = new InMemoryRegistry(nodes, new RandomMasterElector(nodes));
        Scheduler scheduler = new InMemoryScheduler(registry);
        IntegerIterateMasterTask masterTask = new IntegerIterateMasterTask(1, 20);
        MasterTaskStatus status = scheduler.scheduleImmediately(masterTask);
        System.out.println("Master task status is: " + status.toString());
    }
}
