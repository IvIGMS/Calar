/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author ivanfriasgil
 */
public class CalarFrame extends JFrame{
       public CalarFrame(){
           this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           this.setTitle("Calar");
           this.setBackground(new Color(0, 31, 255));
           this.setResizable(false);
           this.setSize(800, 600);
           this.setVisible(true);
           this.setIconImage(new ImageIcon("tw-logo.png").getImage());
       }
}
