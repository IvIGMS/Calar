/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ivanfriasgil
 */
public class ConnectionDB {
    
    public static void conect() {
        // Credenciales
        String url = "jdbc:mysql://localhost:3306/calar";
        String usuario = "ivan@frias";
        String password = "$tcabberR1";
        
        try {
            Connection cn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
    }
}
