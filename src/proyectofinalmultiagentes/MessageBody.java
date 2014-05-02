/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofinalmultiagentes;

/**
 *
 * @author Cynthia
 */
public class MessageBody {
    private char color;
    private int amount;
    
     public MessageBody(char color, int amount){
        this.color = color;
        this.amount = amount;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public char getColor() {
        return color;
    }

    public int getAmount() {
        return amount;
    }
    
    
   
}
