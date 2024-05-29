package com.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BattleController {
    private BattleInterface view;
    private Civilization civilization;
    private CivilizationDao civilizationDao;
    private List<MilitaryUnit> enemyUnits;
    private Battle battle;

    @SuppressWarnings("unchecked")
    public BattleController(BattleInterface view) {
        this.view = view;
        this.civilizationDao = new CivilizationDao();
        this.civilization = civilizationDao.getSave(1); // Cargar una civilización con ID 1 (ejemplo)
        this.enemyUnits = createEnemyUnits();

        // Convertimos las listas de unidades a arreglos de ArrayList
        ArrayList<MilitaryUnit>[] civilizationArmy = convertListToArrayListArray(civilization.getOwnArmy());
        ArrayList<MilitaryUnit>[] enemyArmy = convertListToArrayListArray(enemyUnits);

        this.battle = new Battle(civilizationArmy, enemyArmy);

        // Inicializar vista con datos de la civilización
        view.setMaterials(civilization.getFood(), civilization.getWood(), civilization.getIron(), civilization.getMana());
        view.setCivilizationUnits(civilization.getOwnArmy());
        view.setEnemyUnits(enemyUnits);

        // Añadir listeners a los botones
        view.addUpgradeButtonListener(new UpgradeButtonListener());
        view.addStatsButtonListener(new StatsButtonListener());
        view.addSanctifyButtonListener(new SanctifyButtonListener());

        // Iniciar los timers
        view.startMaterialTimer(new MaterialTimerListener());
        view.startPopupTimer(new PopupTimerListener());
    }

    private ArrayList<MilitaryUnit>[] convertListToArrayListArray(List<MilitaryUnit> units) {
        ArrayList<MilitaryUnit>[] army = new ArrayList[9]; // Asumiendo que hay 9 tipos de unidades
        for (int i = 0; i < 9; i++) {
            army[i] = new ArrayList<>();
        }
        for (MilitaryUnit unit : units) {
            int unitType = getUnitType(unit);
            if (unitType != -1) { // Asegurarse de que el tipo de unidad es reconocido
                army[unitType].add(unit);
            }
        }
        return army;
    }

    private int getUnitType(MilitaryUnit unit) {
        if (unit instanceof Swordsman) return 0;
        if (unit instanceof Spearman) return 1;
        if (unit instanceof Crossbow) return 2;
        if (unit instanceof Cannon) return 3;
        if (unit instanceof ArrowTower) return 4;
        if (unit instanceof Catapult) return 5;
        if (unit instanceof RocketLauncherTower) return 6;
        if (unit instanceof Priest) return 7;
        if (unit instanceof Magician) return 8;
        return -1; // Tipo desconocido
    }

    private class UpgradeButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                civilization.upgradeTechnologyAttack();
                civilization.upgradeTechnologyDefense();
                civilizationDao.addSave(civilization); // Guardar cambios en la base de datos
                view.showMessage("Civilization upgraded successfully!", "Upgrade", JOptionPane.INFORMATION_MESSAGE);
            } catch (ResourceException ex) {
                view.showMessage("Not enough resources to upgrade civilization.", "Upgrade Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class StatsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showStats(civilization.getStats());
        }
    }

    private class SanctifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (MilitaryUnit unit : civilization.getOwnArmy()) {
                unit.setSanctified(true);
            }
            civilizationDao.addSave(civilization); // Guardar cambios en la base de datos
            view.showMessage("Units sanctified successfully!", "Sanctify", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private class MaterialTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            civilization.setFood(civilization.getFood() + 10);
            civilization.setWood(civilization.getWood() + 5);
            civilization.setIron(civilization.getIron() + 3);
            civilization.setMana(civilization.getMana() + 2);

            view.updateMaterials(civilization.getFood(), civilization.getWood(), civilization.getIron(), civilization.getMana());

            civilizationDao.addSave(civilization); // Guardar cambios en la base de datos
        }
    }

    private class PopupTimerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            battle.startBattle();
            StringBuilder battleLog = new StringBuilder();
            battleLog.append(battle.getBattleDevelopment());
            view.showBattleLog(battleLog.toString());
            civilizationDao.addSave(civilization); // Guardar el estado de la civilización después de la batalla
        }
    }

    private List<MilitaryUnit> createEnemyUnits() {
        List<MilitaryUnit> enemies = new ArrayList<>();
        enemies.add(new Cannon(5, 5)); // Añadir unidades enemigas según tu lógica
        // Añadir más unidades enemigas según sea necesario
        return enemies;
    }
}
