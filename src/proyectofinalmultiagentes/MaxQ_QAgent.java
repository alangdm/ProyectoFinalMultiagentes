package proyectofinalmultiagentes;

import Nodes.MaxNodes.MaxNode;
import State.State;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alvaro
 */
public class MaxQ_QAgent extends Agent implements Runnable{
    
    private MaxNode maxRoot;
    private boolean running = false;
    private boolean runMaxQQ = false;
    private MaxQ_QLearning learning;
    private State startState;
    private ArrayList<State> result;

    public MaxQ_QLearning getLearning() {
        return learning;
    }

    public ArrayList<State> getResult() {
        return result;
    }
    
    public MaxQ_QAgent(String orientation, int positionX, int positionY, Environment env, MessageServer msgSvr, int capacity, int objectiveRed, int objectiveGreen, int objectiveBlue, Interfaz interfaz, MaxNode maxRoot) {
       //super(id, orientation, positionX, positionY, env, msgSvr, capacity);
       super(orientation, positionX, positionY, env, msgSvr, capacity, objectiveRed, objectiveGreen, objectiveBlue, interfaz);
       this.maxRoot = maxRoot;
       this.result = new ArrayList<>();
       learning = new MaxQ_QLearning();
    } 

    public synchronized void runMaxQQ(State startState) {
        runMaxQQ = true;
        this.startState = startState;
    }
    
    @Override
    public void run() {
        //while (running) {
            if (runMaxQQ) {
                  result = learning.maxQQ(maxRoot, startState);
                  runMaxQQ =false;
            }
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        //}
    }
    
    public synchronized void stop() {
        running = false;
    }
}
