package com.project;  
import javax.swing.*;
import java.awt.*;

public class GameInterface extends JFrame {
    public GameInterface() {
        setTitle("Upgrade Technology");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel attackUnitPanel = createAttackUnitPanel();
        JPanel defenceUnitPanel = createDefenceUnitPanel();
        JPanel buildingsPanel = createBuildingsPanel();
        JPanel specialUnitPanel = createSpecialUnitPanel();

        tabbedPane.addTab("Attack Unit", attackUnitPanel);
        tabbedPane.addTab("Defence Unit", defenceUnitPanel);
        tabbedPane.addTab("Buildings", buildingsPanel);
        tabbedPane.addTab("Special Unit", specialUnitPanel);

        add(tabbedPane);

        setVisible(true);
    }

    private JPanel createAttackUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false); 

        panel.add(createUnitPanel("Swordsman", "8000", "3000", "50", "0", "path_to_swordsman_icon.png"));
        panel.add(createUnitPanel("Spearman", "5000", "6500", "50", "0", "path_to_spearman_icon.png"));
        panel.add(createUnitPanel("Crossbow", "0", "45000", "7000", "0", "path_to_crossbow_icon.png"));
        panel.add(createUnitPanel("Cannon", "0", "30000", "15000", "0", "path_to_cannon_icon.png"));

        JLabel background = new JLabel(new ImageIcon("path_to_background_image.jpg")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createDefenceUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setOpaque(false); 

        panel.add(createUnitPanel("Arrow Tower", "0", "2000", "0", "0", "path_to_arrow_tower_icon.png"));
        panel.add(createUnitPanel("Catapult", "0", "4000", "500", "0", "path_to_catapult_icon.png"));
        panel.add(createUnitPanel("Rocket Launcher Tower", "0", "50000", "5000", "0", "path_to_rocket_launcher_tower_icon.png"));

        JLabel background = new JLabel(new ImageIcon("path_to_background_image.jpg")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);



        return backgroundPanel;
    }

    private JPanel createBuildingsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setOpaque(false); 

        panel.add(createUnitPanel("Farm", "5000", "10000", "12000", "0", "path_to_farm_icon.png"));
        panel.add(createUnitPanel("Church", "10000", "20000", "24000", "10000", "path_to_church_icon.png"));
        panel.add(createUnitPanel("Magic Tower", "10000", "20000", "24000", "0", "path_to_magic_tower_icon.png"));
        panel.add(createUnitPanel("Smithy", "5000", "10000", "12000", "0", "path_to_smithy_icon.png"));
        panel.add(createUnitPanel("Carpentry", "5000", "10000", "12000", "0", "path_to_carpentry_icon.png"));
        

        JLabel background = new JLabel(new ImageIcon("path_to_background_image.jpg")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createSpecialUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false); 

        panel.add(createUnitPanel("Magician", "300000", "150000", "1000", "500", "path_to_magician_icon.png"));
        panel.add(createUnitPanel("Priest", "200000", "100000", "500", "300", "path_to_priest_icon.png"));

        JLabel background = new JLabel(new ImageIcon("path_to_background_image.jpg")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createUnitPanel(String unitName, String food, String wood, String iron, String mana, String iconPath) {
        JPanel unitPanel = new JPanel(new BorderLayout());
        unitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        unitPanel.setOpaque(false); 

        // Crear los componentes
        JLabel iconLabel = new JLabel(new ImageIcon(iconPath)); // Reemplaza con la ruta al icono correspondiente
        JLabel resourcesLabel = new JLabel(String.format("Food: %s Wood: %s Iron: %s Mana: %s", food, wood, iron, mana));
        JTextField unitCountField = new JTextField("1", 5);
        JButton createButton = new JButton("Create");

        createButton.addActionListener(e -> {
            int count = Integer.parseInt(unitCountField.getText());
            JOptionPane.showMessageDialog(this, unitName + " units to create: " + count);
        });

        unitPanel.add(iconLabel, BorderLayout.WEST);
        unitPanel.add(resourcesLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo
        bottomPanel.add(new JLabel("Units to create: "));
        bottomPanel.add(unitCountField);
        bottomPanel.add(createButton);

        unitPanel.add(bottomPanel, BorderLayout.SOUTH);

        return unitPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameInterface::new);
    }
}