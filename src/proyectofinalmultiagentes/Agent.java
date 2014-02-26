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
public class Agent {
    /**
     * orientation puede ser "up", "down", "left", "right", es case insentitive
     */
    private int id;
    private String orientation;
    private int positionX;
    private int positionY;
    private Environment env;
    private ProximitySensor prox;

    public Agent(int id, String orientation, int positionX, int positionY, Environment env) {
        this.id = id;
        this.orientation = orientation.toLowerCase();
        this.positionX = positionX;
        this.positionY = positionY;
        this.env = env;
        this.prox = new ProximitySensor(this);
        this.env.setObjectInPosition(positionX, positionY, Environment.AGENT);
    }

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
    
    public void executeAction(String action){
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
            default:
                //ERROR accion invalida
                break;
        }
    }
    
    //Acciones planeables
    
    public void turnLeft(){
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
    
    public void turnRight(){
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
    
    public void moveForward(){
        switch(orientation){
            case "up":
                if((positionY-1)>=0 && env.getObjectInPosition(positionX, positionY-1)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionY--;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "down":
                if((positionY+1)<env.getMapSizeY() && env.getObjectInPosition(positionX, positionY+1)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionY++;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "left":
                if((positionX-1)>=0 && env.getObjectInPosition(positionX-1, positionY)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionX--;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "right":
                if((positionX+1)<env.getMapSizeY() && env.getObjectInPosition(positionX+1, positionY)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionX++;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
        }
    }
    
    public void moveBackwards(){
        switch(orientation){
            case "up":
                if((positionY+1)<env.getMapSizeY() && env.getObjectInPosition(positionX, positionY+1)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionY++;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "down":
                if((positionY-1)>=0 && env.getObjectInPosition(positionX, positionY-1)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionY--;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "left":
                if((positionX+1)<env.getMapSizeY() && env.getObjectInPosition(positionX+1, positionY)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionX++;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
            case "right":
                if((positionX-1)>=0 && env.getObjectInPosition(positionX-1, positionY)== Environment.EMPTY){
                    env.setObjectInPosition(positionX, positionY, Environment.EMPTY);
                    positionX--;
                    env.setObjectInPosition(positionX, positionY, Environment.AGENT);
                }
                break;
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
    
    //Fin Otras Acciones
    
}
