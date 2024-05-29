package com.project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BattleLogView {

    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Battle Log");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        // Crear el panel principal con pestañas
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Crear y añadir una pestaña con datos específicos de una batalla
        JPanel battlePanel = createBattlePanel();
        tabbedPane.add("Tab 1", new JScrollPane(battlePanel));
        
        // Añadir las pestañas al marco principal
        frame.add(tabbedPane);
        
        // Hacer visible el marco
        frame.setVisible(true);
    }
    
    private static JPanel createBattlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Crear un panel para los iconos y título
        JPanel topPanel = new JPanel(new BorderLayout());
        
        // Añadir el icono de batalla (dos espadas cruzadas)
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(UIManager.getIcon("OptionPane.informationIcon")); // Puedes cambiar este icono por uno personalizado
        topPanel.add(iconLabel, BorderLayout.CENTER);
        
        panel.add(topPanel, BorderLayout.NORTH);
        
        // Crear la tabla de reporte de batalla
        String[] columnNames = {"Civilization Army", "Units", "Drops", "Enemy Army"};
        Object[][] data = {
            {"Swordsman", 4, 4, "Swordsman", 8, 0},
            {"Spearman", 2, 1, "Spearman", 1, 0},
            {"Crossbow", 0, 0, "Crossbow", 1, 0}
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        
        // Añadir la tabla a un scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
}
