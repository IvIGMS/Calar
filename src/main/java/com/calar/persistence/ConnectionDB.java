/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.persistence;


import com.calar.logic.LineaFactura;
import com.calar.logic.Product;
import com.calar.logic.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

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
            System.out.println("Error al conectarse a la bbdd. " + ex.getMessage());
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
    
    public static Map<Integer, Object[]> getProducts(String email_){
        
        Map<Integer, Object[]> productos = new HashMap<Integer, Object[]>();
        
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from calar.producto where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            int contador = 0;
            
            while (resultado.next()) {
                // Recuperamos el nombre y precio de cada producto
                String nombre_producto = resultado.getString("nombre_producto");
                float precio = resultado.getFloat("precio");
                
                productos.put(contador, new Object[]{nombre_producto, precio});
                contador ++;   
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return productos;
    }
    
    public static void addProduct(Product product){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into calar.producto VALUES (\"" + product.getNombre() + "\", \""+ product.getPrecio() + "\", \""+ product.getUser_id() + "\");";
            stmt.executeUpdate(sql);      
            System.out.println("La query funciona.");
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Has introducido un producto ya existente.");
        }
    }
    
    public static void dropProduct(String name, String email){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "DELETE FROM calar.producto WHERE nombre_producto = \"" + name + "\" AND user_id = \"" + email + "\";";
            stmt.executeUpdate(sql);      
            System.out.println("El producto se ha eliminado correctamente");
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Has introducido un producto que no existe");
        }
    }
    
    public static ArrayList<String> getGreeting(){
        
        ArrayList<String> lista = new ArrayList<String>();
        
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from calar.greeting;");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            while (resultado.next()) {
                lista.add(resultado.getString("text")); 
            }

            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.
            
            return lista;
            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
            return lista;
        }
    }
    // ####################
    // IMPLEMENTAR
    // ####################
    public static int addFactura(User user){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into calar.factura (user_id, total_cost) VALUES (\"" + user.getEmail() + "\",0.0);";
            stmt.executeUpdate(sql);      
            System.out.println("Nueva factura añadida.");
                    
            cn.close(); // Cerramos conexión.
            
            System.out.println("Estamos en el segundo try");
            cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente a la segunda");
            
            // Recuperamos el id de la factura
            sql = "SELECT * FROM calar.factura ORDER BY id DESC LIMIT 1;";
            PreparedStatement statement = cn.prepareStatement(sql);
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            // Tratamos el resultado
            int id = -1;
            System.out.println("Este es mi id");
            while (resultado.next()) {
                id = resultado.getInt("id"); 
            }
            
            System.out.println("Justo antes de cerrar la segunda conexion");
            
            cn.close();
            
            return id;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear la factura");
            return -1;
        }  
    }
    
    public static void dropLastFacture(){
        // Introducir un user previamente validado a la bbdd.
        try {
            
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente.");
            
            // Recuperamos el id de la factura
            String sql = "SELECT * FROM calar.factura ORDER BY id DESC LIMIT 1;";
            PreparedStatement statement = cn.prepareStatement(sql);
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            // Tratamos el resultado
            int id = -1;
            
            while (resultado.next()) {
                id = resultado.getInt("id"); 
            }
            
            cn.close();
            
            cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente.");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            sql = "delete from calar.factura where id = " + id +";";
            stmt.executeUpdate(sql);      
            System.out.println("La factura se ha eliminado correctamente.");
            cn.close(); // Cerramos conexión.

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear la factura");
        }  
    }
    
    public static float getPriceProduct(String nombreProducto, String email){
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select precio from calar.producto where nombre_producto = \"" + nombreProducto + "\" and user_id = \"" + email + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            float precio = 0;
            
            while (resultado.next()) {
                // Recuperamos el precio del producto
                precio = resultado.getFloat("precio");
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         
            return precio;
            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
            return 0;
        }
    }
    
    public static void insertarLineaFactura(LineaFactura lin, String email){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Comienza linea factura");
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into calar.linea_factura (factura_id, nombre_producto, email_producto, cantidad, total_cost) VALUES (" + lin.getId_factura() + ", \"" + lin.getN_producto() + "\", \"" + email + "\", " + lin.getCantidad() + ", " + lin.getPrecioTotal() + ");";
            System.out.println(sql);
            stmt.executeUpdate(sql);      
            System.out.println("Nueva linea de factura añadida.");
            
            cn.close(); // Cerramos conexión.
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear la factura");
        }  
    }
    
    public static ArrayList<String> getNameProducts(String email_){
        
        ArrayList<String> products = new ArrayList<String>();
        
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select nombre_producto from calar.producto where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            
            
            while (resultado.next()) {
                // Recuperamos el nombre y precio de cada producto
                String nombre_producto = resultado.getString("nombre_producto");
                products.add(nombre_producto);
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return products;
    }
    
    public static void updatePrecioFactura(float costo_total, int id){
        // Introducir un user previamente validado a la bbdd.
        try {
            
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "update calar.factura set total_cost = " + costo_total + " where id = " + id + "";
            System.out.println(sql);
            stmt.executeUpdate(sql);      
            System.out.println("Precio total factura actualizado");
            
            cn.close(); // Cerramos conexión.
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualiar el coste de la factura.");
        }  
    }
    
    public static int getIdLastFacture(){
        // Introducir un user previamente validado a la bbdd.
        try {
            
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);
            System.out.println("Se ha conectado correctamente.");
            
            // Recuperamos el id de la factura
            String sql = "SELECT id FROM calar.factura ORDER BY id DESC LIMIT 1;";
            PreparedStatement statement = cn.prepareStatement(sql);
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            // Tratamos el resultado
            int id = -1;
            
            while (resultado.next()) {
                id = resultado.getInt("id"); 
            }
            
            cn.close();
            
            return id;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al crear la factura");
            return -1;
        }  
    }
    
    public static Map<Integer, Float> getFacturas(String email_){
        
        Map<Integer, Float> productos = new HashMap<Integer, Float>();
        
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select id, total_cost from calar.factura where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            while (resultado.next()) {
                // Recuperamos el nombre y precio de cada producto
                int id = resultado.getInt("id");
                float total_cost = resultado.getFloat("total_cost");
                
                productos.put(id, total_cost);
  
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return productos;
    }
    
    public static Map<Integer, String[]> getLineasFactura(String email_){
        
        Map<Integer, String[]> lineas = new HashMap<Integer, String[]>();
        
        try {
            Connection cn = DriverManager.getConnection(URL_DB, USUARIO_DB, PASSWORD_DB);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select factura_id, nombre_producto, cantidad, total_cost from calar.linea_factura where email_producto = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            int contador = 0;
            
            while (resultado.next()) {
                // Recuperamos el nombre y precio de cada producto
                String factura_id = resultado.getString("factura_id");
                String nombre_producto = resultado.getString("nombre_producto");
                String cantidad = resultado.getString("cantidad");
                String precioSum = resultado.getString("total_cost");
                
                // Usamos key (id), value (linea_entera)
                lineas.put(contador, new String[]{factura_id, nombre_producto, cantidad, precioSum});
                contador ++;   
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return lineas;
    }
    
}
