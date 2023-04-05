package com.calar.gui;
import com.calar.logic.User;
import com.calar.persistence.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowProducts extends JFrame {

    private JPanel panel;
    private ArrayList<String> listaProductos;
    private JButton exitButton;
    private JButton addButton;
    private JButton refreshButton;
    private JButton dropButton;
    private User user;

    public ShowProducts(User user, AddFactura fact) {
        this.user = user;
        // Configuración del JFrame
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(fact);
        setSize(400, 720);
        setResizable(false);
        
        listaProductos = ConnectionDB.getNameProducts(user.getEmail());

        // Creación del panel y configuración de la cuadrícula
        panel = new JPanel(new GridLayout(0, 1, 10, 10)); // 0 indica número de filas dinámicas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un objeto Font para fijar el tamaño de los JLabel
        Font font = new Font("Arial", Font.PLAIN, 24);

        // Agregar cada fruta a un panel con GridLayout (3,1) y agregarlo al panel principal
        for (int i = 0; i < listaProductos.size(); i++) {
            
            JPanel prodPanel = new JPanel(new GridLayout(2,1));
            prodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            prodPanel.setPreferredSize(new Dimension(100, 70)); // Fijar el tamaño preferido del panel de la fruta

            JLabel nombreLabel = new JLabel(listaProductos.get(i));
            nombreLabel.setHorizontalAlignment(JLabel.CENTER);
            nombreLabel.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(nombreLabel);

            panel.add(prodPanel);
        }

        // Creación del JScrollPane y agregación del panel al mismo
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Creación del botón "Salir" y configuración del ActionListener
        exitButton = new JButton("Cerrar");
        exitButton.setSize(200,100);
        exitButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        exitButton.addActionListener(e -> {
            // CODE - Cerrar la ventana.
            dispose();
        });

        // Agregar el boton al panel y al sur del JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el JScrollPane al JFrame
        add(scrollPane);

        // Mostrar el JFrame
        setVisible(true);
    }
}