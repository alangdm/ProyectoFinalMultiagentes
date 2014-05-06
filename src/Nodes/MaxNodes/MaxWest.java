/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.MaxNodes;

import State.Coord2D;
import State.State;
import java.util.ArrayList;

/**
 *
 * @author Luis Ricardo
 */
public class MaxWest extends PrimitiveMaxNode<Integer>{

    public MaxWest(String name, ArrayList children) {
        super(name, children);
    }

    @Override
    public State execute(State s) {
        if(s.getCab().getY() != 0){
            return new State(new Coord2D(s.getCab().getX(), s.getCab().getY()-1), s.getPas(), s.isInCab(), s.getWalls(), s.getDest(), s.getCurrentDest(), s.isPicked());
        }
        return s;
        
    }

    @Override
    public float V(State s) {
        /*if(!V.containsKey(0)){
            V.put(0, 0.0f);
        }*/
        V.putIfAbsent(0, 0.0f);
        return V.get(0);
    }

    @Override
    public int reward(State s) {
        return -1;
    }

    @Override
    public void editV(State s, Float value) {
        V.put(0, value);
    }
    
}
