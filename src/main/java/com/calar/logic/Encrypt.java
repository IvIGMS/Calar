/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.calar.logic;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ivanfriasgil
 */
public class Encrypt {

  public static String encryptPassword(String password) {
    byte[] hash = null;
      try {
          hash = MessageDigest.getInstance("SHA-256").
                  digest(password.getBytes(StandardCharsets.UTF_8));
      } catch (NoSuchAlgorithmException ex) {
          System.out.println("Fallo en el tipo de algoritmo.");
      }
    StringBuilder pass = new StringBuilder();
    for (byte b : hash) {
      pass.append(String.format("%02x", b));
    }
    return pass.toString();
  }
}
