/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alan
 */
public class ProyectoFinalMultiagentes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] map = new int[4][];
        map[0] = new int[]{1,0,0};
        map[1] = new int[]{0,0,0};
        map[2] = new int[]{0,0,0};
        map[3] = new int[]{0,1,0};
        
        Environment env = new Environment(map);
        
        List<String> actions = new ArrayList<>();
        actions.add("turnright");
        actions.add("movebackwards");
        actions.add("turnleft");
        actions.add("turnleft");
        actions.add("moveforward");
        //actions.add("moveforward"); //descomentar para ver como en caso de que tenga en frente una pared, obstaculo o agente gira a la izquierda hasta encontrar un lugar al que pueda avanzar y avance a el
        
        Agent ag = new Agent(0, "right", 1, 2, env);
        System.out.println(env);
        ag.executeListOfActions(actions);
        System.out.println(env);
        
        
    }
}
