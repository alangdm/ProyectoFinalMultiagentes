/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.MaxNodes;

import Nodes.QNodes.QNode;
import State.Coord2D;
import State.State;
import java.util.ArrayList;

/**
 *
 * @author Luis Ricardo
 */
public class MaxSouth extends PrimitiveMaxNode<Integer>{

    public MaxSouth(String name, ArrayList<QNode> children) {
        super(name, children);
    }

    @Override
    public State execute(State s) {
        if(s.getCab().getX() != s.getSize() - 1){
            return new State(new Coord2D(s.getCab().getX()+1, s.getCab().getY()), s.getPas(), s.isInCab(), s.getWalls(), s.getDest(), s.getCurrentDest());
        }
        return s;
    }

    @Override
    public float V(State s) {
        if(!V.containsKey(0)){
            V.put(0, 0.0f);
        }
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
