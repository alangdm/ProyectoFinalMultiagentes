
package Nodes.QNodes;

import Nodes.MaxNodes.MaxNode;
import State.Coord2D;
import State.NavState;
import State.State;
import java.util.HashMap;

public class QEast extends QNode<NavState>{
    
    public QEast(String name, MaxNode child){
        super(name);
        C1 = new HashMap<>();
        C2 = new HashMap<>();
        this.child = child;
    }
    @Override
    public void editC1(State s, Float value){
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        C1.put(nav, value);
    }
    @Override
    public void editC2(State s, Float value){
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        C2.put(nav, value);
    }
    @Override
    public float C1(State s) {
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        if(!C1.containsKey(nav)){
            C1.put(nav, 0.0f);
        }
        return C1.get(nav);
    }

    @Override
    public float C2(State s) {
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        if(!C2.containsKey(nav)){
            C2.put(nav, 0.0f);
        }
        return C2.get(nav);
    }

    @Override
    public int time(State s) {
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        if(!time.containsKey(nav)){
            time.put(nav, 1);
        }
        return time.get(nav);
    }

    @Override
    public void editTime(State s) {
        NavState nav = new NavState(s.getCab(),s.getCurrentDest());
        if(!time.containsKey(nav)){
            time.put(nav, 1);
        }else{
            int prevTime = time.get(nav);
            time.put(nav, prevTime+1);
        }
    }
}
