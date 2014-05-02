/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import State.Coord2D;
import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author Alan
 */
public class Environment {
    public static final char EMPTY = 0;
    public static final char OBSTACLE = 1;
    public static final char CONTAINER = 2;
    public static final char REDSOURCE = 3;
    public static final char GREENSOURCE = 4;
    public static final char BLUESOURCE = 5;
    //public static final char AGENT = 1;
    /**
     * El map se toma como que 0,0 es la esquina superior izquierda, visualmente hablando, x va de izquierda a derecha y y de arriba a abajo
     */
    private char[][] map;
    private char[][] agentsmap;
    private int mapSizeX;
    private int mapSizeY;
    private Container container;
    private Source redSource;
    private Source greenSource;
    private Source blueSource;

    public Environment(char[][] map, Container container, Source redSource, Source greenSource, Source blueSource) {
        if(map.length >0){
            if(map[0].length>0){ //FALTA checar que todos los renglones del mapa sean del mismo tamano
                this.map = map;
                mapSizeX = map.length;
                mapSizeY = map[0].length;
                agentsmap = new char[mapSizeX][mapSizeY];
            }
            else{
                //ERROR mapa no puede tener 0 en una de sus dimensiones
            }
        }
        else{
            //ERROR mapa no puede tener 0 en una de sus dimensiones
        }
        this.container = container;
        map[container.getX()][container.getY()] = CONTAINER;
        this.redSource = redSource;
        map[redSource.getX()][redSource.getY()] = REDSOURCE;
        this.blueSource = blueSource;
        map[blueSource.getX()][blueSource.getY()] = BLUESOURCE;
        this.greenSource = greenSource;
        map[greenSource.getX()][greenSource.getY()] = GREENSOURCE;
    }
    
    public char[][] getMapCopy(){
        char[][] copy = new char[mapSizeX][mapSizeY];
        for(int i=0;i<mapSizeX;i++){
            copy[i] = Arrays.copyOf(map[i], mapSizeY);
        }
        return copy;
    }
    
    public char[][] getAgentMapCopy(){
        char[][] copy = new char[mapSizeX][mapSizeY];
        for(int i=0;i<mapSizeX;i++){
            copy[i] = Arrays.copyOf(agentsmap[i], mapSizeY);
        }
        return copy;
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }
    
    public char getMapObjectInPosition(int x, int y) {
        return map[x][y];
    }
    
    public char getAgentInPostition(int x, int y){
        return agentsmap[x][y];
    }
    
    public void setAgentInPosition(int x, int y, int id){
        agentsmap[x][y] = (char)id;
    }
    
    public void removeAgentFromPosition(int x, int y){
        agentsmap[x][y] = EMPTY;
    }
    
    public void depositColorInContainer(int color, int amount){
        container.depositColor(color, amount);
    }
    
    public Color getContainerColor(){
        return container.getContainerColor();
    }
    
    public Coord2D getContainerPosition(){
        return container.getPosition();
    }
    
    public Coord2D getRedSourcePosition(){
        return redSource.getPosition();
    }
    
    public Coord2D getGreenSourcePosition(){
        return greenSource.getPosition();
    }
    
    public Coord2D getBlueSourcePosition(){
        return blueSource.getPosition();
    }

    @Override
    public String toString() {
        return "Environment{" + "\n\tmap=\n" + mapToString(map) + "\n\tagentsmap=\n" + mapToString(agentsmap) + "\tmapSizeX=" + mapSizeX + "\n\tmapSizeY=" + mapSizeY + "\n}";
    }
    
    private String mapToString(char[][] originMap){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<mapSizeY;i++){
            res.append('[');
            for(int j=0;j<mapSizeX;j++){
                res.append((int)originMap[j][i]);
                if(j<mapSizeX-1){
                    res.append(',');
                }
            }
            res.append("]\n");
        }
        return res.toString();
    }
    
    
}

