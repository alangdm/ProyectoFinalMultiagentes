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
public class MaxPutdown extends PrimitiveMaxNode<Integer>{

    public MaxPutdown(String name, ArrayList<QNode> children) {
        super(name, children);
    }

    @Override
    public State execute(State s) {
        if(s.getCab().equals(s.getDest()) && s.isInCab()){
            return new State(s.getCab(), s.getPas(), false, s.getWalls(), s.getDest(), s.getCurrentDest());
        }else{
            return s;
        }
    }

    @Override
    public float V(State s) {
        if(s.getCab().equals(s.getDest()) && s.isInCab()){
            if(!V.containsKey(0)){
                V.put(0, 0.0f);
            }
            return V.get(0);
        }else{
            if(!V.containsKey(1)){
                V.put(1, 0.0f);
            }
            return V.get(1);
        }
        
    }

    @Override
    public int reward(State s) {
        if(s.getCab().equals(s.getDest())&& s.isInCab()){
            return 20;
        }else{
            return -10;
        }
    }

    @Override
    public void editV(State s, Float value) {
        if(s.getCab().equals(s.getDest())&& s.isInCab()){
            V.put(0, value);
        }else{
            V.put(1, value);
        }
    }
    
}
