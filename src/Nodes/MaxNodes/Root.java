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
public class Root extends MaxNode<Integer>{
    
    public Root(String name, ArrayList<QNode> children) {
        super(name, children);
    }

    @Override
    public boolean terminal(State s) {
        return !s.isInCab() && s.getCab().equals(s.getDest());
    }

    @Override
    public int policy(State s) {//egreedy de forma glie
        if(s.isInCab()){
            s.setCurrentDest(s.getDest());
        }
        return super.policy(s);
    }

    @Override
    public float pseudoReward(State s) {
        if(terminal(s)){
            return 0;
        }else{
            return -1;
        }
    }
    
}
