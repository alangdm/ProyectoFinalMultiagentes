package State;


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
public class State {
    private Coord2D cab;
    private Coord2D pas;
    private Coord2D source;
    private Coord2D dest;
    private boolean inCab;
    private int reward;

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }
    private ArrayList<Coord2D> walls;
    public Coord2D currentDest;
    public int size = 5;
    public int getSize() {
        return size;
    }
    

    public Coord2D getCurrentDest() {
        return currentDest;
    }

    public void setCurrentDest(Coord2D currentDest) {
        this.currentDest = currentDest;
    }

    public Coord2D getSource() {
        return source;
    }

    public Coord2D getCab() {
        return cab;
    }

    public Coord2D getPas() {
        return pas;
    }

    public Coord2D getDest() {
        return dest;
    }

    public boolean isInCab() {
        return inCab;
    }

    public ArrayList<Coord2D> getWalls() {
        return walls;
    }

    public State(Coord2D cab, Coord2D pas, boolean inCab, ArrayList<Coord2D> walls,Coord2D dest, Coord2D currentDest) {
        this.cab = cab;
        this.pas = pas;
        this.inCab = inCab;
        this.walls = walls;
        this.dest = dest;
        this.currentDest= currentDest;
        this.source=pas;
    }
    
    public String toString(){
        return "Cab: "+ cab + (inCab ? "Inside" : "outside");
    }
}
