

package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import State.Coord2D;
import State.Double2DCoord;
import State.State;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class QPickUp extends QNode<Double2DCoord>{//source, taxi loc
    public QPickUp(String name, MaxNode child){
        super(name);
        C1 = new ConcurrentHashMap<>();
        C2 = new ConcurrentHashMap<>();
        this.child = child;
    }
    @Override
    public void editC1(State s, Float value){
        C1.put(new Double2DCoord(s.getSource(), s.getCab()), value);
    }
    @Override
    public void editC2(State s, Float value){
        C2.put(new Double2DCoord(s.getSource(), s.getCab()), value);
    }
    @Override
    public float C1(State s) {
        Double2DCoord sourceCab = new Double2DCoord(s.getSource(), s.getCab());
        /*if(!C1.containsKey(sourceCab)){
            C1.put(sourceCab, 0.0f);
        }*/
        C1.putIfAbsent(sourceCab, 0.0f);
        return C1.get(sourceCab);
    }

    @Override
    public float C2(State s) {
        Double2DCoord sourceCab = new Double2DCoord(s.getSource(), s.getCab());
        /*if(!C2.containsKey(sourceCab)){
            C2.put(sourceCab, 0.0f);
        }*/
        
        C2.putIfAbsent(sourceCab, 0.0f);
        return C2.get(sourceCab);
    }


    @Override
    public int time(State s) {
        Double2DCoord nav = new Double2DCoord(s.getSource(),s.getCab());
        /*if(!time.containsKey(nav)){
            time.put(nav, 1);
        }*/
        
        time.putIfAbsent(nav, 1);
        return time.get(nav);
    }

    @Override
    public synchronized void editTime(State s) {
        Double2DCoord nav = new Double2DCoord(s.getSource(),s.getCab());
        if(!time.containsKey(nav)){
            time.put(nav, 1);
        }else{
            int prevTime = time.get(nav);
            time.put(nav, prevTime+1);
        }
        
    }
}
