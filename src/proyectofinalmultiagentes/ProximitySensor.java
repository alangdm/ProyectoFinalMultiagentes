/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

/**
 *
 * @author Alan
 */
public class ProximitySensor {
    private Agent owner;

    public ProximitySensor(Agent owner) {
        this.owner = owner;
    }
    
    /**
     * Checar la distancia al objeto que pueda estorbar mas cercano
     * @return la distancia al objeto o pared mas cercana, -1 si la orientacion es invalida
     */
    public int senseDistance(){
        int distance =1;
        Environment env = owner.getEnv();
        int originalX = owner.getPositionX();
        int originalY = owner.getPositionY();
        switch(owner.getOrientation()){
            case "up":
                for(int i = originalY-1;i>=0;i--){
                    char objectInI = env.getMapObjectInPosition(originalX, i);
                    char agentInI = env.getAgentInPostition(originalX, i);
                    if(objectInI==Environment.OBSTACLE || agentInI==Environment.AGENT){
                        break;
                    }
                    else{
                        distance++;
                    }
                }
                return distance;
            case "down":
                for(int i = originalY+1;i<env.getMapSizeY();i++){
                    char objectInI = env.getMapObjectInPosition(originalX, i);
                    char agentInI = env.getAgentInPostition(originalX, i);
                    if(objectInI==Environment.OBSTACLE || agentInI==Environment.AGENT){
                        break;
                    }
                    else{
                        distance++;
                    }
                }
                return distance;
            case "left":
                for(int i = originalX-1;i>=0;i--){
                    char objectInI = env.getMapObjectInPosition(i, originalY);
                    char agentInI = env.getAgentInPostition(i, originalY);
                    if(objectInI==Environment.OBSTACLE || agentInI==Environment.AGENT){
                        break;
                    }
                    else{
                        distance++;
                    }
                }
                return distance;
            case "right":
                for(int i = originalX+1;i<env.getMapSizeX();i++){
                    char objectInI = env.getMapObjectInPosition(i, originalY);
                    char agentInI = env.getAgentInPostition(i, originalY);
                    if(objectInI==Environment.OBSTACLE || agentInI==Environment.AGENT){
                        break;
                    }
                    else{
                        distance++;
                    }
                }
                return distance;
            default:
                return -1;
        }
    }
}
