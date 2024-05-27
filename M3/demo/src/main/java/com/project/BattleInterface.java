package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BattleInterface extends JFrame {

    private JLabel foodLabel;
    private JLabel woodLabel;
    private JLabel ironLabel;
    private JLabel manaLabel;

    private int food = 0;
    private int wood = 0;
    private int iron = 0;
    private int mana = 0;

    public BattleInterface() {
        // Configuración de la ventana principal
        setTitle("La Batalla Comienza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Panel de materiales a la derecha con fondo personalizado
        JPanel materialsPanel = new MaterialsPanel("C:\\ruta\\a\\tu\\C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\maderafondo.png");
        materialsPanel.setLayout(new BoxLayout(materialsPanel, BoxLayout.Y_AXIS));
        materialsPanel.setBorder(BorderFactory.createTitledBorder("Materiales"));

        // Crear paneles para cada material con su imagen y etiqueta
        JPanel foodPanel = createMaterialPanel("Comida", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\steak.png", foodLabel = new JLabel("0"));
        JPanel woodPanel = createMaterialPanel("Madera", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\wood.png", woodLabel = new JLabel("0"));
        JPanel ironPanel = createMaterialPanel("Hierro", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\iron.png", ironLabel = new JLabel("0"));
        JPanel manaPanel = createMaterialPanel("Mana", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\mana.png", manaLabel = new JLabel("0"));

        materialsPanel.add(foodPanel);
        materialsPanel.add(woodPanel);
        materialsPanel.add(ironPanel);
        materialsPanel.add(manaPanel);

        // Panel de botones debajo de los materiales
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JButton upgradeButton = new JButton("Upgrade Civilización");
        JButton statsButton = new JButton("Civilization Stats");
        JButton sanctifyButton = new JButton("Sanctify Units");

        buttonsPanel.add(upgradeButton);
        buttonsPanel.add(statsButton);
        buttonsPanel.add(sanctifyButton);

        // Añadir el panel de botones al panel de materiales
        materialsPanel.add(buttonsPanel);

        // Panel del campo de batalla a la izquierda con fondo
        JPanel battleFieldPanel = new BattleFieldPanel("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\campo_de_batalla_v2.png");
        battleFieldPanel.setBorder(BorderFactory.createTitledBorder("Campo de Batalla"));

        // Añadir los paneles a la ventana principal
        add(materialsPanel, BorderLayout.EAST);
        add(battleFieldPanel, BorderLayout.CENTER);

        // Timer para actualizar los materiales cada segundo (1000 ms)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incrementMaterials();
            }
        });
        timer.start();

        // Hacer visible la ventana
        setVisible(true);
    }

    private JPanel createMaterialPanel(String name, String imagePath, JLabel valueLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        JLabel nameLabel = new JLabel(name + ": ");

        panel.add(imageLabel);
        panel.add(nameLabel);
        panel.add(valueLabel);

        return panel;
    }

    private void incrementMaterials() {
        food += 100; // Incrementa comida en 100
        wood += 50; // Incrementa madera en 50
        iron += 30; // Incrementa hierro en 30
        mana += 2; // Incrementa mana en 2

        foodLabel.setText(String.valueOf(food));
        woodLabel.setText(String.valueOf(wood));
        ironLabel.setText(String.valueOf(iron));
        manaLabel.setText(String.valueOf(mana));
    }

    // Clase personalizada para el panel del campo de batalla con fondo
    private class BattleFieldPanel extends JPanel {
        private Image backgroundImage;
        private final int margin = 20;

        public BattleFieldPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                int imgWidth = getWidth() - 2 * margin;
                int imgHeight = getHeight() - 2 * margin;
                g.drawImage(backgroundImage, margin, margin, imgWidth, imgHeight, this);
            }
        }
    }

    // Clase personalizada para el panel de materiales con fondo
    private class MaterialsPanel extends JPanel {
        private Image backgroundImage;
        private final int margin = 20;

        public MaterialsPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                int imgWidth = getWidth() - 2 * margin;
                int imgHeight = getHeight() - 2 * margin;
                g.drawImage(backgroundImage, margin, margin, imgWidth, imgHeight, this);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BattleInterface());
    }
}
