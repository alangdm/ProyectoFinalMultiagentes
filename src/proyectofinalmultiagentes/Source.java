/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import State.Coord2D;

/**
 *
 * @author Alan
 */
public class Source {
    private int color;
    private Coord2D position;
    
    public Source(int color, int x, int y){
        this.color = color;
        position = new Coord2D(x, y);
    }

    public int getColor() {
        return color;
    }

    public Coord2D getPosition() {
        return position;
    }
    
    public int getX(){
        return position.getX();
    }
    
    public int getY(){
        return position.getY();
    }
    
}
