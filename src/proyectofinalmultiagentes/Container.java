/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import java.awt.Color;

/**
 *
 * @author Alan
 */
public class Container {
    private int r;
    private int g;
    private int b;
    
    public Container(){
        r=0;
        g=0;
        b=0;
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
}
