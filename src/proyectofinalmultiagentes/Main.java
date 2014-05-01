package proyectofinalmultiagentes;


import Nodes.MaxNodes.*;
import Nodes.QNodes.*;
import State.Coord2D;
import State.State;
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
    public static void main(String[] args) {
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
        
        
        /*ArrayList<State> result = learning.maxQQ(maxRoot, state);
        System.out.println(result);*/
        for (int i = 0; i < 10000; i++) {
            Coord2D start = genStart();
            Coord2D src = genSrc();
            Coord2D des = genDes();
            State state = new State(start,src, false,new  ArrayList<Coord2D>(),des, src);
            System.out.println("Start: "+start + "\nSource: "+ src+"\nDestination: " + des + "\nBest: " + (man(start,src) + man(src,des) + 2) +"\nCurrent: ");
            //for (int j = 0; j < 5; j++) {
                ArrayList<State> result = learning.maxQQ(maxRoot, state);
                System.out.println(result.size() + ", lista: "+ result);
            //}
             
                
            /*try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
            }*/
        }
        
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
