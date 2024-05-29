package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.io.IOException;
import javax.imageio.ImageIO;

public class CivilizationMenu extends JFrame {

    private JButton playButton;
    private JButton loadGameButton;
    private JButton creditsButton;
    private JButton quitButton;
    private CivilizationDao civilizationDao;

    public CivilizationMenu() {
        // Inicializa el DAO
        civilizationDao = new CivilizationDao();

        // Configuración del JFrame
        setTitle("Jefazo's Civilization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración del JPanel con fondo personalizado
        JPanel backgroundPanel = new BackgroundPanel(getClass().getResource("/com/project/fotos/civilizations_main_page.png"));
        backgroundPanel.setLayout(new BorderLayout());

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Hacer transparente el panel de botones
        buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER; // Centrar los botones horizontalmente
        gbc.fill = GridBagConstraints.NONE;

        // Creación de los botones con fondo personalizado
        playButton = createButton("PLAY", getClass().getResource("/com/project/fotos/wood-button.png"));
        loadGameButton = createButton("LOAD GAME", getClass().getResource("/com/project/fotos/wood-button.png"));
        creditsButton = createButton("CREDITS", getClass().getResource("/com/project/fotos/wood-button.png"));
        quitButton = createButton("QUIT", getClass().getResource("/com/project/fotos/wood-button.png"));

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

        // Acción para el botón de PLAY
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWindow().setVisible(true);
            }
        });

        // Acción para el botón de CREDITS
        creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreditsWindow().setVisible(true);
            }
        });

        // Acción para el botón de salida
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private JButton createButton(String text, URL backgroundImagePath) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setPreferredSize(new Dimension(200, 50));

        try {
            Image img = ImageIO.read(backgroundImagePath);
            button.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            System.err.println("Error: No se pudo cargar la imagen de fondo para el botón " + text);
            e.printStackTrace();
        }

        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);

        return button;
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(URL imagePath) {
            try {
                // Cargar la imagen de fondo
                backgroundImage = ImageIO.read(imagePath);
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

    class LoginWindow extends JFrame {
        public LoginWindow() {
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel loginPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel nameLabel = new JLabel("Nombre:");
            loginPanel.add(nameLabel, gbc);

            gbc.gridx = 1;
            JTextField nameField = new JTextField(15);
            loginPanel.add(nameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            JButton enterButton = new JButton("Entrar al campo de batalla");
            loginPanel.add(enterButton, gbc);

            add(loginPanel);

            enterButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String playerName = nameField.getText();
                    // Guardar el nombre del jugador en la base de datos
                    civilizationDao.insertPlayer(playerName);
                    System.out.println("Jugador: " + playerName);
                    dispose(); // Cierra la ventana de login
                }
            });

            setVisible(true);
        }
    }

    class CreditsWindow extends JFrame {
        public CreditsWindow() {
            setTitle("Créditos");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel creditsPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel creditsLabel = new JLabel("<html><center>Desarrollado por:<br>Daniel Hirich<br>Marc Cachinero<br>Victor Valero</center></html>", SwingConstants.CENTER);
            creditsPanel.add(creditsLabel, gbc);

            gbc.gridy = 1;
            JButton closeButton = new JButton("Cerrar");
            closeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Cierra la ventana de créditos
                }
            });
            creditsPanel.add(closeButton, gbc);

            add(creditsPanel);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CivilizationMenu().setVisible(true));
    }
}
