/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import Nodes.MaxNodes.MaxNode;
import State.State;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alvaro
 */
public class RecolectorAgent extends Agent implements Runnable{
    
    private MaxNode maxRoot;
    private boolean running = false;
    private MaxQ_QLearning learning;
    private ArrayList<State> result;

    public MaxQ_QLearning getLearning() {
        return learning;
    }

    public ArrayList<State> getResult() {
        return result;
    }
    
    
     public RecolectorAgent(String orientation, int positionX, int positionY, Environment env, MessageServer msgSvr, int capacity, int objectiveRed, int objectiveGreen, int objectiveBlue, Interfaz interfaz, MaxNode maxRoot) {
        super(orientation, positionX, positionY, env, msgSvr, capacity, objectiveRed, objectiveGreen, objectiveBlue, interfaz);
        this.maxRoot = maxRoot;
        this.result = new ArrayList<>();
        learning = new MaxQ_QLearning();
     }

    @Override
    public void run() {
        running = true;
        while (running) {
            if(getObjectiveAccomplished()){
                env.removeAgentFromPosition(positionX, positionY);
                break;
            }
            chooseNewColor();
            
        }
    }

    @Override
    public boolean chooseNewColor() {
        if(super.chooseNewColor()){
            State state = getCurrentState();
            
            //List<String> actions = learning.hierarchicalExecution(state, maxRoot); // aqui puede ser mejor llamar a maxQ
            
            
            ArrayList<State> states = learning.maxQQ(maxRoot, state);
            
            List<String> actions = new ArrayList<>();
            
            for (int i = states.size()-1;i>=0;i--) {
                actions.add(states.get(i).getAction());
            }
            
            executeListOfActions(actions);
            return true;
        }
        return false;
    }

    public synchronized void stop() {
        running = false;
    }
}
