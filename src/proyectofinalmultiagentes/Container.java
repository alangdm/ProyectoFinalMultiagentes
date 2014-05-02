/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import State.Coord2D;
import java.awt.Color;

/**
 *
 * @author Alan
 */
public class Container {
    private int r;
    private int g;
    private int b;
    private Coord2D position;
    
    public Container(int positionX, int positionY){
        r=0;
        g=0;
        b=0;
        position = new Coord2D(positionX, positionY);
    }
    
    public void depositColor(int color, int amount){
        switch(color){
            case Agent.COLORRED:
                r = r+amount>255?255:r+amount;
                break;
            case Agent.COLORGREEN:
                g = g+amount>255?255:g+amount;
                break;
            case Agent.COLORBLUE:
                b = b+amount>255?255:b+amount;
                break;
            default:
                break;
        }
    }
    
    public Color getContainerColor(){
        return new Color(r,g,b);
    }

    public Coord2D getPosition(){
        return position;
    }
    
    public int getX(){
        return position.getX();
    }
    
    public int getY(){
        return position.getY();
    }
}
