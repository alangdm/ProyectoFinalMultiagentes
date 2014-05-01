package Nodes.MaxNodes;


import State.State;
import Nodes.MaxQGraphNode;
import Nodes.QNodes.QNode;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class MaxNode<T> extends MaxQGraphNode{
    protected ArrayList<QNode> actions;
    protected float alpha = 0.9f;
    protected float discountFactor =1f;
    //protected HashMap<T, Integer> time;
    protected int maxActionValue =0;
    //protected float time = 1.0f-0.01f;

    public float getDiscountFactor() {
        return discountFactor;
    }

    public ArrayList<QNode> getActions() {
        return actions;
    }

    public float getAlpha(int action, State s) {
        return 0.99f/actions.get(action).time(s);
    }
    public float getAlpha(){
        return alpha;
    }
    public MaxNode(String name, ArrayList<QNode> children) {
        super(name);
        this.actions = children;
    }
    public boolean isPrimitive(){
        return actions == null;
    }
    public void reduceAlpha(){
        alpha*=0.99;
        //time-=0.01f;
    }
    public QNode getAction(int index){
        return actions.get(index);
    }
    public abstract boolean terminal(State s);
    
    public int policy(State s) {//egreedy de forma glie
        maxAction(s);
        //System.out.println("Accion maxima vale: " +maxAction(s));
        int max = maxActionValue;
        float epsilon = 0.99f/actions.get(max).time(s);
        if(Math.random() < 1-epsilon){
            //System.out.println("Greedy con epsilon: " + epsilon);
            return max;
        }else{
            //System.out.println("Random con epsilon: " + epsilon);
            return (int)(Math.random() * actions.size());
        }
    }
    public float V1(State s){
        if(this.isPrimitive()){
            return((PrimitiveMaxNode)this).V(s);
        }
        float max = Float.NEGATIVE_INFINITY;
        for (int i = 0; i < actions.size(); i++) {
            float piv = actions.get(i).Q1(s);
            //float piv = actions.get(i).C1(s);
            if(piv>max){
                max = piv;
            }
        }
        return max;
    }
    
    public abstract float pseudoReward(State s);
    public float maxAction(State s){
        if(isPrimitive()){
            return ((PrimitiveMaxNode)this).V(s);
        }
        float max =Float.NEGATIVE_INFINITY;
        int action =0;
        for (int i = 0; i < actions.size(); i++) {
            if(terminal(s)){//si esto arregla todo me  meto una bala por el ***
                maxActionValue = i;
                return -100;
            }
            float piv = actions.get(i).C2(s) + actions.get(i).getChild().maxAction(s);
            //System.out.println("Action: "+getName()+"-" + actions.get(i).getName() + " value: "+ piv);
            if(piv > max){
                max = piv;
                action = i;
            }
        }
        maxActionValue = action;
        return max;
    }
    public int argMax(State s){
        float max =Float.NEGATIVE_INFINITY;
        int action =0;
        for (int i = 0; i < actions.size(); i++) {
            float piv;
            piv = actions.get(i).C2(s)+actions.get(i).getChild().V1(s);
            if(piv > max){
                max = piv;
                action = i;
            }
        }
        return action;
    }
    
}
