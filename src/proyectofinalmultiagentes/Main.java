package proyectofinalmultiagentes;


import Nodes.MaxNodes.*;
import Nodes.QNodes.*;
import State.Coord2D;
import State.State;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
        
        MaxQ_QAgent agente1 = new MaxQ_QAgent(0, "Caca", 0, 0, null, null, 0, maxRoot);
        MaxQ_QAgent agente2 = new MaxQ_QAgent(0,"Caca", 0, 0, null, null, 0, maxRoot);
        MaxQ_QAgent agente3 = new MaxQ_QAgent(0, "Caca", 0, 0, null, null, 0, maxRoot);
        
        for (int i = 0; i < 3000; i++) {
            
            State state1 = initState();
            State state2 = initState();
            State state3 = initState();
            
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
                System.out.println("Politica jerarquica: "+ agente1.getLearning().hierarchicalExecution(state1, maxRoot));
            }
            
            res=0;
            for (State s : agente1.getResult()) {
                res+= s.getReward();
            }
            try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg1.csv", true));
                out.write(""+res+","+i);
                out.newLine();
                out.close();
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
            BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg2.csv", true));
                out.write(""+res+","+i);
                out.newLine();
                out.close();
            } catch (IOException e) {}
            
            ag3.join();
            
            res=0;
            for (State s : agente3.getResult()) {
                res+= s.getReward();
            }
            try {
            BufferedWriter out = new BufferedWriter(new FileWriter("resultsAg3.csv", true));
                out.write(""+res+","+i);
                out.newLine();
                out.close();
            } catch (IOException e) {}
            
            System.out.println("Agente 3 iteracion: "+ i + "\n"+"Start: "+state3.getCab() + "\nSource: "+ state3.getSource()+"\nDestination: " + state3.getDest() + "\nBest: " + (man(state3.getCab(), state3.getSource()) + man( state3.getSource(),state3.getDest()) + 2) +"\nCurrent: ");
            //ArrayList<State> result = learning.maxQQ(maxRoot, state);
            System.out.println(agente3.getResult().size() + ", lista: "+ agente3.getResult());
        }
        
    }
    public static State initState(){
        Coord2D start = genStart();
        Coord2D src = genSrc();
        Coord2D des = genDes();
        State state = new State(start,src, false,new  ArrayList<Coord2D>(),des, src);
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
        int x[] ={0, 0, 4, 4};
        int y[] ={0, 4, 0, 3};
        return new Coord2D(x[(int)(Math.random() * s)],y[(int)(Math.random() * s)] );
    }
    public static Coord2D genDes(){
        int s = 4;
        int x[] ={0, 0, 4, 4};
        int y[] ={0, 4, 0, 3};
        return new Coord2D(x[(int)(Math.random() * s)],y[(int)(Math.random() * s)] );
    }
}
