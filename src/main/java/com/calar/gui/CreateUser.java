package com.calar.gui;

import com.calar.logic.Encrypt;
import com.calar.logic.User;
import com.calar.logic.Validations;
import com.calar.persistence.ConnectionDB;
import javax.swing.JOptionPane;

/**
 *
 * @author ivanfriasgil
 */
public class CreateUser extends javax.swing.JFrame {

    /**
     * Creates new form CreateUser
     */
    public CreateUser() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtWelcome = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        txtNombre = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        txtPassword1 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        txtPassword2 = new javax.swing.JLabel();
        password2 = new javax.swing.JPasswordField();
        btnCrearUsuario = new javax.swing.JButton();
        responseSuccess = new javax.swing.JLabel();
        responseEmail = new javax.swing.JLabel();
        responseName = new javax.swing.JLabel();
        responseSurName = new javax.swing.JLabel();
        responsePassword = new javax.swing.JLabel();
        responsePasswordDistinct = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(900, 500));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(61, 152, 233));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 500));

        jPanel2.setBackground(new java.awt.Color(79, 165, 241));

        txtWelcome.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        txtWelcome.setForeground(new java.awt.Color(255, 255, 255));
        txtWelcome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtWelcome.setText("Introduce tus datos");

        txtEmail.setForeground(new java.awt.Color(255, 255, 255));
        txtEmail.setText("Email:");

        email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        email.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        txtNombre.setForeground(new java.awt.Color(255, 255, 255));
        txtNombre.setText("Nombre:");
        txtNombre.setToolTipText("");

        nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        txtApellido.setForeground(new java.awt.Color(255, 255, 255));
        txtApellido.setText("Apellido:");
        txtApellido.setToolTipText("");

        apellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        apellido.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellidoActionPerformed(evt);
            }
        });

        txtPassword1.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword1.setText("Contraseña:");

        password1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password1ActionPerformed(evt);
            }
        });

        txtPassword2.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword2.setText("Repita Contraseña:");

        btnCrearUsuario.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        btnCrearUsuario.setText("CREATE USER");
        btnCrearUsuario.setActionCommand("");
        btnCrearUsuario.setAutoscrolls(true);
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        responseSuccess.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        responseSuccess.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtApellido)
                        .addGap(18, 18, 18)
                        .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPassword1)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtEmail)
                                    .addComponent(txtNombre))
                                .addGap(18, 18, 18))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtPassword2)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(nombre)
                                .addComponent(email)
                                .addComponent(password1)
                                .addComponent(password2, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                    .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(responseSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(txtWelcome)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPassword2))
                .addGap(18, 18, 18)
                .addComponent(btnCrearUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(responseSuccess)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        responseEmail.setForeground(new java.awt.Color(255, 255, 255));

        responseName.setForeground(new java.awt.Color(255, 255, 255));

        responseSurName.setForeground(new java.awt.Color(255, 255, 255));

        responsePassword.setForeground(new java.awt.Color(255, 255, 255));

        responsePasswordDistinct.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(responseEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responseSurName, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(responsePasswordDistinct, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(responseEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responseName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responseSurName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responsePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(responsePasswordDistinct, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 909, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        // TODO add your handling code here:
        // Funcionalidad para crear un usuario.
        if (validateAll()){
            // Borramos las responses.           
            String password1_ = new String(password1.getPassword());
            String passwordHash = Encrypt.encryptPassword(password1_);
            User user = new User(email.getText(), nombre.getText(), apellido.getText(), passwordHash);
            ConnectionDB.createUser(user);
            responseSuccess.setText("Usuario creado correctamente\nPuede iniciar sesión");
            JOptionPane.showMessageDialog(null, "Usuario creado correctamente\nPuede iniciar sesión");
            this.dispose();
        }
        
        
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed

    private void apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellidoActionPerformed

    private void password1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password1ActionPerformed

    private boolean validateAll(){
        int validator = 0;
        String password1_ = new String(password1.getPassword());
        String password2_ = new String(password2.getPassword());
        
        if (password1_.equals(password2_)){
            responsePasswordDistinct.setText("");
            // Validamos el email.
            if (Validations.validateEmail(email.getText())){
                System.out.println("Email correcto");
                validator++;
                responseEmail.setText("");
                
                if (Validations.isDistinctEmail(email.getText())){
                    System.out.println("Email único");
                    validator++;
                } else {
                    responseEmail.setText("Email ya registrado");
                }
            } else {
                responseEmail.setText("Email incorrecto");
            }

            // Validamos el nombre.
            if (Validations.validateName(nombre.getText())){
                System.out.println("Nombre validado correctamente");
                validator++;
                responseName.setText("");
            } else {
                responseName.setText("Nombre incorrecto");
            }

            // Validamos el apellido.
            if (Validations.validateName(apellido.getText())){
                System.out.println("Apellido validado correctamente");
                validator++;
                responseSurName.setText("");
            } else {
                responseSurName.setText("Apellido incorrecto");
            }
            
            // Validamos el password.
            if (Validations.validatePassword(password1_)){
                System.out.println("Contraseña validada correctamente.");
                validator++;
                responsePassword.setText("");
            } else {
                responsePassword.setText("Contraseña incorrecta"); 
            }
            System.out.println("***************************");
            
        } else {
            responsePasswordDistinct.setText("Las contraseñas no son iguales");
        }
        
        // Si todo es corrector, validator es igual a 4, por lo tanto retorna true.
        return validator == 5;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellido;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JTextField email;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nombre;
    private javax.swing.JPasswordField password1;
    private javax.swing.JPasswordField password2;
    private javax.swing.JLabel responseEmail;
    private javax.swing.JLabel responseName;
    private javax.swing.JLabel responsePassword;
    private javax.swing.JLabel responsePasswordDistinct;
    private javax.swing.JLabel responseSuccess;
    private javax.swing.JLabel responseSurName;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtPassword1;
    private javax.swing.JLabel txtPassword2;
    private javax.swing.JLabel txtWelcome;
    // End of variables declaration//GEN-END:variables
}