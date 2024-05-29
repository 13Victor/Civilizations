package com.project;

import javax.swing.*;
import java.awt.*;

public class GameInterface extends JFrame {
    public GameInterface() {
        setTitle("Upgrade Technology");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        panel.add(createUnitPanel("Swordsman", "8000", "3000", "50", "0", "/com/project/fotos/swordsman.png"));
        panel.add(createUnitPanel("Spearman", "5000", "6500", "50", "0", "/com/project/fotos/spearman.png"));
        panel.add(createUnitPanel("Crossbow", "0", "45000", "7000", "0", "/com/project/fotos/crossbow.png"));
        panel.add(createUnitPanel("Cannon", "0", "30000", "15000", "0", "/com/project/fotos/cannon.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createDefenceUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Arrow Tower", "0", "2000", "0", "0", "/com/project/fotos/arrow-tower.png"));
        panel.add(createUnitPanel("Catapult", "0", "4000", "500", "0", "/com/project/fotos/catapult.png"));
        panel.add(createUnitPanel("Rocket Launcher Tower", "0", "50000", "5000", "0", "/com/project/fotos/rocket-launcher.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createBuildingsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Farm", "5000", "10000", "12000", "0", "/com/project/fotos/farm.png"));
        panel.add(createUnitPanel("Church", "10000", "20000", "24000", "10000", "/com/project/fotos/church-.png"));
        panel.add(createUnitPanel("Magic Tower", "10000", "20000", "24000", "0", "/com/project/fotos/magic-tower.png"));
        panel.add(createUnitPanel("Smithy", "5000", "10000", "12000", "0", "/com/project/fotos/smithy.png"));
        panel.add(createUnitPanel("Carpentry", "5000", "10000", "12000", "0", "/com/project/fotos/carpentry.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createSpecialUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Magician", "300000", "150000", "1000", "500", "/com/project/fotos/magician.png"));
        panel.add(createUnitPanel("Priest", "200000", "100000", "500", "300", "/com/project/fotos/priest.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createUnitPanel(String unitName, String food, String wood, String iron, String mana, String iconPath) {
        JPanel unitPanel = new JPanel(new BorderLayout());
        unitPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 30, 10));
        unitPanel.setOpaque(false);

        // Crear los componentes
        JLabel iconLabel = new JLabel(loadImage(iconPath));
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

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameInterface::new);
    }
}
