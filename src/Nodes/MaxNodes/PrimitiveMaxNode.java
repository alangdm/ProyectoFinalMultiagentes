/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.MaxNodes;

import Nodes.QNodes.QNode;
import State.State;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Luis Ricardo
 */
public abstract class PrimitiveMaxNode<T> extends MaxNode<T>{
    public PrimitiveMaxNode(String name, ArrayList<QNode> children) {
        super(name, children);
        V = new HashMap<>();
    }

    @Override
    public boolean terminal(State s) {
        return true;
    }

    @Override
    public int policy(State s) {
        return 0;
    }
    
    @Override
    public float pseudoReward(State s) {
        return 0;
    }

    @Override
    public float getAlpha(int action, State s) {
        return this.alpha;
    }
    public abstract void editV(State s,Float value);
    public abstract int reward(State s);
    public abstract State execute(State s);
    public abstract float V(State s);
}
