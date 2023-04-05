package com.calar.gui;
import com.calar.logic.User;
import com.calar.persistence.ConnectionDB;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Productos extends JFrame {

    private JPanel panel;
    private Map<Integer, Object[]> productos = new HashMap<Integer, Object[]>();
    private JButton exitButton;
    private JButton addButton;
    private JButton refreshButton;
    private JButton dropButton;
    private User user;

    public Productos(User user) {
        this.user = user;
        // Configuración del JFrame
        setTitle("Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setResizable(false);
        
        productos = ConnectionDB.getProducts(user.getEmail());    

        // Creación del panel y configuración de la cuadrícula
        panel = new JPanel(new GridLayout(0, 3, 10, 10)); // 0 indica número de filas dinámicas
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un objeto Font para fijar el tamaño de los JLabel
        Font font = new Font("Arial", Font.PLAIN, 24);

        // Agregar cada fruta a un panel con GridLayout (3,1) y agregarlo al panel principal
        for (int i = 0; i < productos.size(); i++) {
            
            Object[] prod = productos.get(i);
            
            JPanel prodPanel = new JPanel(new GridLayout(2,1));
            prodPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            prodPanel.setPreferredSize(new Dimension(100, 70)); // Fijar el tamaño preferido del panel de la fruta

            JLabel nombreLabel = new JLabel("" + prod[0]);
            nombreLabel.setHorizontalAlignment(JLabel.CENTER);
            nombreLabel.setFont(font); // Fijar el tamaño de la fuente
            prodPanel.add(nombreLabel);

            JLabel precioLabel = new JLabel("Precio: " + prod[1] + "€");
            precioLabel.setHorizontalAlignment(JLabel.CENTER);
            prodPanel.add(precioLabel);

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

        // Creación del botón "Añadir producto"
        addButton = new JButton("Añadir");
        addButton.setSize(200,100);
        addButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        addButton.addActionListener(e -> {
            // Código para añadir un nuevo producto
            AddProduct pr = new AddProduct(user);
            pr.setVisible(true);
            pr.setLocationRelativeTo(null);
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
            ImageIcon imagenOriginal = new ImageIcon("images/peligro.png");
            Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(imagenRedimensionada);
            JOptionPane.showMessageDialog(null, "Los productos por defecto no pueden eliminarse.\nSolo puedes eliminar los que has añadido.", "Eliminar producto", JOptionPane.INFORMATION_MESSAGE, icon);

        });
        
        // Creación del botón "Actualizar" con imagen
        refreshButton = new JButton("Actualizar");
        refreshButton.setSize(200,100);
        refreshButton.setFont(new java.awt.Font("Helvetica Neue", 0, 24));
        refreshButton.addActionListener(e -> {
            // Código para añadir un nuevo producto
            this.dispose();
            Productos producto = new Productos(user);
            producto.setVisible(true);
            producto.setLocationRelativeTo(null);
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