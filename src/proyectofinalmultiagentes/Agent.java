/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import java.util.List;

/**
 *
 * @author Alan
 */
public class Agent implements Comparable<Agent> {
    
    public static final char COLORNONE = 0;
    public static final char COLORRED = 1;
    public static final char COLORGREEN = 2;
    public static final char COLORBLUE = 3;
    
    private int id;
    /**
     * orientation puede ser "up", "down", "left", "right", es case insentitive
     */
    private String orientation;
    private int positionX;
    private int positionY;
    private Environment env;
    private ProximitySensor prox;
    private MessageServer msgSvr;
    private int capacity;
    private char currentColor;
    private int colorAmount;
    private int amountToHarvest;

    public Agent(int id, String orientation, int positionX, int positionY, Environment env, MessageServer msgSvr, int capacity) {
        this.id = id;
        this.orientation = orientation.toLowerCase();
        this.positionX = positionX;
        this.positionY = positionY;
        this.env = env;
        this.prox = new ProximitySensor(this);
        this.env.setAgentInPosition(positionX, positionY);
        this.msgSvr = msgSvr;
        this.capacity = capacity;
        this.currentColor = COLORNONE;
        this.colorAmount = 0;
        this.amountToHarvest = 0;
    }
    public Agent(){
        
    }

    //Getters
    
    public String getOrientation() {
        return orientation;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Environment getEnv() {
        return env;
    }
    
    public int getId(){
        return id;
    }
    
    //End Getters
    
    //Overriden Methods
    
    @Override
    public int compareTo(Agent o) {
        return id-o.id;
    }
    
    //End Overriden Methods
    
    //Ejecutores de acciones
    
    public void executeListOfActions(List<String> actions){
        for(String action : actions){
            if(action.equalsIgnoreCase("moveforward") && prox.senseDistance()==1){
                if(avoidObstacle()){
                    //pedir replaneacion
                    System.out.println("Necesito un nuevo plan");
                }
                else{
                    //enviar mensaje de encierro
                    System.out.println("Estoy encerrado");
                }
                break;
            }
            else{
                executeAction(action);   
            }
        }
    }
    
    private void executeAction(String action){
        switch(action.toLowerCase()){
            case "turnleft":
                turnLeft();
                break;
            case "turnright":
                turnRight();
                break;
            case "moveforward":
                moveForward();
                break;
            case "movebackwards":
                moveBackwards();
                break;
            case "harvestcolor":
                harvestColor();
                break;
            case "depositcolor":
                depositColor();
                break;
            default:
                //ERROR accion invalida
                break;
        }
    }
    //End ejecutores de acciones
    
    //Acciones planeables
    
    private void turnLeft(){
        switch(orientation){
            case "up":
                orientation = "left";
                break;
            case "down":
                orientation = "right";
                break;
            case "left":
                orientation = "down";
                break;
            case "right":
                orientation = "up";
                break;
        }
    }
    
    private void turnRight(){
        switch(orientation){
            case "up":
                orientation = "right";
                break;
            case "down":
                orientation = "left";
                break;
            case "left":
                orientation = "up";
                break;
            case "right":
                orientation = "down";
                break;
        }
    }
    
    private void moveForward(){
        switch(orientation){
            case "up":
                if((positionY-1)>=0 && env.getMapObjectInPosition(positionX, positionY-1)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY--;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "down":
                if((positionY+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX, positionY+1)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY++;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "left":
                if((positionX-1)>=0 && env.getMapObjectInPosition(positionX-1, positionY)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX--;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "right":
                if((positionX+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX+1, positionY)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX++;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
        }
    }
    
    private void moveBackwards(){
        switch(orientation){
            case "up":
                if((positionY+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX, positionY+1)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY++;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "down":
                if((positionY-1)>=0 && env.getMapObjectInPosition(positionX, positionY-1)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY--;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "left":
                if((positionX+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX+1, positionY)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX++;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
            case "right":
                if((positionX-1)>=0 && env.getMapObjectInPosition(positionX-1, positionY)== Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX--;
                    env.setAgentInPosition(positionX, positionY);
                }
                break;
        }
    }
    
    private void harvestColor(){
        //OPCIONAL CREAR CLASE DE SOURCE TAMBIEN
        switch(env.getMapObjectInPosition(positionX, positionY)){
            case Environment.REDSOURCE:
                if(currentColor == COLORRED){
                    if(capacity<amountToHarvest){
                        colorAmount = capacity;
                    }
                    else{
                        colorAmount = amountToHarvest;
                    }
                    amountToHarvest-=colorAmount;
                }
                else{
                    //FAIL
                }
                break;
            case Environment.GREENSOURCE:
                if(currentColor == COLORGREEN){
                    if(capacity<amountToHarvest){
                        colorAmount = capacity;
                    }
                    else{
                        colorAmount = amountToHarvest;
                    }
                    amountToHarvest-=colorAmount;
                }
                else{
                    //FAIL
                }
                break;
            case Environment.BLUESOURCE:
                if(currentColor == COLORBLUE){
                    if(capacity<amountToHarvest){
                        colorAmount = capacity;
                    }
                    else{
                        colorAmount = amountToHarvest;
                    }
                    amountToHarvest-=colorAmount;
                }
                else{
                    //FAIL
                }
                break;
            default:
                //FAIL
                break;
        }
    }
    
    private void depositColor(){
        if(env.getMapObjectInPosition(positionX, positionY)==Environment.CONTAINER){
            //LLAMAR A METODO EN CONTAINER PARA METER COLOR
            colorAmount = 0;
            currentColor = COLORNONE;
        }
        else{
            //FAIL
        }
    }
    
    //Fin Acciones planeables
    
    //Otras Acciones 
    
    /**
     * Intentar cambiar de direccion y avanzar para evadir un obstaculo
     * @return true si pudo evadirlo, false si esta encerrado
     */
    public boolean avoidObstacle(){
        for(int i =0; i<3; i++){
            turnLeft();
            if(prox.senseDistance()>1){
                moveForward(); //quiza esto no vaya
                return true;
            }
        }
        turnLeft();
        return false;
    }
    
    public int testProximitySensor(){
        return prox.senseDistance();
    }
    
    public void interpretMessage(Message msg){
        //TO DO logica de interpretacion de mensaje
    }
    
    //Fin Otras Acciones
    
    //Comunicacion
    /**
     * 
     * @param msg mensaje a enviar
     * @return true si se envio el mensaje, false si fallo el envio
     */
    private boolean sendMessage(Message msg){
        if(msg.getReceiverId() == -1){
            msgSvr.broadcastMessage(msg);
            return true;
        }
        else{
            return msgSvr.sendMessage(msg);
        }
    }
    
    public void receiveMessage(Message msg){
        interpretMessage(msg);
    }
    
    //Comunicacion
   
}
