/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyectofinalmultiagentes;

import State.Coord2D;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Daniel
 */
public class Interfaz extends javax.swing.JFrame {
    
    private JLabel[][] labels;
    
    
    public static final Color Vacio = Color.WHITE;
    public static final Color Gris = new Color(62, 62, 62);
    public static final Color Rojo = new Color(211, 83, 83);
    public static final Color Verde = new Color(129, 212, 127);
    public static final Color Azul = new Color(127, 139, 212);
    public static final Color Obstaculo = new Color(62, 62, 62);
    
    public static final ImageIcon Agent = new ImageIcon("agent.png");
    public static final ImageIcon Fuente = new ImageIcon("fuente.png");
    public static final ImageIcon Contenedor = new ImageIcon("container.png");
    
    private int rows, cols;
    
    public Interfaz(int rows, int cols) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        
        
        this.rows = rows;
        this.cols = cols;
        
        setSize(100*cols, 100*rows);
        
        labels = new JLabel[rows][cols];
        GridLayout myGrid = new GridLayout(rows, cols);
        this.setLayout(myGrid);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                JLabel label = new JLabel();
                labels[row][col] = label;
                label.setOpaque(true);
                label.setBackground(Vacio);
                label.setBorder(BorderFactory.createLineBorder(Gris));
                this.add(labels[row][col]);
            }
        }
        
    }
    
    public synchronized void clean(Coord2D pos){
        if(pos.getX()<cols && pos.getY()<rows){
            JLabel label = labels[pos.getY()][pos.getX()];
            label.setBackground(Vacio);
            label.setIcon(null);
        }
    }
    
    public synchronized void setTile(Coord2D pos, Color color, Icon icon){
        if(pos.getX()<cols && pos.getY()<rows){
            JLabel label = labels[pos.getY()][pos.getX()];
            label.setBackground(color);
            label.setIcon(icon);
        }
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Interfaz in = new Interfaz(8,8);
                in.setVisible(true);
                in.setTile(new Coord2D(0, 0), Verde, null);
                in.setTile(new Coord2D(1, 1), Verde, null);
                in.clean(new Coord2D(1,1));
                in.setTile(new Coord2D(1, 1), Rojo, Agent);
                in.setTile(new Coord2D(2, 2), Azul, Fuente);
                in.setTile(new Coord2D(3, 3), Verde, Contenedor);
                in.setTile(new Coord2D(4, 4), Gris, null);
            }
        });
    }
}
