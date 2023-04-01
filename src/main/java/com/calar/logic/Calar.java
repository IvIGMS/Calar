/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.calar.logic;

import com.calar.gui.Login;
import com.calar.gui.Productos;

/**
 *
 * @author ivanfriasgil
 */
public class Calar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Vamos a llamar a nuestra pantalla de inicio, que en este caso es el login
        
        
        Login login = new Login();
        login.setVisible(true);
        login.setLocationRelativeTo(null);
        /*
        Productos productos = new Productos(new User("a","b","c"));
        productos.setVisible(true);
        productos.setLocationRelativeTo(null);
        */
    }
}
