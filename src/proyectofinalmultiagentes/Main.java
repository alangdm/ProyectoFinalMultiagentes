package proyectofinalmultiagentes;


import Nodes.MaxNodes.*;
import Nodes.QNodes.*;
import State.Coord2D;
import State.State;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Luis Ricardo
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MaxQ_QLearning learning = new MaxQ_QLearning();
        
        //tree
        MaxNode mNorth = new MaxNorth("MaxNorth", null);
        QNode qNorth = new QNorth("QNorth", mNorth);
        
        MaxNode mSouth = new MaxSouth("MaxSouth", null);
        QNode qSouth = new QSouth("QSouth", mSouth);
        
        MaxNode mEast = new MaxEast("MaxEast", null);
        QNode qEast = new QEast("QEast", mEast);
        
        MaxNode mWest = new MaxWest("MaxWest", null);
        QNode qWest = new QWest("QWest", mWest);
        
        ArrayList<QNode> navigation = new ArrayList<>();
        navigation.add(qWest);
        navigation.add(qNorth);
        navigation.add(qSouth);
        navigation.add(qEast);
        MaxNode mNavigate = new MaxNavigate("MaxNavigate",navigation);
        
        MaxNode maxPickup = new MaxPickup("MaxPickup", null); 
        QNode qPickup = new QPickUp("QPickup", maxPickup);
        QNode qNavigateForGet = new QNavigateForGet("QNavigateForGet", mNavigate);
        
        ArrayList<QNode> get = new ArrayList<>();
        get.add(qPickup);
        get.add(qNavigateForGet);
        MaxNode maxGet = new MaxGet("MaxGet", get);
        
        MaxNode maxPutDown = new MaxPutdown("MaxPutdown", null); 
        QNode qPutDown = new QPutDown("QPutDown", maxPutDown);
        QNode qNavigateForPut = new QNavigateForPut("QNavigateForPut", mNavigate);
        
        ArrayList<QNode> put = new ArrayList<>();
        put.add(qNavigateForPut);
        put.add(qPutDown);
        
        MaxNode maxPut = new MaxPut("MaxPut", put);
        
        QNode qGet = new QGet("Qget", maxGet);
        QNode qPut = new QPut("QPut", maxPut);
        
        ArrayList<QNode> root = new ArrayList<>();
        root.add(qGet);
        //root.add(qNavigateForPut);
        //root.add(qPutDown);
        root.add(qPut);
        
        MaxNode maxRoot = new Root("MaxRoot", root);
        
        char[][] map = new char[State.getSize()][];
        for(int i=0;i<5;i++){
            map[i] = new char[State.getSize()];
        }
        Source redSource = new Source(Agent.COLORRED, 0, 0);
        Source greenSource = new Source(Agent.COLORGREEN, 3, 4);
        Source blueSource = new Source(Agent.COLORBLUE, 4, 2);
        Container container = new Container(3, 3);
        Interfaz interfaz = new Interfaz(State.getSize(), State.getSize());
        interfaz.setVisible(true);
        Environment environment = new Environment(map, container, redSource, greenSource, blueSource, interfaz);
        
        MaxQ_QAgent agente1 = new MaxQ_QAgent("left", 0, 0, environment, null, 0, 0, 0, 0, interfaz, maxRoot);
        MaxQ_QAgent agente2 = new MaxQ_QAgent("left", 0, 0, environment, null, 0, 0, 0, 0, interfaz, maxRoot);
        MaxQ_QAgent agente3 = new MaxQ_QAgent("left", 0, 0, environment, null, 0, 0, 0, 0, interfaz, maxRoot);
        
        BufferedWriter out1 = null;
        BufferedWriter out2 = null;
        BufferedWriter out3 = null;
        try {
        out1 = new BufferedWriter(new FileWriter("resultsAg1.csv", false));
        out2 = new BufferedWriter(new FileWriter("resultsAg2.csv", false));
        out3 = new BufferedWriter(new FileWriter("resultsAg3.csv", false));
        } catch (IOException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < 5000; i++) {
            
            State state1 = initState();
            State state2 = initState();
            State state3 = initState();
            
            interfaz.clean(new Coord2D(agente1.getPositionX(), agente1.getPositionY()));
            agente1.setPositionX(state1.getCab().getX());
            agente1.setPositionY(state1.getCab().getY());
            agente1.setAgentInInteface();
            interfaz.clean(new Coord2D(agente2.getPositionX(), agente2.getPositionY()));
            agente2.setPositionX(state2.getCab().getX());
            agente2.setPositionY(state2.getCab().getY());
            agente2.setAgentInInteface();
            interfaz.clean(new Coord2D(agente3.getPositionX(), agente3.getPositionY()));
            agente3.setPositionX(state3.getCab().getX());
            agente3.setPositionY(state3.getCab().getY());
            agente3.setAgentInInteface();
            
            agente1.runMaxQQ(state1);
            agente2.runMaxQQ(state2);
            agente3.runMaxQQ(state3);
            Thread ag1 = new Thread(agente1, "Thread de agente1");
            Thread ag2 = new Thread(agente2, "Thread de agente2");
            Thread ag3 = new Thread(agente3, "Thread de agente3");
            ag1.start();
            ag2.start();
            ag3.start();
            
            int res;
            
            ag1.join();
            System.out.println("Agente 1 iteracion: "+ i + "\n"+"Start: "+state1.getCab() + "\nSource: "+ state1.getSource()+"\nDestination: " + state1.getDest() + "\nBest: " + (man(state1.getCab(), state1.getSource()) + man( state1.getSource(),state1.getDest()) + 2) +"\nCurrent: ");
            //ArrayList<State> result = learning.maxQQ(maxRoot, state);
            System.out.println(agente1.getResult().size() + ", lista: "+ agente1.getResult());
            if(i>2900){
               // System.out.println("Politica jerarquica: "+ agente1.getLearning().hierarchicalExecution(state1, maxRoot));
            }
            
            res=0;
            for (State s : agente1.getResult()) {
            res+= s.getReward();
            }
            try {
            // BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg1.csv", true));
            out1.write(""+res+","+i);
            out1.newLine();

            } catch (IOException e) {}
            
            ag2.join();
            System.out.println("Agente 2 iteracion: "+ i + "\n"+"Start: "+state2.getCab() + "\nSource: "+ state2.getSource()+"\nDestination: " + state2.getDest() + "\nBest: " + (man(state2.getCab(), state2.getSource()) + man( state2.getSource(),state2.getDest()) + 2) +"\nCurrent: ");
            //ArrayList<State> result = learning.maxQQ(maxRoot, state);
            System.out.println(agente2.getResult().size() + ", lista: "+ agente2.getResult());
            
            res=0;
            for (State s : agente2.getResult()) {
            res+= s.getReward();
            }
            try {
            //BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg2.csv", true));
            out2.write(""+res+","+i);
            out2.newLine();

            } catch (IOException e) {}
            
            
            ag3.join();
            
            res=0;
            for (State s : agente3.getResult()) {
            res+= s.getReward();
            }
            try {
            //BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg3.csv", true));
            out3.write(""+res+","+i);
            out3.newLine();
            } catch (IOException e) {}
            System.out.println("Agente 3 iteracion: "+ i + "\n"+"Start: "+state3.getCab() + "\nSource: "+ state3.getSource()+"\nDestination: " + state3.getDest() + "\nBest: " + (man(state3.getCab(), state3.getSource()) + man( state3.getSource(),state3.getDest()) + 2) +"\nCurrent: ");
            //ArrayList<State> result = learning.maxQQ(maxRoot, state);
            System.out.println(agente3.getResult().size() + ", lista: "+ agente3.getResult());
            
        }
        
        interfaz.clean(new Coord2D(agente1.getPositionX(), agente1.getPositionY()));
        interfaz.clean(new Coord2D(agente2.getPositionX(), agente2.getPositionY()));
        interfaz.clean(new Coord2D(agente3.getPositionX(), agente3.getPositionY()));
        
        Agent.resetId();
        
        MessageServer server = new MessageServer();
        
        Coord2D temp = genSrc();
        RecolectorAgent recAgent1 = new RecolectorAgent("left", temp.getY(), temp.getX(), environment, server, 100, 100, 100, 100, interfaz, maxRoot);
        server.addAgent(recAgent1);
        temp = genSrc();
        RecolectorAgent recAgent2 = new RecolectorAgent("left", temp.getY(), temp.getX(), environment, server, 100, 100, 100, 100, interfaz, maxRoot);
        server.addAgent(recAgent2);
        temp = genSrc();
        RecolectorAgent recAgent3 = new RecolectorAgent("left", temp.getY(), temp.getX(), environment, server, 100, 100, 100, 100, interfaz, maxRoot);
        server.addAgent(recAgent3);
        
        Thread ag1 = new Thread(recAgent1, "Thread de agente1");
        Thread ag2 = new Thread(recAgent2, "Thread de agente2");
        Thread ag3 = new Thread(recAgent3, "Thread de agente3");
        ag1.start();
        Thread.sleep(100);
        ag2.start();
        Thread.sleep(100);
        ag3.start();
        
        environment.showSourcesAndContainers();
        
    }
    public static State initState(){
        Coord2D start = genStart();
        Coord2D src = genSrc();
        Coord2D des = genDes();
        State state = new State(start,src, false,new  ArrayList<Coord2D>(),des, src, false);
        return state;
    }
    public static int man(Coord2D start, Coord2D finish){
        return Math.abs(start.getX()-finish.getX())+ Math.abs(start.getY()-finish.getY()) ;
    }
    public static Coord2D genStart(){
        return new Coord2D((int)(Math.random() * 5), (int)(Math.random() * 5));
    }
    public static Coord2D genSrc(){
        int s = 4;
        int x[] ={0, 2, 4, 4};
        int y[] ={0, 4, 0, 3};
        
        List<Coord2D> arr = new ArrayList<>();
        
        Coord2D f1 = new Coord2D(0, 0);
        Coord2D f2 = new Coord2D(2, 4);
        Coord2D f3 = new Coord2D(4, 3);
        arr.add(f1);
        arr.add(f2);
        arr.add(f3);
        
        return arr.get((int)(Math.random() * 3));
    }
    public static Coord2D genDes(){
        int s = 4;
        int x[] ={0, 3, 3, 4};
        int y[] ={3, 4, 0, 3};
        //return new Coord2D(x[(int)(Math.random() * s)],y[(int)(Math.random() * s)] );
        return new Coord2D(3,3);
    }
}
