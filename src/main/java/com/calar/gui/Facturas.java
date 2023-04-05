package com.calar.gui;
import com.calar.logic.User;
import com.calar.persistence.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Facturas extends JFrame {

    private JPanel panel;
    private Map<Integer, Float> facturas = new HashMap<Integer, Float>();
    private Map<Integer, String[]> lineas = new HashMap<Integer, String[]>();
    private JButton exitButton;
    private JButton addButton;
    private JButton refreshButton;
    private JButton dropButton;
    private User user;

    public Facturas(User user) {
        this.user = user;
        // Configuración del JFrame
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 800);
        setResizable(false);
        
        facturas = ConnectionDB.getFacturas(user.getEmail());  
        lineas = ConnectionDB.getLineasFactura(user.getEmail());
        System.out.println(lineas);
        
        // Creación del panel y configuración de la cuadrícula
        panel = new JPanel(new GridLayout(0, 1, 10, 10)); // 0 indica número de filas dinámicas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un objeto Font para fijar el tamaño de los JLabel
        Font font = new Font("Arial", Font.PLAIN, 24);
        Font font2 = new Font("Arial", Font.PLAIN, 18);

        // Agregar cada fruta a un panel con GridLayout (3,1) y agregarlo al panel principal
        for (Map.Entry<Integer, Float> factura : facturas.entrySet()) {
            

            String id_factura = String.valueOf(factura.getKey());
            String precio_total = String.valueOf(factura.getValue());
            
            // Panel
            JPanel prodPanel = new JPanel(new GridLayout(0,1, 10, 10));
            prodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // ID FACTURA
            JLabel id_ = new JLabel(id_factura);
            id_.setHorizontalAlignment(JLabel.CENTER);
            id_.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(id_);
            
            int contador = 0;
            
            // ########################
            for (Map.Entry<Integer, String[]> entrada : lineas.entrySet()) {
                int clave = entrada.getKey();
                String[] valores = entrada.getValue();

                System.out.println("Clave: " + clave);
                
                
                
                if (valores[0].equals(id_factura)){
                    JLabel valor_linea = new JLabel("  " + contador + ": " + valores[1] + " - " + valores[2] + " - " + valores[3]);
                    valor_linea.setHorizontalAlignment(JLabel.LEADING);
                    valor_linea.setFont(font2); // Fijar el tamaño de la fuente
                    prodPanel.add(valor_linea);
                    contador ++;
                }
 
            }
            contador = 0;
            // ########################
            

                // PRECIO FINAL
            JLabel precio_total_ = new JLabel("Precio total: " + precio_total);
            precio_total_.setHorizontalAlignment(JLabel.CENTER);
            precio_total_.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(precio_total_);

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

        // Creación del botón "Añadir factura"
        addButton = new JButton("Añadir");
        addButton.setSize(200,100);
        addButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        addButton.addActionListener(e -> {
            // Código para añadir un nueva factura y rescatar el id de la factura.
            int id_factura = ConnectionDB.addFactura(user);
            
            // El id de la factura lo coge bien
            
            AddFactura fact = new AddFactura(user, id_factura);
            fact.setVisible(true);
            fact.setLocationRelativeTo(null);
            
            
        });
        
        // Creación del botón "Eliminar producto"
        dropButton = new JButton("Eliminar");
        dropButton.setSize(200,100);
        dropButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        dropButton.addActionListener(e -> {
            // Código para eliminar un producto
            DropProduct drop = new DropProduct(user);
            drop.setVisible(true);
            drop.setLocationRelativeTo(null);
        });
        
        // Creación del botón "Actualizar" con imagen
        refreshButton = new JButton("Actualizar");
        refreshButton.setSize(200,100);
        refreshButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        refreshButton.addActionListener(e -> {
            // Código para añadir un nuevo producto
            this.dispose();
            Facturas factura = new Facturas(user);
            factura.setVisible(true);
            factura.setLocationRelativeTo(null);
        });

        // Agregar los botones al panel y al sur del JFrame
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(dropButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Agregar el JScrollPane al JFrame
        add(scrollPane);

        // Mostrar el JFrame
        setVisible(true);
    }
}