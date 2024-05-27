package com.project;

import java.util.ArrayList;
import java.util.Random;

public class Battle implements Variables {

    private ArrayList<MilitaryUnit>[][] armies;
    private StringBuilder battleDevelopment;
    private int[][] initialCostFleet;
    private int initialNumberUnitsCivilization;
    private int initialNumberUnitsEnemy;
    private int[] wasteWoodIron;
    private int enemyDrops;
    private int civilizationDrops;
    private int[][] resourcesLosses;
    private int[][] initialArmies;
    private int[] actualNumberUnitsCivilization;
    private int[] actualNumberUnitsEnemy;
    private Random random;
    private int numBattle;
    private int woodAcquired;
    private int ironAcquired;
    private boolean win;
    private ArrayList<Integer> civilizationLosses;
    private ArrayList<Integer> enemyLosses;
    private ArrayList<MilitaryUnit>[] civilizationArmy;
    private ArrayList<MilitaryUnit>[] enemyArmy;
    private String detailedReport;

    @SuppressWarnings("unchecked")
    public Battle(ArrayList<MilitaryUnit>[] civilizationArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
        this.civilizationArmy = civilizationArmy;
        this.enemyArmy = enemyArmy;
        this.armies = (ArrayList<MilitaryUnit>[][]) new ArrayList[2][];
        this.armies[0] = civilizationArmy;
        this.armies[1] = enemyArmy;
        this.battleDevelopment = new StringBuilder();
        this.initialCostFleet = new int[2][3];
        this.wasteWoodIron = new int[2];
        this.resourcesLosses = new int[2][4];
        this.initialArmies = new int[2][9];
        this.actualNumberUnitsCivilization = new int[9];
        this.actualNumberUnitsEnemy = new int[9];
        this.random = new Random();
        this.civilizationLosses = new ArrayList<>();
        this.enemyLosses = new ArrayList<>();
        initInitialArmies();
        updateResourcesLosses();
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

    private void updateResourcesLosses() {
        for (int i = 0; i < 4; i++) {
            resourcesLosses[0][i] = 0;
            resourcesLosses[1][i] = 0;
        }
    }

    public String getBattleReport() {
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
        // Inicializa las pérdidas
        civilizationLosses.clear();
        enemyLosses.clear();
    
        // Calcula las pérdidas de la civilización
        for (int i = 0; i < initialArmies[0].length; i++) {
            int losses = initialArmies[0][i] - (i < civilizationArmy.length ? civilizationArmy[i].size() : 0);
            civilizationLosses.add(losses);
        }
    
        // Calcula las pérdidas del enemigo
        for (int i = 0; i < initialArmies[1].length; i++) {
            int losses = initialArmies[1][i] - (i < enemyArmy.length ? enemyArmy[i].size() : 0);
            enemyLosses.add(losses);
        }
    
        // Calcula recursos adquiridos
        woodAcquired = calculateResourcesAcquired("wood");
        ironAcquired = calculateResourcesAcquired("iron");
    
        // Determina si ganó la civilización
        win = resourcesLosses[0][3] < resourcesLosses[1][3];
    
        // Genera el reporte detallado
        generateDetailedReport();
    }
    
    private int calculateResourcesAcquired(String resourceType) {
        int resources = 0;
        for (int i = 0; i < civilizationLosses.size(); i++) {
            resources += getUnitResourceCost(i, resourceType) * civilizationLosses.get(i) * PERCENTATGE_WASTE / 100;
        }
        for (int i = 0; i < enemyLosses.size(); i++) {
            resources += getUnitResourceCost(i, resourceType) * enemyLosses.get(i) * PERCENTATGE_WASTE / 100;
        }
        return resources;
    }
    
    private int getUnitResourceCost(int unitIndex, String resourceType) {
        switch (resourceType) {
            case "wood":
                return Variables.WOOD_COST_UNITS[unitIndex];
            case "iron":
                return Variables.IRON_COST_UNITS[unitIndex];
            default:
                return 0;
        }
    }
    
    private void generateDetailedReport() {
        StringBuilder report = new StringBuilder();
        report.append("Resumen de la Batalla:\n");
        report.append("Pérdidas de la Civilización:\n");
        for (int i = 0; i < civilizationLosses.size(); i++) {
            report.append("Unidad ").append(i).append(": ").append(civilizationLosses.get(i)).append("\n");
        }
        report.append("Pérdidas del Enemigo:\n");
        for (int i = 0; i < enemyLosses.size(); i++) {
            report.append("Unidad ").append(i).append(": ").append(enemyLosses.get(i)).append("\n");
        }
        report.append("Recursos adquiridos:\n");
        report.append("Madera: ").append(woodAcquired).append("\n");
        report.append("Hierro: ").append(ironAcquired).append("\n");
        report.append("Resultado de la batalla: ").append(win ? "Victoria" : "Derrota").append("\n");
        detailedReport = report.toString();
    }
    

    // Getters y setters
    public int getNumBattle() {
        return numBattle;
    }

    public void setNumBattle(int numBattle) {
        this.numBattle = numBattle;
    }

    public int getWoodAcquired() {
        return woodAcquired;
    }

    public void setWoodAcquired(int woodAcquired) {
        this.woodAcquired = woodAcquired;
    }

    public int getIronAcquired() {
        return ironAcquired;
    }

    public void setIronAcquired(int ironAcquired) {
        this.ironAcquired = ironAcquired;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public ArrayList<Integer> getCivilizationLosses() {
        return civilizationLosses;
    }

    public void setCivilizationLosses(ArrayList<Integer> civilizationLosses) {
        this.civilizationLosses = civilizationLosses;
    }

    public ArrayList<Integer> getEnemyLosses() {
        return enemyLosses;
    }

    public void setEnemyLosses(ArrayList<Integer> enemyLosses) {
        this.enemyLosses = enemyLosses;
    }

    public ArrayList<MilitaryUnit>[] getCivilizationArmy() {
        return civilizationArmy;
    }

    public void setCivilizationArmy(ArrayList<MilitaryUnit>[] civilizationArmy) {
        this.civilizationArmy = civilizationArmy;
        this.armies[0] = civilizationArmy;
    }

    public ArrayList<MilitaryUnit>[] getEnemyArmy() {
        return enemyArmy;
    }

    public void setEnemyArmy(ArrayList<MilitaryUnit>[] enemyArmy) {
        this.enemyArmy = enemyArmy;
        this.armies[1] = enemyArmy;
    }

    public ArrayList<MilitaryUnit>[][] getArmies() {
        return armies;
    }

    public void setArmies(ArrayList<MilitaryUnit>[][] armies) {
        this.armies = armies;
    }

    public void setBattleDevelopment(StringBuilder battleDevelopment) {
        this.battleDevelopment = battleDevelopment;
    }

    public int[][] getInitialCostFleet() {
        return initialCostFleet;
    }

    public void setInitialCostFleet(int[][] initialCostFleet) {
        this.initialCostFleet = initialCostFleet;
    }

    public int getInitialNumberUnitsCivilization() {
        return initialNumberUnitsCivilization;
    }

    public void setInitialNumberUnitsCivilization(int initialNumberUnitsCivilization) {
        this.initialNumberUnitsCivilization = initialNumberUnitsCivilization;
    }

    public int getInitialNumberUnitsEnemy() {
        return initialNumberUnitsEnemy;
    }

    public void setInitialNumberUnitsEnemy(int initialNumberUnitsEnemy) {
        this.initialNumberUnitsEnemy = initialNumberUnitsEnemy;
    }

    public int[] getWasteWoodIron() {
        return wasteWoodIron;
    }

    public void setWasteWoodIron(int[] wasteWoodIron) {
        this.wasteWoodIron = wasteWoodIron;
    }

    public int getEnemyDrops() {
        return enemyDrops;
    }

    public void setEnemyDrops(int enemyDrops) {
        this.enemyDrops = enemyDrops;
    }

    public int getCivilizationDrops() {
        return civilizationDrops;
    }

    public void setCivilizationDrops(int civilizationDrops) {
        this.civilizationDrops = civilizationDrops;
    }

    public int[][] getResourcesLosses() {
        return resourcesLosses;
    }

    public void setResourcesLosses(int[][] resourcesLosses) {
        this.resourcesLosses = resourcesLosses;
    }

    public int[][] getInitialArmies() {
        return initialArmies;
    }

    public void setInitialArmies(int[][] initialArmies) {
        this.initialArmies = initialArmies;
    }

    public int[] getActualNumberUnitsCivilization() {
        return actualNumberUnitsCivilization;
    }

    public void setActualNumberUnitsCivilization(int[] actualNumberUnitsCivilization) {
        this.actualNumberUnitsCivilization = actualNumberUnitsCivilization;
    }

    public int[] getActualNumberUnitsEnemy() {
        return actualNumberUnitsEnemy;
    }

    public void setActualNumberUnitsEnemy(int[] actualNumberUnitsEnemy) {
        this.actualNumberUnitsEnemy = actualNumberUnitsEnemy;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getDetailedReport() {
        return detailedReport;
    }

    public void setDetailedReport(String detailedReport) {
        this.detailedReport = detailedReport;
    }
}
