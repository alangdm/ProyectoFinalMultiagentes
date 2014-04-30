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
public class MaxNavigate extends MaxNode<Integer>{
    
    public MaxNavigate(String name, ArrayList<QNode> children) {
        super(name, children);
    }

    @Override
    public boolean terminal(State s) {
        return s.getCab().equals(s.getCurrentDest());
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
