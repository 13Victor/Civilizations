package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class BattleInterface extends JFrame {

    private JLabel foodLabel;
    private JLabel woodLabel;
    private JLabel ironLabel;
    private JLabel manaLabel;
    private JTextArea battleLogArea;

    private List<MilitaryUnit> civilizationUnits;
    private List<MilitaryUnit> enemyUnits;

    public BattleInterface() {
        setTitle("La Batalla Comienza");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel materialsPanel = new JPanel();
        materialsPanel.setLayout(new BoxLayout(materialsPanel, BoxLayout.Y_AXIS));
        materialsPanel.setBorder(BorderFactory.createTitledBorder("Materiales"));

        JPanel foodPanel = createMaterialPanel("Comida", "/com/project/fotos/steak.png", foodLabel = new JLabel("0"));
        JPanel woodPanel = createMaterialPanel("Madera", "/com/project/fotos/wood.png", woodLabel = new JLabel("0"));
        JPanel ironPanel = createMaterialPanel("Hierro", "/com/project/fotos/iron.png", ironLabel = new JLabel("0"));
        JPanel manaPanel = createMaterialPanel("Mana", "/com/project/fotos/mana.png", manaLabel = new JLabel("0"));

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

        JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));
        JLabel nameLabel = new JLabel(name + ": ");

        panel.add(imageLabel);
        panel.add(nameLabel);
        panel.add(valueLabel);

        return panel;
    }

    public void setMaterials(int food, int wood, int iron, int mana) {
        foodLabel.setText(String.valueOf(food));
        woodLabel.setText(String.valueOf(wood));
        ironLabel.setText(String.valueOf(iron));
        manaLabel.setText(String.valueOf(mana));
    }

    public void updateMaterials(int food, int wood, int iron, int mana) {
        setMaterials(food, wood, iron, mana);
    }

    public void setCivilizationUnits(List<MilitaryUnit> civilizationUnits) {
        this.civilizationUnits = civilizationUnits;
    }

    public void setEnemyUnits(List<MilitaryUnit> enemyUnits) {
        this.enemyUnits = enemyUnits;
    }

    public void addUpgradeButtonListener(ActionListener listener) {
        JButton upgradeButton = new JButton("Upgrade Civilización");
        upgradeButton.addActionListener(listener);
    }

    public void addStatsButtonListener(ActionListener listener) {
        JButton statsButton = new JButton("Civilization Stats");
        statsButton.addActionListener(listener);
    }

    public void addSanctifyButtonListener(ActionListener listener) {
        JButton sanctifyButton = new JButton("Sanctify Units");
        sanctifyButton.addActionListener(listener);
    }

    public void startMaterialTimer(ActionListener listener) {
        Timer materialTimer = new Timer(1000, listener);
        materialTimer.start();
    }

    public void startPopupTimer(ActionListener listener) {
        Timer popupTimer = new Timer(180000, listener);
        popupTimer.start();
    }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public void showStats(String stats) {
        JOptionPane.showMessageDialog(this, stats, "Civilization Stats", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showBattleLog(String battleLog) {
        JDialog popup = new JDialog(this, "Battle Log", false);
        popup.setSize(400, 300);
        popup.setLocation(0, 0);

        JTextArea textArea = new JTextArea(15, 30);
        textArea.setEditable(false);
        textArea.setText(battleLog);

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> popup.dispose());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(closeButton, BorderLayout.SOUTH);

        popup.add(panel);
        popup.setVisible(true);
    }

    private class BattleFieldPanel extends JPanel {
        private Image backgroundImage;
        private final int margin = 20;

        public BattleFieldPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
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
        SwingUtilities.invokeLater(() -> {
            BattleInterface view = new BattleInterface();
            new BattleController(view);
        });
    }
}
