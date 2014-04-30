/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.MaxNodes;

import Nodes.QNodes.QNode;
import State.State;
import java.util.ArrayList;

/**
 *
 * @author Luis Ricardo
 */
public class MaxPut extends MaxNode<Integer>{
    
    public MaxPut(String name, ArrayList<QNode> children) {
        super(name, children);
    }

    @Override
    public boolean terminal(State s) {
        return !s.isInCab();
    }

    @Override
    public float pseudoReward(State s) {
        if(terminal(s)){
            return 0;
        }else{
            return -1;
        }
    }
    @Override
    public float maxAction(State s){
        s.setCurrentDest(s.getDest());
        return super.maxAction(s);
    }

    @Override
    public int argMax(State s) {
        s.setCurrentDest(s.getDest());
        return super.argMax(s);
    }
}
