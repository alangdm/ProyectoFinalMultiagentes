/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalmultiagentes;

import java.util.Arrays;

/**
 *
 * @author Alan
 */
public class Environment {
    public static final int EMPTY = 0;
    public static final int OBSTACLE = 1;
    public static final int AGENT = 2;
    /**
     * El map se toma como que 0,0 es la esquina superior izquierda, visualmente hablando, x va de izquierda a derecha y y de arriba a abajo
     */
    private int[][] map;
    private int mapSizeX;
    private int mapSizeY;

    public Environment(int[][] map) {
        if(map.length >0){
            if(map[0].length>0){ //FALTA checar que todos los renglones del mapa sean del mismo tamano
                this.map = map;
                mapSizeX = map.length;
                mapSizeY = map[0].length;
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
    
    public int getObjectInPosition(int x, int y) {
        return map[x][y];
    }
    
    public void setObjectInPosition(int x, int y, int object){
        if(map[x][y]==OBSTACLE){
            //ERROR no se pueden cambiar los obstaculos
        }
        else if (object == OBSTACLE){
            //ERROR no se pueden poner nuevos obstaculos
        }
        else{
            map[x][y] = object;
        }
    }

    @Override
    public String toString() {
        return "Environment{" + "\n\tmap=\n" + mapToString() + "\tmapSizeX=" + mapSizeX + "\n\tmapSizeY=" + mapSizeY + "\n}";
    }
    
    private String mapToString(){
        StringBuilder res=new StringBuilder();
        for(int i=0;i<mapSizeY;i++){
            res.append('[');
            for(int j=0;j<mapSizeX;j++){
                res.append(map[j][i]);
                if(j<mapSizeX-1){
                    res.append(',');
                }
            }
            res.append("]\n");
        }
        return res.toString();
    }
    
    
}

