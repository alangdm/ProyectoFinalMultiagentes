/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

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
    public static final char AGENT = 1;
    /**
     * El map se toma como que 0,0 es la esquina superior izquierda, visualmente hablando, x va de izquierda a derecha y y de arriba a abajo
     */
    private char[][] map;
    private char[][] agentsmap;
    private int mapSizeX;
    private int mapSizeY;

    public Environment(char[][] map) {
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
    
    public void setAgentInPosition(int x, int y){
        agentsmap[x][y] = AGENT;
    }
    
    public void removeAgentFromPosition(int x, int y){
        agentsmap[x][y] = EMPTY;
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

