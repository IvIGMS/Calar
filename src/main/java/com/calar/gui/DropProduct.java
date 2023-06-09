/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.calar.gui;

import com.calar.logic.Product;
import com.calar.logic.User;
import com.calar.logic.Validations;
import com.calar.persistence.ConnectionDB;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanfriasgil
 */
public class DropProduct extends javax.swing.JFrame {
    
    private User user;
    private Product product;
    /**
     * Creates new form AddProduct
     */
    public DropProduct(User user) {
        this.user = user;
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        nombre = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        drop = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nombre.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        nombre.setText("Nombre");

        nombreField.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        nombreField.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        drop.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        drop.setText("Eliminar");
        drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropActionPerformed(evt);
            }
        });

        salir.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(65, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(drop, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(nombre)))
                .addGap(71, 71, 71))
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(drop, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_salirActionPerformed

    private void dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropActionPerformed
        // TODO add your handling code here:
        String nombreProducto = nombreField.getText();
        String user_idProducto = user.getEmail();
     
        // VALIDAMOS NOMBRE Y PRECIO
        if (Validations.validateName(nombreProducto) ){
            
            boolean exists;
            
            exists = ConnectionDB.dropProduct(nombreProducto, user_idProducto);
            
            if (exists){
                ImageIcon imagenOriginal = new ImageIcon("images/delete.png");
                Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(imagenRedimensionada);
                JOptionPane.showMessageDialog(null, "El producto \"" + nombreProducto +"\" se ha elimnado correctamente", "Eliminar producto", JOptionPane.INFORMATION_MESSAGE, icon); 
            } else {
                System.out.println("No existe el producto introducido");
            }
            
                        
        } else {
            JOptionPane.showMessageDialog(null, "Error al introducir los datos.");
        }  
    }//GEN-LAST:event_dropActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton drop;
    private javax.swing.JLabel nombre;
    private javax.swing.JTextField nombreField;
    private javax.swing.JPanel panel;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
