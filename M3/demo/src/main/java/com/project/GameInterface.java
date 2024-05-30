package com.project;

import javax.swing.*;
import java.awt.*;

public class GameInterface extends JFrame {
    private GameController controller;

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

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private JPanel createAttackUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Swordsman", 8000, 3000, 50, 0, "/com/project/fotos/swordsman.png"));
        panel.add(createUnitPanel("Spearman", 5000, 6500, 50, 0, "/com/project/fotos/spearman.png"));
        panel.add(createUnitPanel("Crossbow", 0, 45000, 7000, 0, "/com/project/fotos/crossbow.png"));
        panel.add(createUnitPanel("Cannon", 0, 30000, 15000, 0, "/com/project/fotos/cannon.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createDefenceUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Arrow Tower", 0, 2000, 0, 0, "/com/project/fotos/arrow-tower.png"));
        panel.add(createUnitPanel("Catapult", 0, 4000, 500, 0, "/com/project/fotos/catapult.png"));
        panel.add(createUnitPanel("Rocket Launcher Tower", 0, 50000, 5000, 0, "/com/project/fotos/rocket-launcher.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createBuildingsPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Farm", 5000, 10000, 12000, 0, "/com/project/fotos/farm.png"));
        panel.add(createUnitPanel("Church", 10000, 20000, 24000, 10000, "/com/project/fotos/church.png"));
        panel.add(createUnitPanel("Magic Tower", 10000, 20000, 24000, 0, "/com/project/fotos/magic-tower.png"));
        panel.add(createUnitPanel("Smithy", 5000, 10000, 12000, 0, "/com/project/fotos/smithy.png"));
        panel.add(createUnitPanel("Carpentry", 5000, 10000, 12000, 0, "/com/project/fotos/carpentry.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createSpecialUnitPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false);

        panel.add(createUnitPanel("Magician", 12000, 2000, 0, 5000, "/com/project/fotos/magician.png"));
        panel.add(createUnitPanel("Priest", 15000, 0, 0, 15000, "/com/project/fotos/priest.png"));

        BackgroundPanel backgroundPanel = new BackgroundPanel("/com/project/fotos/backgroundpersonajes.png");
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(panel, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createUnitPanel(String unitName, int food, int wood, int iron, int mana, String iconPath) {
        JPanel unitPanel = new JPanel(new BorderLayout());
        unitPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 30, 10));
        unitPanel.setOpaque(false);

        // Crear los componentes
        JLabel iconLabel = new JLabel(loadImage(iconPath));
        JLabel resourcesLabel = new JLabel(String.format("Food: %d Wood: %d Iron: %d Mana: %d", food, wood, iron, mana));
        JTextField unitCountField = new JTextField("1", 5);
        JButton createButton = new JButton("Create");

        createButton.addActionListener(e -> {
            if (controller != null) {
                int count = Integer.parseInt(unitCountField.getText());
                controller.createUnit(unitName, count, food, wood, iron, mana);
            } else {
                System.err.println("Controller is not set");
            }
        });

        unitPanel.add(iconLabel, BorderLayout.WEST);
        unitPanel.add(resourcesLabel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); 
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

    public void updateMaterials(int food, int wood, int iron, int mana) {
        
        System.out.println("Food: " + food + " Wood: " + wood + " Iron: " + iron + " Mana: " + mana);
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            java.net.URL imgURL = getClass().getResource(imagePath);
            if (imgURL != null) {
                backgroundImage = new ImageIcon(imgURL).getImage();
            } else {
                System.err.println("Couldn't find file: " + imagePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int width = getWidth();
            int height = getHeight();
            g.drawImage(backgroundImage, 0, 0, width, height, this);
        }
    }
}
