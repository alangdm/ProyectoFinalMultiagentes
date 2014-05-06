/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import State.Coord2D;
import State.State;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alan
 */
public class Agent implements Comparable<Agent> {
    
    public static final char COLORNONE = 0;
    public static final char COLORRED = 1;
    public static final char COLORGREEN = 2;
    public static final char COLORBLUE = 3;
    private static int nextId = 1;
    
    protected int id;
    /**
     * orientation puede ser "up", "down", "left", "right", es case insentitive
     */
    private String orientation;
    protected int positionX;
    protected int positionY;
    protected Environment env;
    private ProximitySensor prox;
    private MessageServer msgSvr;
    private int capacity;
    private char currentColor;
    private int colorAmount;
    private int amountToHarvest;
    private int objectiveRed;
    private int objectiveGreen;
    private int objectiveBlue;
    private int estimatedRed;
    private int estimatedGreen;
    private int estimatedBlue;
    private int currentRed;
    private int currentGreen;
    private int currentBlue;
    private ConcurrentLinkedQueue<Message> messageQueue;
    private Interfaz interfaz;

    public Agent(String orientation, int positionX, int positionY, Environment env, MessageServer msgSvr, int capacity, int objectiveRed, int objectiveGreen, int objectiveBlue, Interfaz interfaz) {
        this.id = nextId++;
        this.orientation = orientation.toLowerCase();
        this.positionX = positionX;
        this.positionY = positionY;
        this.env = env;
        this.prox = new ProximitySensor(this);
        this.env.setAgentInPosition(positionX, positionY, id);
        this.msgSvr = msgSvr;
        this.capacity = capacity;
        this.currentColor = COLORNONE;
        this.colorAmount = 0;
        this.amountToHarvest = 0;
        this.objectiveRed = objectiveRed;
        this.objectiveGreen = objectiveGreen;
        this.objectiveBlue = objectiveBlue;
        estimatedRed = estimatedGreen = estimatedBlue = 0;
        currentRed = currentGreen = currentBlue = 0;
        messageQueue = new ConcurrentLinkedQueue<>();
        this.interfaz = interfaz;
        setAgentInInteface();
    }
    
    public Agent(){}

    //Getters
    
    public String getOrientation() {
        return orientation;
    }

    public int getPositionX() {
        return positionX;
    }
    
    public void setPositionX(int positionX){
        this.positionX = positionX;
    }
    
    public void setPositionY(int positionY){
        this.positionY = positionY;
    }
    
