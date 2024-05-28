package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private List<Unit> civilizationUnits;
    private List<Unit> enemyUnits;
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
        civilizationUnits.add(new Unit("Swordsman", 100));
        civilizationUnits.add(new Unit("Spearman", 150));
        enemyUnits.add(new Unit("Cannon", 200));

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

        JPanel battleFieldPanel = new BattleFieldPanel("C:\\Users\\marcc\\OneDrive\\Documentos\\GitHub\\Civilizations\\M3\\demo\\src\\main\\java\\com\\project\\fotos\\campo_de_batalla_v2.png");
        battleFieldPanel.setBorder(BorderFactory.createTitledBorder("Campo de Batalla"));

        add(materialsPanel, BorderLayout.EAST);
        add(battleFieldPanel, BorderLayout.CENTER);

        // Add action listener to the upgrade button
        upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GameInterface();
            }
        });

        // Timer para actualizar los materiales cada segundo (1000 ms)
        Timer materialTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                incrementMaterials();
            }
        });
        materialTimer.start();

        // Timer para mostrar el popup cada tres minutos (180000 ms)
        Timer popupTimer = new Timer(10000000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulateBattle();
                showPopup();
            }
        });
        popupTimer.start();

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
        food += 10; // Incrementa comida en 10
        wood += 5; // Incrementa madera en 5
        iron += 3; // Incrementa hierro en 3
        mana += 2; // Incrementa mana en 2

        foodLabel.setText(String.valueOf(food));
        woodLabel.setText(String.valueOf(wood));
        ironLabel.setText(String.valueOf(iron));
        manaLabel.setText(String.valueOf(mana));
    }

    private void showPopup() {
        JDialog popup = new JDialog(this, "Battle Log", false);
        popup.setSize(400, 300);
        popup.setLocation(0, 0);

        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        textArea.setText(battleLog.toString());

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.dispose();
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        popup.add(panel);
        popup.setVisible(true);
    }

    private void simulateBattle() {
        battleLog.setLength(0); // Reset battle log

        for (Unit enemy : enemyUnits) {
            if (!civilizationUnits.isEmpty()) {
                Unit defender = civilizationUnits.get(0);
                battleLog.append(String.format("Enemy Unit: %s will attack...\n", enemy.getName()));
                battleLog.append(String.format("Civilization Unit: %s will defend...\n", defender.getName()));
                int damage = enemy.getAttackPower();
                battleLog.append(String.format("%s generates the damage = %d\n", enemy.getName(), damage));
                defender.setHealth(defender.getHealth() - damage);
                battleLog.append(String.format("%s stays with = %d\n", defender.getName(), defender.getHealth()));

                if (defender.getHealth() <= 0) {
                    battleLog.append(String.format("%s of Civilization is dead\n", defender.getName()));
                    civilizationUnits.remove(defender);
                }
                battleLog.append("Extra turn Enemy.\n\n");
            }
        }

        if (civilizationUnits.isEmpty()) {
            battleLog.append("End of the battle\n");
            battleLog.append("Enemy WIN !!\n");
        }
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

    private class Unit {
        private String name;
        private int health;
        private int attackPower;

        public Unit(String name, int health) {
            this.name = name;
            this.health = health;
            this.attackPower = 100; // Default attack power
        }

        public String getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public int getAttackPower() {
            return attackPower;
        }

        public void setAttackPower(int attackPower) {
            this.attackPower = attackPower;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BattleInterface());
    }
}
