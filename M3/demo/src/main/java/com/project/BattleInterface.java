package com.project;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BattleInterface extends JFrame {

    private JLabel foodLabel;
    private JLabel woodLabel;
    private JLabel ironLabel;
    private JLabel manaLabel;
    private JTextArea battleLogArea;

    private int food = 0;
    private int wood = 0;
    private int iron = 0;
    private int mana = 0;

    private List<MilitaryUnit> civilizationUnits;
    private List<MilitaryUnit> enemyUnits;
    private StringBuilder battleLog;

    public BattleInterface() {
        setTitle("La Batalla Comienza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        civilizationUnits = new ArrayList<>();
        enemyUnits = new ArrayList<>();
        battleLog = new StringBuilder();

        // Example units, you can add your own logic to initialize these
        civilizationUnits.add(new Swordsman(5, 5));
        civilizationUnits.add(new Spearman(5, 5));
        enemyUnits.add(new Cannon(5, 5));

        JPanel materialsPanel = new JPanel();
        materialsPanel.setLayout(new BoxLayout(materialsPanel, BoxLayout.Y_AXIS));
        materialsPanel.setBorder(BorderFactory.createTitledBorder("Materiales"));

        JPanel foodPanel = createMaterialPanel("Comida", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\steak.png", foodLabel = new JLabel("0"));
        JPanel woodPanel = createMaterialPanel("Madera", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\wood.png", woodLabel = new JLabel("0"));
        JPanel ironPanel = createMaterialPanel("Hierro", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\iron.png", ironLabel = new JLabel("0"));
        JPanel manaPanel = createMaterialPanel("Mana", "C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\mana.png", manaLabel = new JLabel("0"));

        materialsPanel.add(foodPanel);
        materialsPanel.add(woodPanel);
        materialsPanel.add(ironPanel);
        materialsPanel.add(manaPanel);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 1, 5, 5));

        JButton upgradeButton = new JButton("Upgrade Civilización");
        JButton statsButton = new JButton("Civilization Stats");
        JButton sanctifyButton = new JButton("Sanctify Units");

        buttonsPanel.add(upgradeButton);
        buttonsPanel.add(statsButton);
        buttonsPanel.add(sanctifyButton);

        materialsPanel.add(buttonsPanel);

        JPanel battleFieldPanel = new BattleFieldPanel("/com/project/fotos/campo_de_batalla_v2.png");
        battleFieldPanel.setBorder(BorderFactory.createTitledBorder("Campo de Batalla"));

        add(materialsPanel, BorderLayout.EAST);
        add(battleFieldPanel, BorderLayout.CENTER);

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BattleInterface());
    }
}
