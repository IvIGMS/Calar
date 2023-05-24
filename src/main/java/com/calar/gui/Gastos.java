package com.calar.gui;
import com.calar.logic.User;
import com.calar.persistence.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Gastos extends JFrame {

    private JPanel panel;
    private Map<Integer, String[]> gastos = new HashMap<Integer, String[]>();
    private JButton exitButton;
    private User user;

    public Gastos(User user) {
        this.user = user;
        // Configuración del JFrame
        setTitle("Gastos proveedores");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1080, 800);
        setResizable(false);
        
        gastos = ConnectionDB.getGastos(user.getEmail());  
        
        
        // Creación del panel y configuración de la cuadrícula
        panel = new JPanel(new GridLayout(0, 1, 10, 10)); // 0 indica número de filas dinámicas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un objeto Font para fijar el tamaño de los JLabel
        Font font = new Font("Arial", Font.PLAIN, 24);
        Font font2 = new Font("Arial", Font.PLAIN, 18);

        // Agregar cada fruta a un panel con GridLayout (3,1) y agregarlo al panel principal
        for (Map.Entry<Integer, String[]> gastos : gastos.entrySet()) {

            String id_gastos = String.valueOf(gastos.getKey());
            String[] values = gastos.getValue();
            
            // Panel
            JPanel prodPanel = new JPanel(new GridLayout(0,1, 10, 10));
            prodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            // ####### id
            
            JLabel id_ = new JLabel(id_gastos);
            id_.setHorizontalAlignment(JLabel.CENTER);
            id_.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(id_);
            
            // ####### CONCEPTO
            
            JLabel concepto = new JLabel("Concepto: " + values[3]);
            concepto.setHorizontalAlignment(JLabel.CENTER);
            concepto.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(concepto);
            
            // ####### CANTIDAD
            
            JLabel cantidad = new JLabel("Cantidad: " + values[1]);
            cantidad.setHorizontalAlignment(JLabel.CENTER);
            cantidad.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(cantidad);
            
            // ####### COSTE
            
            JLabel coste = new JLabel("Coste: " + values[2] +"€");
            coste.setHorizontalAlignment(JLabel.CENTER);
            coste.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(coste);
            
            // ####### FECHA
            
            JLabel date = new JLabel(values[4]);
            date.setHorizontalAlignment(JLabel.CENTER);
            date.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(date);
            
            
            panel.add(prodPanel);
        }

        // Creación del JScrollPane y agregación del panel al mismo
        JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Creación del botón "Salir" y configuración del ActionListener
        exitButton = new JButton("Salir");
        exitButton.setSize(200,100);
        exitButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        exitButton.addActionListener(e -> {
            // CODE - Cerrar la ventana.
            dispose();
        });
        
        // Agregar los botones al panel y al sur del JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el JScrollPane al JFrame
        add(scrollPane);

        // Mostrar el JFrame
        setVisible(true);
    }
}