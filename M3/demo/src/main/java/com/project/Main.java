package com.project;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        CivilizationDao dao = new CivilizationDao();
        SwingUtilities.invokeLater(() -> {
            GameInterface view = new GameInterface();
            Civilization civilization = new Civilization("New Game"); // Asignar una nueva civilización o cargar una existente
            GameController controller = new GameController(view, dao, civilization);
            view.setController(controller);
        });
    }
}
