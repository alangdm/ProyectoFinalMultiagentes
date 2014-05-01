/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import State.Double2DCoord;
import State.State;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Luis Ricardo
 */
public class QGet extends QNode<Double2DCoord>{//source, dest
    public QGet(String name, MaxNode child){
        super(name);
        C1 = new ConcurrentHashMap<>();
        C2 = new ConcurrentHashMap<>();
        this.child = child;
    }
    @Override
    public void editC1(State s, Float value){
        C1.put(new Double2DCoord(s.getSource(), s.getDest()), value);
    }
    @Override
    public void editC2(State s, Float value){
        C2.put(new Double2DCoord(s.getSource(), s.getDest()), value);
    }
    @Override
    public float C1(State s) {
        Double2DCoord sourceDest = new Double2DCoord(s.getSource(), s.getDest());
        /*if(!C1.containsKey(sourceDest)){
            C1.put(sourceDest, 0.0f);
        }*/
        
        C1.putIfAbsent(sourceDest, 0.0f);
        return C1.get(sourceDest);
    }

    @Override
    public float C2(State s) {
        Double2DCoord sourceDest = new Double2DCoord(s.getSource(), s.getDest());
        /*if(!C2.containsKey(sourceDest)){
            C2.put(sourceDest, 0.0f);
        }*/
        
        C2.putIfAbsent(sourceDest, 0.0f);
        return C2.get(sourceDest);
    }

    @Override
    public int time(State s) {
        Double2DCoord nav = new Double2DCoord(s.getSource(),s.getDest());
        /*if(!time.containsKey(nav)){
            time.put(nav, 1);
        }
        */
        
        time.putIfAbsent(nav, 1);
        return time.get(nav);
    }

    @Override
    public synchronized void editTime(State s) {
        Double2DCoord nav = new Double2DCoord(s.getSource(),s.getDest());
        if(!time.containsKey(nav)){
            time.put(nav, 1);
        }else{
            int prevTime = time.get(nav);
            time.put(nav, prevTime+1);
        }
        
    }
}
