/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ivanfriasgil
 */
public class Validations {
    public static boolean validateEmail(String email){
        // Utilizamos una expresión regular para validar el formato del correo electrónico
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    public static boolean isDistinctEmail(String email){
        // ################################
        // Hay que crear esta funcionalidad
        // ################################
        return true;
    }
    
    public static boolean validateName(String name){
        System.out.println("Nombre validado");
        return name.length() >= 2 && name.length() <=50;
    }
    
    public static boolean validatePassword(String password){
        if (password.length() < 8) {
        return false;
    }
    boolean tieneMayuscula = false;
    boolean tieneMinuscula = false;
    boolean tieneNumero = false;
    for (int i = 0; i < password.length(); i++) {
        char c = password.charAt(i);
        if (Character.isUpperCase(c)) {
            tieneMayuscula = true;
        } else if (Character.isLowerCase(c)) {
            tieneMinuscula = true;
        } else if (Character.isDigit(c)) {
            tieneNumero = true;
        }
    }
    return tieneMayuscula && tieneMinuscula && tieneNumero;
    }
    
    public static boolean validateDoublePassword(String pass1, String pass2){
        return pass1.equals(pass2);
    }
    
    public static boolean validatePrice(String strNumero) {
        System.out.println("Precio del producto a añadir validado");
        try {
            float numero = Float.parseFloat(strNumero); // convierte el String a int
            return numero < 100; // devuelve true si el número es menor que 100, false en caso contrario
        } catch (NumberFormatException e) {
            return false; // devuelve false si el String no es un número válido
        }
    }

}
