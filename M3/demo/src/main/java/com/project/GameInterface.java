package com.project;

import javax.swing.*;
import java.awt.*;

public class GameInterface extends JFrame {
    public GameInterface() {
        // Configurar la ventana principal
        setTitle("Upgrade Technology");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un JTabbedPane
        JTabbedPane tabbedPane = new JTabbedPane();

        // Crear las cuatro pestañas
        JPanel attackUnitPanel = createAttackUnitPanel();
        JPanel defenceUnitPanel = createDefenceUnitPanel();
        JPanel buildingsPanel = createBuildingsPanel();
        JPanel specialUnitPanel = createSpecialUnitPanel();

        // Agregar las pestañas al JTabbedPane
        tabbedPane.addTab("Attack Unit", attackUnitPanel);
        tabbedPane.addTab("Defence Unit", defenceUnitPanel);
        tabbedPane.addTab("Buildings", buildingsPanel);
        tabbedPane.addTab("Special Unit", specialUnitPanel);

        // Agregar el JTabbedPane al JFrame
        add(tabbedPane);

        // Hacer visible la ventana
        setVisible(true);
    }

    private JPanel createAttackUnitPanel() {
        // Crear un JPanel con un GridLayout de 2 filas y 2 columnas
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo

        // Agregar los componentes
        panel.add(createUnitPanel("Swordsman", "500000", "180000", "3150", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\swordsman.png"));
        panel.add(createUnitPanel("Spearman", "50000", "65000", "50", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\spearman.png"));
        panel.add(createUnitPanel("Crossbow", "0", "45000", "7000", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\crossbow.png"));
        panel.add(createUnitPanel("Cannon", "0", "30000", "15000", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\cannon.png"));

        // Crear un JLabel con el fondo
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\backgroundpersonajes.png")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        // Crear un JPanel para contener el fondo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createDefenceUnitPanel() {
        // Crear un JPanel con un GridLayout de 1 fila y 3 columnas
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo

        // Agregar los componentes
        panel.add(createUnitPanel("Arrow Tower", "0", "400000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\arrow-tower.png"));
        panel.add(createUnitPanel("Catapult", "0", "40000", "500", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\catapult.png"));
        panel.add(createUnitPanel("Rocket Launcher Tower", "0", "50000", "10000", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\rocket-launcher.png"));

        // Crear un JLabel con el fondo
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\backgroundpersonajes.png")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        // Crear un JPanel para contener el fondo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createBuildingsPanel() {
        // Crear un JPanel con un GridLayout de 2 filas y 3 columnas
        JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
        panel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo

        // Agregar los componentes
        panel.add(createUnitPanel("Farm", "0", "20000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\farm.png"));
        panel.add(createUnitPanel("Church", "0", "30000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\church-.png"));
        panel.add(createUnitPanel("Magic Tower", "0", "50000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\magic-tower.png"));
        panel.add(createUnitPanel("Smithy", "0", "25000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\smithy.png"));
        panel.add(createUnitPanel("Carpentry", "0", "15000", "0", "0", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\carpentry.png"));

        // Crear un JLabel con el fondo
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\backgroundpersonajes.png")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        // Crear un JPanel para contener el fondo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createSpecialUnitPanel() {
        // Crear un JPanel con un GridLayout de 1 fila y 2 columnas
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        panel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo

        // Agregar los componentes
        panel.add(createUnitPanel("Magician", "300000", "150000", "1000", "500", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\magician.png"));
        panel.add(createUnitPanel("Priest", "200000", "100000", "500", "300", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\priest.png"));

        // Crear un JLabel con el fondo
        JLabel background = new JLabel(new ImageIcon("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\backgroundpersonajes.png")); // Reemplaza con la ruta a la imagen de fondo
        background.setLayout(new BorderLayout());
        background.add(panel, BorderLayout.CENTER);

        // Crear un JPanel para contener el fondo
        JPanel backgroundPanel = new JPanel(new BorderLayout());
        backgroundPanel.add(background, BorderLayout.CENTER);

        return backgroundPanel;
    }

    private JPanel createUnitPanel(String unitName, String food, String wood, String iron, String mana, String iconPath) {
        // Crear un JPanel con un BoxLayout
        JPanel unitPanel = new JPanel();
        unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.Y_AXIS));
        unitPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        unitPanel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo

        // Crear los componentes
        JLabel iconLabel = new JLabel(new ImageIcon(iconPath)); // Reemplaza con la ruta al icono correspondiente
        JLabel resourcesLabel = new JLabel(String.format("Food: %s Wood: %s Iron: %s Mana: %s", food, wood, iron, mana));
        JTextField unitCountField = new JTextField("1", 5);
        JButton createButton = new JButton("Create");

        // Configurar el botón para que muestre un mensaje al hacer clic
        createButton.addActionListener(e -> {
            int count = Integer.parseInt(unitCountField.getText());
            JOptionPane.showMessageDialog(this, unitName + " units to create: " + count);
        });

        // Alinear los componentes horizontalmente centrados
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resourcesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unitCountField.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar los componentes al unitPanel
        unitPanel.add(iconLabel);
        unitPanel.add(resourcesLabel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Hacer que el panel sea transparente para ver el fondo
        bottomPanel.add(new JLabel("Units to create: "));
        bottomPanel.add(unitCountField);
        bottomPanel.add(createButton);

        unitPanel.add(bottomPanel);

        return unitPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameInterface::new);
    }
}