    public void setAgentInInteface(){
        interfaz.setTile(new Coord2D(positionX,positionY), Interfaz.getAgentColor(currentColor), Interfaz.AGENT);
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
    
    public static void resetId(){
        nextId = 1;
    }
    
    public boolean getObjectiveAccomplished(){
        return currentBlue>=objectiveBlue && currentGreen>=objectiveGreen && currentRed>=objectiveRed;
    }
    
    public State getCurrentState(){
        Coord2D sourceCoord;
        Coord2D containerCoord = env.getContainerPosition();
        boolean carryingColor = colorAmount!=0;
        switch(currentColor){
            case COLORRED:
                sourceCoord = env.getRedSourcePosition();
                break;
            case COLORGREEN:
                sourceCoord = env.getGreenSourcePosition();
                break;
            case COLORBLUE:
                sourceCoord = env.getBlueSourcePosition();
                break;
            case COLORNONE:
            default:
                sourceCoord = new Coord2D(0,0);
                break;
        }
        sourceCoord=new Coord2D(sourceCoord.getY(),sourceCoord.getX());
        containerCoord = new Coord2D(containerCoord.getY(),containerCoord.getX());
        //return new State(new Coord2D(positionY,positionX), sourceCoord, carryingColor, env.getWalls(),containerCoord,carryingColor?containerCoord:sourceCoord);
        return new State(new Coord2D(positionY,positionX), sourceCoord, carryingColor, env.getWalls(),containerCoord,sourceCoord, false);
    }
    //End Getters
    
    //Overriden Methods
    
    @Override
    public int compareTo(Agent o) {
        return id-o.id;
    }
    
    //End Overriden Methods
    
    public void setOrientation(String orientation){
        switch(orientation.toLowerCase()){
            case "north":
                this.orientation = "up";
                break;
            case "south":
                this.orientation = "down";
                break;
            case "east":
                this.orientation = "right";
                break;
            case "west":
                this.orientation = "left";
                break;
        }
    }
    
    //Ejecutores de acciones
    
    public boolean chooseNewColor(){
        checkMessages();
        /*if(env.getContainerPosition().equals(new Coord2D(positionX, positionY))){
            while(prox.senseDistance()==1){
                turnLeft();
            }
            moveForward();
        }*/
        int remainingRed = objectiveRed-estimatedRed;
        remainingRed = (remainingRed<0)?0:remainingRed;
        int remainingBlue = objectiveBlue-estimatedBlue;
        remainingBlue = (remainingBlue<0)?0:remainingBlue;
        int remainingGreen = objectiveGreen-estimatedGreen;
        remainingGreen = (remainingGreen<0)?0:remainingGreen;
//        if((remainingRed+remainingBlue+remainingGreen)<=0 || getObjectiveAccomplished())
//            return false;
//        if(remainingRed>=remainingBlue){
//            if(remainingRed>=remainingGreen){
//                currentColor = COLORRED;
//                amountToHarvest = remainingRed;
//            }
//            else{
//                currentColor = COLORGREEN;
//                amountToHarvest = remainingGreen;
//            }
//        }
//        else if(remainingBlue>=remainingGreen){
//            currentColor = COLORBLUE;
//            amountToHarvest = remainingBlue;
//        }
//        else{
//            currentColor = COLORGREEN;
//            amountToHarvest = remainingGreen;
//        }
        switch (id) {
            case 1:
                currentColor = COLORRED;
                amountToHarvest = remainingRed;
                break;
            case 2:
                currentColor = COLORGREEN;
                amountToHarvest = remainingGreen;
                break;
            case 3:
                currentColor = COLORBLUE;
                amountToHarvest = remainingBlue;
                break;
            default:
                throw new AssertionError();
        }
        if (amountToHarvest<=0) {
            currentColor = COLORNONE;
            amountToHarvest = 0;
            return false;
        }
        Message colorChosen = new Message("tell","colorChosen",new MessageBody(currentColor,capacity<amountToHarvest?capacity:amountToHarvest),-1,id);  
        System.out.println("Color chosen color: " + (int)currentColor + " amount: "+(capacity<amountToHarvest?capacity:amountToHarvest)+" agent: " +id);
        sendMessage(colorChosen);
        return true;
    }
    
    public void executeListOfActions(List<String> actions){
        for(String action : actions){
            action = action.substring(3).toLowerCase();
            boolean notMovement = action.equals("pickup") || action.equals("putdown");
            setOrientation(action);
            /*if(!notMovement && prox.senseDistance()==1){
                if(avoidObstacle()){
                    //pedir replaneacion
                    System.out.println("Evadiiiiiir");
                }
                else{
                    //enviar mensaje de encierro
                    System.out.println("Estoy encerrado");
                }
                break;
            }
            else{*/
            
                System.out.println("accion["+id+"]: "+ action + " en [" + positionX + "][" + positionY + "]");
                executeAction(action); 
            try {
                Thread.sleep(100);
            
            } catch (InterruptedException ex) {
                Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
            }
            //}
        }
    }
    
    protected void executeAction(String action){
        switch(action.toLowerCase()){
            /*case "turnleft":
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
                break;*/
            //case "moveleft":
            case "west":
            //case "south":
                moveLeft();
                break;
            //case "moveright":
            case "east":
            //case "north":
                moveRight();
                break;
            //case "moveup":
            case "north":
            //case "east":
                moveUp();
                break;
            //case "movedown":
            case "south":
            //case "west":
                moveDown();
                break;
            //case "harvestcolor":
            case "pickup":
                harvestColor();
                break;
            //case "depositcolor":
            case "putdown":
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
                if((positionY-1)>=0 && env.getMapObjectInPosition(positionX, positionY-1)!= Environment.OBSTACLE){
                    env.removeAgentFromPosition(positionX, positionY);
                    interfaz.clean(new Coord2D(positionX,positionY));
                    positionY--;
                    env.setAgentInPosition(positionX, positionY, id);
                    setAgentInInteface();
                }
                break;
            case "down":
                if((positionY+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX, positionY+1)!= Environment.OBSTACLE){
                    env.removeAgentFromPosition(positionX, positionY);
                    interfaz.clean(new Coord2D(positionX,positionY));
                    positionY++;
                    env.setAgentInPosition(positionX, positionY, id);
                    setAgentInInteface();
                }
                break;
            case "left":
                if((positionX-1)>=0 && env.getMapObjectInPosition(positionX-1, positionY)!= Environment.OBSTACLE){
                    env.removeAgentFromPosition(positionX, positionY);
                    interfaz.clean(new Coord2D(positionX,positionY));
                    positionX--;
                    env.setAgentInPosition(positionX, positionY, id);
                    setAgentInInteface();
                }
                break;
            case "right":
                if((positionX+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX+1, positionY)!= Environment.OBSTACLE){
                    env.removeAgentFromPosition(positionX, positionY);
                    interfaz.clean(new Coord2D(positionX,positionY));
                    positionX++;
                    env.setAgentInPosition(positionX, positionY, id);
                    setAgentInInteface();
                }
                break;
        }
    }
    
    private void moveBackwards(){
        switch(orientation){
            case "up":
                if((positionY+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX, positionY+1)!= Environment.OBSTACLE && env.getAgentInPostition(positionX, positionY+1)==Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY++;
                    env.setAgentInPosition(positionX, positionY, id);
                }
                break;
            case "down":
                if((positionY-1)>=0 && env.getMapObjectInPosition(positionX, positionY-1)!= Environment.OBSTACLE && env.getAgentInPostition(positionX, positionY-1)==Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionY--;
                    env.setAgentInPosition(positionX, positionY, id);
                }
                break;
            case "left":
                if((positionX+1)<env.getMapSizeY() && env.getMapObjectInPosition(positionX+1, positionY)!= Environment.OBSTACLE && env.getAgentInPostition(positionX+1, positionY)==Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX++;
                    env.setAgentInPosition(positionX, positionY, id);
                }
                break;
            case "right":
                if((positionX-1)>=0 && env.getMapObjectInPosition(positionX-1, positionY)!= Environment.OBSTACLE && env.getAgentInPostition(positionX-1, positionY)==Environment.EMPTY){
                    env.removeAgentFromPosition(positionX, positionY);
                    positionX--;
                    env.setAgentInPosition(positionX, positionY, id);
                }
                break;
        }
    }
    
    private void moveLeft(){
        orientation = "left";
        moveForward();
    }
    
    private void moveUp(){
        orientation = "up";
        moveForward();
    }
    
    private void moveRight(){
        orientation = "right";
        moveForward();
    }
    
    private void moveDown(){
        orientation = "down";
        moveForward();
    }
    
    private void harvestColor(){
        //mandar mensaje a final de el if de cada case, despues del += de cada estimated
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
                    estimatedRed+=colorAmount;
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
                    estimatedGreen+=colorAmount;
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
                    estimatedBlue+=colorAmount;
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
        if(env.getMapObjectInPosition(positionX, positionY)==Environment.CONTAINER || colorAmount!=0){
            env.depositColorInContainer(currentColor, colorAmount);
            env.showSourcesAndContainers();
            switch(currentColor){
                case COLORRED:
                    currentRed+=colorAmount;
                    break;
                case COLORGREEN:
                    currentGreen+=colorAmount;
                    break;
                case COLORBLUE:
                    currentBlue+=colorAmount;
                    break;
            }
            Message colorDeposited = new Message("tell","colorDeposited",new MessageBody(currentColor,colorAmount),-1,id);  
            System.out.println("Color Deposited color: "+(int)currentColor+" amount: "+colorAmount + " id: "+id);
            sendMessage(colorDeposited);
        }
        else{
            switch(currentColor){
                case COLORRED:
                    estimatedRed-=colorAmount;
                    break;
                case COLORGREEN:
                    estimatedGreen-=colorAmount;
                    break;
                case COLORBLUE:
                    estimatedBlue-=colorAmount;
                    break;
            }
            Message colorDeposited = new Message("tell","colorDeposited",new MessageBody(currentColor,colorAmount==0?0:-colorAmount),-1,id);  
            sendMessage(colorDeposited);
        }
        colorAmount = 0;
        currentColor = COLORNONE;
    }
    
    //Fin Acciones planeables
    
    //Otras Acciones 
    
    /**
     * Intentar cambiar de direccion y avanzar para evadir un obstaculo
     * @return true si pudo evadirlo, false si esta encerrado
     */
    public boolean avoidObstacle(){
        String prevOrientation = orientation;
        for(int i =0; i<3; i++){
            turnLeft();
            if(prox.senseDistance()>1){
                moveForward();
                try {
                    Thread.sleep((long) (Math.random()*300));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
                }
                turnLeft();
                turnLeft();
                moveForward();
                orientation=prevOrientation;
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
        messageQueue.offer(msg);
    }
    
    public void checkMessages() {
        while(messageQueue.peek()!=null){
        //for (Message m : messageQueue) {
            Message m = messageQueue.poll();
            if (m.getType().equals("colorChosen")) {
                MessageBody body = (MessageBody) m.getContent();
                switch (body.getColor()) {
                    case COLORRED:
                        estimatedRed += body.getAmount();
                        break;
                    case COLORGREEN:
                        estimatedGreen += body.getAmount();
                        break;
                    case COLORBLUE:
                        estimatedBlue += body.getAmount();
                        break;
                }
            }
            if (m.getType().equals("colorDeposited")) {
                MessageBody body = (MessageBody) m.getContent();
                switch (body.getColor()) {
                    case COLORRED:
                        currentRed += body.getAmount();
                        break;
                    case COLORGREEN:
                        currentGreen += body.getAmount();
                        break;
                    case COLORBLUE:
                        currentBlue += body.getAmount();
                        break;
                }
            }
        }
    }
    //Comunicacion
   
}
