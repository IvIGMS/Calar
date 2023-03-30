/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.persistence;

import com.calar.logic.User;
import java.sql.*;

/**
 *
 * @author ivanfriasgil
 */
public class ConnectionDB {
    private static final String URL_DB = "jdbc:mysql://localhost/calar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USUARIO_DB = "ivan@frias";
    private static final String PASSWORD_DB = "$tcabberR1";
    
    public static void testConnection() {
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
    }
    
    public static void createUser(User user){
        // Introducir un user previamente validado a la bbdd.
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into user VALUES (\""+ user.getEmail() +
                    "\", \""+ user.getName() +"\", \""+ user.getSurName() +
                    "\", \""+ user.getPassword() +"\");";
            stmt.executeUpdate(sql);      
            System.out.println("La query funciona.");
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
    }
    
    public static boolean login(String email_, String password_){
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from calar.user where mail = '" + email_ + "'");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            // Cogemos la primera tupla de que nos arroja la query.
            resultado.next();
            String email = resultado.getString(1);
            String password = resultado.getString(4);
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.
            
            // Si el user y el passord coinciden con un registro de nuestra bbdd
            // Podemos entrar a la app.
            return email.equals(email_) && password.equals(password_);
            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
            return false;
        }
    }
    
    public static User getUser(String email_){
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from calar.user where mail = '" + email_ + "'");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            // Cogemos la primera tupla de que nos arroja la query.
            resultado.next();
            String email = resultado.getString(1);
            String name = resultado.getString(2);
            String surName = resultado.getString(3);
            String password = resultado.getString(4);
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.
            
            // Si el user y el passord coinciden con un registro de nuestra bbdd
            // Podemos entrar a la app.
            return new User(email, name, surName);
            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
            return new User("none","none","none");
        }
    }
}
