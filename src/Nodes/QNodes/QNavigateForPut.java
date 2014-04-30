/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import State.Coord2D;
import State.State;
import java.util.HashMap;

/**
 *
 * @author Luis Ricardo
 */
public class QNavigateForPut extends QNode<Coord2D>{//destinatin
    public QNavigateForPut(String name, MaxNode child){
        super(name);
        C1 = new HashMap<>();
        C2 = new HashMap<>();
        this.child = child;
    }
    @Override
    public void editC1(State s, Float value){
        C1.put(s.getDest(), value);
    }
    @Override
    public void editC2(State s, Float value){
        C2.put(s.getDest(), value);
    }
    @Override
    public float C1(State s) {
        if(!C1.containsKey(s.getDest())){
            C1.put(s.getDest(), 0.0f);
        }
        return C1.get(s.getDest());
    }

    @Override
    public float C2(State s) {
        if(!C2.containsKey(s.getDest())){
            C2.put(s.getDest(), 0.0f);
        }
        return C2.get(s.getDest());
    }


    @Override
    public int time(State s) {
        Coord2D piv= s.getDest();
        if(!time.containsKey(piv)){
            time.put(piv, 1);
        }
        return time.get(piv);
    }

    @Override
    public void editTime(State s) {
        Coord2D piv= s.getDest();
        if(!time.containsKey(piv)){
            time.put(piv, 1);
        }else{
            int prevTime = time.get(piv);
            time.put(piv, prevTime+1);
        }
        
    }
}
