/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.persistence;


import com.calar.logic.LineaFactura;
import com.calar.logic.Product;
import com.calar.logic.User;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanfriasgil
 */
public class ConnectionDB {
    /*
    private static final String URL_DB = "jdbc:mysql://localhost/calar?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USUARIO_DB = "ivan@frias";
    private static final String PASSWORD_DB = "$tcabberR1";
    */
    private static final String dbPath = System.getProperty("user.home") + "/calar.db";
    private static final String dbParameters = "jdbc:sqlite:" + dbPath;

    
    public static void testConnection() {
        try {
            Connection cn = DriverManager.getConnection(dbParameters);
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
            Connection cn = DriverManager.getConnection(dbParameters);
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from user where mail = '" + email_ + "'");
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from user where mail = '" + email_ + "'");
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from producto where user_id = \"" + email_ + "\" order by nombre_producto asc;");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            int contador = 0;
            
            while (resultado.next()) {
                // Recuperamos el nombre y precio de cada producto
                String nombre_producto = resultado.getString("nombre_producto");
                float precio = resultado.getFloat("precio");
                int existencias = resultado.getInt("existencias");
                
                productos.put(contador, new Object[]{nombre_producto, precio, existencias});
                contador ++;   
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return productos;
    }
    
    public static boolean addProduct(Product product){
        // Introducir un user previamente validado a la bbdd.
        boolean retorno = true;
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into producto VALUES (\"" + product.getNombre() + "\", \""+ product.getPrecio() + "\", 0, \""+ product.getUser_id() + "\");";
            stmt.executeUpdate(sql);      
            System.out.println("La query funciona.");
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Has introducido un producto ya existente.");
            retorno = false;
        }
        
        return retorno;
    }
    
    public static void dropProduct(String name, String email){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "DELETE FROM producto WHERE nombre_producto = \"" + name + "\" AND user_id = \"" + email + "\";";
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select * from greeting;");
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
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into factura (user_id, total_cost) VALUES (\"" + user.getEmail() + "\",0.0);";
            stmt.executeUpdate(sql);      
            System.out.println("Nueva factura añadida.");
                    
            cn.close(); // Cerramos conexión.
            
            System.out.println("Estamos en el segundo try");
            cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente a la segunda");
            
            // Recuperamos el id de la factura
            sql = "SELECT * FROM factura ORDER BY id DESC LIMIT 1;";
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
            
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente.");
            
            // Recuperamos el id de la factura
            String sql = "SELECT * FROM factura ORDER BY id DESC LIMIT 1;";
            PreparedStatement statement = cn.prepareStatement(sql);
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            // Tratamos el resultado
            int id = -1;
            
            while (resultado.next()) {
                id = resultado.getInt("id"); 
            }
            
            cn.close();
            
            cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente.");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            sql = "delete from factura where id = " + id +";";
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select precio from producto where nombre_producto = \"" + nombreProducto + "\" and user_id = \"" + email + "\";");
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
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into linea_factura (factura_id, nombre_producto, email_producto, cantidad, total_cost) VALUES (" + lin.getId_factura() + ", \"" + lin.getN_producto() + "\", \"" + email + "\", " + lin.getCantidad() + ", " + lin.getPrecioTotal() + ");";
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select nombre_producto from producto where user_id = \"" + email_ + "\";");
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
            
            Connection cn = DriverManager.getConnection(dbParameters);
            
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "update factura set total_cost = " + costo_total + " where id = " + id + "";
            System.out.println(sql);
            stmt.executeUpdate(sql);      
            System.out.println("Precio total factura actualizado");
            
            cn.close(); // Cerramos conexión.
         
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualiar el coste de la factura.");
        }  
    }
    
    public static boolean dropFactura(int idFactura, User user){
        int id = -1;
        
        // Introducir un user previamente validado a la bbdd.
        try {
            
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente.");
            
            // Recuperamos el id de la factura
            String sql = "SELECT id from factura where id = " + idFactura + " and user_id = \"" + user.getEmail() + "\";";
            PreparedStatement statement = cn.prepareStatement(sql);
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            // Tratamos el resultado

            while (resultado.next()) {
                id = resultado.getInt("id"); 
            }
            
            cn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar factura.");
            return false;

        }
        
        if (id < 0){
            return false;
        } else {
            try {
            
            Connection cn2 = DriverManager.getConnection(dbParameters);

            // Operaciones:
            Statement stmt2 = cn2.createStatement();
            String sql = "delete from factura where id = " + id + "";
            stmt2.executeUpdate(sql);
            System.out.println("Factura eliminada");
            cn2.close(); // Cerramos conexión.
         
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al eliminar la factura.");
            }
            
            return true;
        }
    }
    
