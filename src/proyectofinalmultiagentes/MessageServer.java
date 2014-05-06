/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import java.util.TreeMap;

/**
 *
 * @author Alan
 */
public class MessageServer {
    private TreeMap<Integer,Agent> agents;

    public MessageServer(TreeMap<Integer,Agent> agents) {
        this.agents = agents;
    }
    
    public MessageServer(){
        this.agents = new TreeMap<>();
    }
    
    public void addAgent(Agent agent){
        agents.put(agent.getId(), agent);
    }
    
    public void removeAgent(Agent agent){
        agents.remove(agent.getId());
    }
    
    public Agent getAgent(int id){
        return agents.get(id);
    }
    
    public boolean sendMessage(Message msg){
        int receiverId = msg.getReceiverId();
        Agent receiver = agents.get(receiverId);
        if(receiver==null){
            return false;
        }
        else{
            receiver.receiveMessage(msg);
            return true;
        }
    }
    
    public void broadcastMessage(Message msg){
        for(Agent a : agents.values()){
            if(a.getId() != msg.getSenderId())
                a.receiveMessage(msg);
        }
    }
    
}
