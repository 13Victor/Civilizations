package com.project;

import java.util.ArrayList;
import java.util.Random;

public class Battle {

    private ArrayList<MilitaryUnit>[] armies;
    private StringBuilder battleDevelopment;
    private int[][] initialCostFleet;
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron;
    private int enemyDrops;
    private int civilizationDrops;
    private int[][] resourcesLooses;
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;
    private Random random;

    @SuppressWarnings("unchecked")
    public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
        this.armies = new ArrayList[2];
        this.armies[0] = civilizationArmy;
        this.armies[1] = enemyArmy;
        this.battleDevelopment = new StringBuilder();
        this.initialCostFleet = new int[2][3];
        this.wasteWoodIron = new int[2];
        this.resourcesLooses = new int[2][4];
        this.initialArmies = new int[2][9];
        this.actualNumberUnitsCivilization = new int[9];
        this.actualNumberUnitsEnemy = new int[9];
        this.random = new Random();
        initInitialArmies();
        updateResourcesLooses();
    }

    private void initInitialArmies() {
        for (int i = 0; i < 9; i++) {
            initialArmies[0][i] = armies[0][i].size();
            initialArmies[1][i] = armies[1][i].size();
        }
        initialNumberUnitsCivilization = getTotalUnits(armies[0]);
        initialNumberUnitsEnemy = getTotalUnits(armies[1]);
    }

    private int getTotalUnits(ArrayList<MilitaryUnit>[] army) {
        int total = 0;
        for (ArrayList<MilitaryUnit> units : army) {
            total += units.size();
        }
        return total;
    }

    private void updateResourcesLooses() {
        for (int i = 0; i < 4; i++) {
            resourcesLooses[0][i] = 0;
            resourcesLooses[1][i] = 0;
        }
    }

    public String getBattleReport(int battles) {
        return "Resumen de la batalla";
    }

    public String getBattleDevelopment() {
        return battleDevelopment.toString();
    }

    public void startBattle() {
        while (getTotalUnits(armies[0]) > 0.2 * initialNumberUnitsCivilization &&
               getTotalUnits(armies[1]) > 0.2 * initialNumberUnitsEnemy) {
            if (random.nextBoolean()) {
                handleAttack(armies[0], armies[1]);
            } else {
                handleAttack(armies[1], armies[0]);
            }
        }
        determineBattleOutcome();
    }

    private void handleAttack(ArrayList<MilitaryUnit>[] attackers, ArrayList<MilitaryUnit>[] defenders) {
        int attackerGroup = getGroupAttacker(attackers);
        int defenderGroup = getGroupDefender(defenders);

        if (attackerGroup >= 0 && defenderGroup >= 0) {
            MilitaryUnit attacker = attackers[attackerGroup].get(random.nextInt(attackers[attackerGroup].size()));
            MilitaryUnit defender = defenders[defenderGroup].get(random.nextInt(defenders[defenderGroup].size()));

            attackUnit(attacker, defender, defenders[defenderGroup]);
        }
    }

    private void attackUnit(MilitaryUnit attacker, MilitaryUnit defender, ArrayList<MilitaryUnit> defenderGroup) {
        int damage = attacker.attack();
        defender.takeDamage(damage);

        if (defender.getActualArmor() <= 0) {
            defenderGroup.remove(defender);
            // Manejar residuos y posibles ataques adicionales
        }
    }

    private int getGroupDefender(ArrayList<MilitaryUnit>[] army) {
        int totalUnits = getTotalUnits(army);
        int randomUnit = random.nextInt(totalUnits);
        int cumulativeSum = 0;

        for (int i = 0; i < army.length; i++) {
            cumulativeSum += army[i].size();
            if (randomUnit < cumulativeSum) {
                return i;
            }
        }
        return -1;
    }

    private int getGroupAttacker(ArrayList<MilitaryUnit>[] army) {
        int totalUnits = getTotalUnits(army);
        int randomUnit = random.nextInt(totalUnits);
        int cumulativeSum = 0;

        for (int i = 0; i < army.length; i++) {
            cumulativeSum += army[i].size();
            if (randomUnit < cumulativeSum) {
                return i;
            }
        }
        return -1;
    }

    private void determineBattleOutcome() {
        // Determina el resultado de la batalla
    }
}
