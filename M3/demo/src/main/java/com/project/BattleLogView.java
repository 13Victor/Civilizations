package com.project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

public class BattleLogView extends JFrame {

    public BattleLogView(List<Map<String, Object>> civilizationUnits, List<Map<String, Object>> enemyUnits) {
        setTitle("Battle Log");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);

        JPanel battlePanel = createBattlePanel(civilizationUnits, enemyUnits);
        add(new JScrollPane(battlePanel));

        setVisible(true);
    }

    private JPanel createBattlePanel(List<Map<String, Object>> civilizationUnits, List<Map<String, Object>> enemyUnits) {
        JPanel panel = new BackgroundPanel("path/to/your/background/image.png");
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon"));
        topPanel.add(iconLabel, BorderLayout.CENTER);

        panel.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Civilization Army", "Units", "Enemy Units", "Enemy Army"};
        Object[][] data = new Object[civilizationUnits.size()][4];

        for (int i = 0; i < civilizationUnits.size(); i++) {
            Map<String, Object> civUnit = civilizationUnits.get(i);
            Map<String, Object> enemyUnit = i < enemyUnits.size() ? enemyUnits.get(i) : null;

            data[i][0] = civUnit.get("type_unit");
            data[i][1] = civUnit.get("unit_count");
            data[i][2] = enemyUnit != null ? enemyUnit.get("unit_count") : null;
            data[i][3] = enemyUnit != null ? enemyUnit.get("type_unit") : null;
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                System.err.println("Error: No se pudo cargar la imagen de fondo.");
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
