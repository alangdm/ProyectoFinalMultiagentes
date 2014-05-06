/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.MaxNodes;

import State.State;
import java.util.ArrayList;

/**
 *
 * @author Luis Ricardo
 */
public class MaxPickup extends PrimitiveMaxNode<Integer>{

    public MaxPickup(String name, ArrayList children) {
        super(name, children);
    }

    @Override
    public State execute(State s) {
        if(!s.getCab().equals(s.getSource())){
            return s;
        }else{
            return new State(s.getCab(), s.getPas(), true, s.getWalls(), s.getDest(), s.getCurrentDest(), true);
        }
    }

    @Override
    public float V(State s) {
        if(s.getCab().equals(s.getSource())&& !s.isInCab()){
            /*if(!V.containsKey(0)){
                V.put(0, 0.0f);
            }*/
            V.putIfAbsent(0, 0.0f);
            return V.get(0);
        }else{
            /*if(!V.containsKey(1)){
                V.put(1, 0.0f);
            }*/
            V.putIfAbsent(1, 0.0f);
            return V.get(1);
        }
        
    }

    @Override
    public int reward(State s) {
        if(s.getCab().equals(s.getSource()) && !s.isInCab()){
            return 1;
        }else{
            return -10;
        }
    }

    @Override
    public void editV(State s, Float value) {
        if(s.getCab().equals(s.getSource()) && !s.isInCab()){
            V.put(0, value);
        }else{
            V.put(1, value);
        }
    }
    
}
