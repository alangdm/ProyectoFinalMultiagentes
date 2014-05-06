package proyectofinalmultiagentes;


import State.State;
import Nodes.MaxNodes.MaxNode;
import Nodes.MaxNodes.PrimitiveMaxNode;
import Nodes.QNodes.QNode;
import java.util.ArrayList;

public class MaxQ_QLearning {
    private State secondary;
    public ArrayList<State> maxQQ(MaxNode i, State s){
        secondary = s;
        ArrayList<State> seq = new ArrayList<>();
        if(i.isPrimitive()){
            int reward = ((PrimitiveMaxNode)i).reward(s);
            ((PrimitiveMaxNode)i).editV(s,(1-i.getAlpha())*((PrimitiveMaxNode)i).V(s) + i.getAlpha()*reward );
            secondary = ((PrimitiveMaxNode)i).execute(s);
            s.setReward(reward);
            seq.add(s);
            i.reduceAlpha();
        }else{
            while(!i.terminal(s)){
                
                int act = i.policy(s);
                QNode next = i.getAction(act);
                ArrayList<State> childSeq = maxQQ(next.getChild(), s);
                //System.out.println("Action: "+ next.getName()+ ", produce estado: "+secondary);
                /*try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }*/
                int best;
                ArrayList<QNode> actions = i.getActions();
                best = i.argMax(secondary);
                int N = 1;
                for (State state : childSeq) {
                    float valueC2 = ((1-i.getAlpha(act, state))*next.C2(state))+i.getAlpha(act, state)*(float)Math.pow(i.getDiscountFactor(), N)*(i.pseudoReward(secondary) +actions.get(best).C2(secondary)+actions.get(best).getChild().V1(state));
                    next.editC2(state, valueC2);
                    float valueC1 = ((1-i.getAlpha(act, state))*next.C1(state))+i.getAlpha(act, state)*(float)Math.pow(i.getDiscountFactor(), N)*(actions.get(best).C1(secondary)+actions.get(best).getChild().V1(secondary));
                    next.editC1(state, valueC1);
                    //i.reduceAlpha();
                    N++;
                }
                next.editTime(s);
                seq.addAll(0,childSeq);
                s=secondary;
            }
        }
        return seq;
    }
}
