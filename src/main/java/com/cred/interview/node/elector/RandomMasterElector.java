package com.cred.interview.node.elector;

import com.cred.interview.node.Node;

import java.util.List;
import java.util.Random;

public class RandomMasterElector implements MasterElector {

    private List<Node> masters;

    public RandomMasterElector(final List<Node> masters) {
        this.masters = masters;
    }

    @Override
    public Node getMaster() {
        return masters.get(new Random().nextInt(masters.size()));
    }
}
