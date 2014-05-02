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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import proyectofinalmultiagentes.Agent;

/**
 *
 * @author Luis Ricardo
 */
public abstract class PrimitiveMaxNode<T> extends MaxNode<T>{
    
    private Agent agent;
    
    protected ConcurrentMap<T, Float> V;
    
    public PrimitiveMaxNode(String name, ArrayList<QNode> children, Agent agent) {
        super(name, children);
        V = new ConcurrentHashMap<>();
        this.agent = agent;
    }
    
     public PrimitiveMaxNode(String name, ArrayList<QNode> children) {
        super(name, children);
        V = new ConcurrentHashMap<>();
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
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
