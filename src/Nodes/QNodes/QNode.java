package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import Nodes.MaxNodes.PrimitiveMaxNode;
import Nodes.MaxQGraphNode;
import State.State;
import java.util.HashMap;

public abstract class QNode<T> extends MaxQGraphNode{
    protected MaxNode child;
    protected HashMap<T, Float> C1;
    protected HashMap<T, Float> C2;
    protected HashMap<T, Integer> time;

    public MaxNode getChild() {
        return child;
    }
    public QNode(String name) {
        super(name);
        time = new HashMap<>();
    }
    public abstract int time(State s);
    public abstract void editTime(State s);
    public abstract float C1(State s);
    public abstract float C2(State s);
    public abstract void editC1(State s, Float value);
    public abstract void editC2(State s, Float value);
    public float Q1(State s) {
        if(!child.isPrimitive()){
            return C1(s) + child.V1(s);
        }else{
            return C1(s) + ((PrimitiveMaxNode)child).V(s);
        }
    }
    
}
