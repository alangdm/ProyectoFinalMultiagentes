/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

/**
 *
 * @author Alan
 */
public class Message<E> {
    private String performative;
    private String type;
    private E content;
    private int receiverId;
    private int senderId;

    public Message(String performative, String type, E content, int receiverId, int senderId) {
        this.performative = performative;
        this.type = type;
        this.content = content;
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    public String getPerformative() {
        return performative;
    }
    
    public String getType(){
        return type;
    }

    public E getContent() {
        return content;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getSenderId() {
        return senderId;
    }
    
    
}
