package com.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CivilizationMenuController {

    public CivilizationMenuController(ss view) {
        view.playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginWindow();
            }
        });

        view.creditsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreditsWindow();
            }
        });

        view.quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    class LoginWindow extends JFrame {
        public LoginWindow() {
            setTitle("Login");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel loginPanel = new ss.BackgroundPanel("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\login.png");
            loginPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel nameLabel = new JLabel("Nombre:");
            nameLabel.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
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
                    // Aquí puedes agregar el código para manejar el nombre del jugador
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

            JPanel creditsPanel = new ss.BackgroundPanel("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\fondocreditos.png");
            creditsPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;

            JLabel creditsLabel = new JLabel("<html><center>Desarrollado por:<br>Daniel Hirich<br>Marc Cachinero<br>Victor Valero</center></html>", SwingConstants.CENTER);
            creditsLabel.setForeground(Color.WHITE); // Cambiar el color del texto a blanco
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
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ss view = new ss();
                new CivilizationMenuController(view);
                view.setVisible(true);
            }
        });
    }
}
