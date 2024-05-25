package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class CivilizationMenu extends JFrame {

    public CivilizationMenu() {
        // Configuración del JFrame
        setTitle("Jefazo's Civilization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del JPanel con fondo personalizado
        JPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new BorderLayout());

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Hacer transparente el panel de botones
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.EAST;

        // Creación de los botones
        JButton playButton = createButton("PLAY");
        JButton loadGameButton = createButton("LOAD GAME");
        JButton creditsButton = createButton("CREDITS");
        JButton quitButton = createButton("QUIT");

        // Agregar botones al panel de botones
        gbc.gridy = 0;
        buttonPanel.add(playButton, gbc);

        gbc.gridy = 1;
        buttonPanel.add(loadGameButton, gbc);

        gbc.gridy = 2;
        buttonPanel.add(creditsButton, gbc);

        gbc.gridy = 3;
        buttonPanel.add(quitButton, gbc);

        // Agregar el panel de botones al panel de fondo
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel al frame
        add(backgroundPanel);

        // Acción para el botón de salida
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                // Cargar la imagen de fondo
                backgroundImage = ImageIO.read(new File("path/to/your/background-image.jpg"));
            } catch (IOException e) {
                System.err.println("Error: No se pudo cargar la imagen de fondo.");
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen de fondo
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CivilizationMenu().setVisible(true);
            }
        });
    }
}
