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
        char[][] map = new char[4][];
        map[0] = new char[]{1,0,0};
        map[1] = new char[]{0,0,0};
        map[2] = new char[]{0,0,0};
        map[3] = new char[]{0,1,0};
        
        Environment env = new Environment(map, new Container(0, 1), new Source(Environment.REDSOURCE, 0, 2), new Source(Environment.GREENSOURCE, 1, 0), new Source(Environment.BLUESOURCE, 1, 1));
        MessageServer msgSvr = new MessageServer();
        
        List<String> actions = new ArrayList<>();
        actions.add("turnright");
        actions.add("movebackwards");
        actions.add("turnleft");
        actions.add("turnleft");
        actions.add("moveforward");
        //actions.add("moveforward"); //descomentar para ver como en caso de que tenga en frente una pared, obstaculo o agente gira a la izquierda hasta encontrar un lugar al que pueda avanzar y avance a el
        
        Agent ag = new Agent("right", 1, 2, env, msgSvr, 100,10,100,50);
        msgSvr.addAgent(ag);
        System.out.println(env);
        ag.executeListOfActions(actions);
        System.out.println(env);
        
        
    }
}