    public static int getIdLastFacture(){
        // Introducir un user previamente validado a la bbdd.
        try {
            
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente.");
            
            // Recuperamos el id de la factura
            String sql = "SELECT id FROM factura ORDER BY id DESC LIMIT 1;";
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
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select id, total_cost from factura where user_id = \"" + email_ + "\" order by date asc;");
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
    
    public static String getDateFactura(int id_){
        String fechaString = "none";
        try {
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select date from factura where id = \"" + id_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            while (resultado.next()) {
                // Recuperamos la fecha que queremos
                Timestamp fecha = resultado.getTimestamp("date"); // Utilizamos getTimestamp() para obtener la fecha y hora
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Definimos el patrón de formato deseado
                fechaString = formatter.format(fecha); 
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.
            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return fechaString;
    }
    
    public static Map<Integer, String[]> getLineasFactura(String email_){
        
        Map<Integer, String[]> lineas = new HashMap<Integer, String[]>();
        
        try {
            Connection cn = DriverManager.getConnection(dbParameters);         
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
    
    public static void addGasto(String user_id, int cantidad, float coste, String concepto){
        // Introducir un user previamente validado a la bbdd.
        try {
            System.out.println("Estamos en el try");
            Connection cn = DriverManager.getConnection(dbParameters);
            System.out.println("Se ha conectado correctamente");
            
            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "insert into gasto (user_id, cantidad, coste, concepto) VALUES (\"" + user_id + "\", \""+ cantidad + "\", \""+ coste + "\", \""+ concepto + "\");";
            stmt.executeUpdate(sql);      
            cn.close(); // Cerramos conexión.
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Fallo");
        }
    }
    
    public static void updateProductExistencias(String email_, String nombreProducto, int cantidad){
            try {
            
            Connection cn = DriverManager.getConnection(dbParameters);

            // Operaciones:
            Statement stmt = cn.createStatement();
            String sql = "UPDATE producto SET existencias = existencias + " + cantidad + " WHERE nombre_producto = \"" + nombreProducto + "\" and user_id = \"" + email_ + "\";";
            System.out.println(sql);
            stmt.executeUpdate(sql);      
            System.out.println("Existencias del producto actualizadas");
            
            cn.close(); // Cerramos conexión.
         
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al actualiar las existencias del producto");
            } 
    }
    
    public static boolean checkProductExists(String nombreProducto, String email) {
        try {
            Connection cn = DriverManager.getConnection(dbParameters);
            Statement stmt = cn.createStatement();
            String sql = "SELECT * FROM producto WHERE nombre_producto = \"" + nombreProducto + "\" and user_id = \"" + email + "\";";
            ResultSet rs = stmt.executeQuery(sql);
            boolean exists = rs.next(); // Comprobamos si hay alguna fila en el ResultSet
            cn.close(); // Cerramos la conexión
            return exists;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false; // En caso de error, devolvemos false
        }
    }
    
    public static Map<Integer, String[]> getGastos(String email_){
        
        Map<Integer, String[]> productos = new HashMap<Integer, String[]>();
        
        try {
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("Select id, cantidad, coste, concepto, date from gasto where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            
            int contador = 0;
            
            while (resultado.next()) {
                // Recuperamos el id y los campos de la tabla gasto.
                int id = resultado.getInt("id");
                int cantidad = resultado.getInt("cantidad");
                float coste = resultado.getFloat("coste");
                String concepto = resultado.getString("concepto");
                
                String date = resultado.getString("date");
                
                
                String idStr = String.valueOf(id);
                String cantidadStr = String.valueOf(cantidad);
                String costeStr = String.valueOf(coste);
                
                productos.put(contador, new String[]{idStr, cantidadStr, costeStr, concepto, date});
                contador ++;   
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.         

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return productos;
    }
    
    public static float calcBalance(String email_){
        float ingresoTotal = 0;
        float gastoTotal = 0;
        
        try {
            Connection cn = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement = cn.prepareStatement("select sum(total_cost) AS total from factura where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado = statement.executeQuery();
            
            while (resultado.next()) {
                // Recuperamos el id y los campos de la tabla gasto.
                ingresoTotal = resultado.getFloat("total");
            }
            
            resultado.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.
            
            Connection cn1 = DriverManager.getConnection(dbParameters);         
            // Operaciones:
            PreparedStatement statement1 = cn1.prepareStatement("select sum(coste) AS total from gasto where user_id = \"" + email_ + "\";");
            // Guardamos la query en resultado
            ResultSet resultado1 = statement1.executeQuery();
            
            while (resultado1.next()) {
                // Recuperamos el id y los campos de la tabla gasto.
                gastoTotal = resultado1.getFloat("total");
            }
            
            resultado1.close(); // Cerramos el resultSet
            cn.close(); // Cerramos conexión.  

            
        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la bbdd" + ex.getMessage());
        }
                 
        return ingresoTotal - gastoTotal;
    }
}

