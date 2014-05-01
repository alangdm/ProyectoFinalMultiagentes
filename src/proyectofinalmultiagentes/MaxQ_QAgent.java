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
    
    public MaxQ_QAgent(int id, String orientation, int positionX, int positionY, Environment env, MessageServer msgSvr, int capacity,  MaxNode maxRoot) {
       super(id, orientation, positionX, positionY, env, msgSvr, capacity);
       this.maxRoot = maxRoot;
       learning = new MaxQ_QLearning();
    } 

    public synchronized void runMaxQQ(State startState) {
        runMaxQQ = true;
        this.startState = startState;
    }
    
    @Override
    public void run() {
        while (running) {
            if (runMaxQQ) {
                  ArrayList<State> result = learning.maxQQ(maxRoot, startState);
            }
            else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
    public synchronized void stop() {
        running = false;
    }
}
