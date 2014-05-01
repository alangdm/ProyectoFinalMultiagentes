
package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import State.Coord2D;
import State.Double2DCoord;
import State.State;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QNavigateForGet extends QNode<Coord2D>{//source
    public QNavigateForGet(String name, MaxNode child){
        super(name);
        C1 = new ConcurrentHashMap<>();
        C2 = new ConcurrentHashMap<>();
        this.child = child;
    }
    @Override
    public void editC1(State s, Float value){
        C1.put(s.getSource(), value);
    }
    @Override
    public void editC2(State s, Float value){
        C2.put(s.getSource(), value);
    }
    @Override
    public float C1(State s) {
        /*if(!C1.containsKey(s.getSource())){
            C1.put(s.getSource(), 0.0f);
        }*/
        
        C1.putIfAbsent(s.getSource(), 0.0f);
        return C1.get(s.getSource());
    }

    @Override
    public float C2(State s) {
        /*if(!C2.containsKey(s.getSource())){
            C2.put(s.getSource(), 0.0f);
        }*/
        C2.putIfAbsent(s.getSource(), 0.0f);
        return C2.get(s.getSource());
    }

    @Override
    public int time(State s) {
        Coord2D piv= s.getSource();
        /*if(!time.containsKey(piv)){
            time.put(piv, 1);
        }*/
        
        time.putIfAbsent(piv, 1);
        return time.get(piv);
    }

    @Override
    public synchronized void editTime(State s) {
        Coord2D piv= s.getSource();
        if(!time.containsKey(piv)){
            time.put(piv, 1);
        }else{
            int prevTime = time.get(piv);
            time.put(piv, prevTime+1);
        }
        
    }
}
