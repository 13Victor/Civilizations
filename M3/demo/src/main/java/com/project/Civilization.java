package com.project;
import java.util.ArrayList;



public class Civilization {
    private static final double UPGRADE_TECH_COST_INCREASE_PERCENTAGE = 0.10;
    private int upgradeDefenseTechnologyIronCost = 100;
    private int upgradeAttackTechnologyIronCost = 100;
    private int upgradeDefenseTechnologyWoodCost = 0;
    private int upgradeAttackTechnologyWoodCost = 0;

    //Constantes de unidades de ataque

    //Constantes de SWORDSMAN
    private static final int FOOD_COST_SWORDSMAN = 8000;
    private static final int WOOD_COST_SWORDSMAN = 3000;
    private static final int IRON_COST_SWORDSMAN = 50;
    //Constantes de SPEARMAN
    private static final int FOOD_COST_SPEARMAN = 5000;
    private static final int WOOD_COST_SPEARMAN = 6500;
    private static final int IRON_COST_SPEARMAN = 6500;
    //Constantes de CROSSBOW
    private static final int WOOD_COST_CROSSBOW = 45000;
    private static final int IRON_COST_CROSSBOW = 7000;
    //Constantes de CANNON
    private static final int WOOD_COST_CANNON = 30000;
    private static final int IRON_COST_CANNON = 15000;
    //Constantes de MAGICIAN
    private static final int MAGICIAN_WOOD_COST = 20;
    private static final int MAGICIAN_MANA_COST = 10;
    //Constantes de PRIEST
    private static final int PRIEST_WOOD_COST = 15;
    private static final int PRIEST_IRON_COST = 5;

    private int technologyDefense;
    private int technologyAttack;
    private int wood;
    private int iron;
    private int food;
    private int mana;
    private int magicTower;
    private int church;
    private int farm;
    private int smithy;
    private int carpentry;
    private int battles;
    private ArrayList<MilitaryUnit>[] army;

    @SuppressWarnings("unchecked")
    public Civilization() {
        army = new ArrayList[9];
        for (int i = 0; i < 9; i++) {
            army[i] = new ArrayList<MilitaryUnit>();
        }
    }

    // Getters and Setters

    public int getTechnologyDefense() {
        return technologyDefense;
    }

    public void setTechnologyDefense(int technologyDefense) {
        this.technologyDefense = technologyDefense;
    }

    public int getTechnologyAttack() {
        return technologyAttack;
    }

    public void setTechnologyAttack(int technologyAttack) {
        this.technologyAttack = technologyAttack;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getIron() {
        return iron;
    }

    public void setIron(int iron) {
        this.iron = iron;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMagicTower() {
        return magicTower;
    }

    public void setMagicTower(int magicTower) {
        this.magicTower = magicTower;
    }

    public int getChurch() {
        return church;
    }

    public void setChurch(int church) {
        this.church = church;
    }

    public int getFarm() {
        return farm;
    }

    public void setFarm(int farm) {
        this.farm = farm;
    }

    public int getSmithy() {
        return smithy;
    }

    public void setSmithy(int smithy) {
        this.smithy = smithy;
    }

    public int getCarpentry() {
        return carpentry;
    }

    public void setCarpentry(int carpentry) {
        this.carpentry = carpentry;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public ArrayList<MilitaryUnit>[] getArmy() {
        return army;
    }

    public void setArmy(ArrayList<MilitaryUnit>[] army) {
        this.army = army;
    }

    public void upgradeTechnologyDefense() throws ResourceException {
        int currentCost = upgradeDefenseTechnologyIronCost;
        if (iron < currentCost || wood < upgradeDefenseTechnologyWoodCost) {
            throw new ResourceException("Not enough resources to upgrade defense technology.");
        }
        upgradeDefenseTechnologyIronCost += (int) (currentCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeDefenseTechnologyWoodCost += (int) (upgradeDefenseTechnologyWoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
    }

    public void upgradeTechnologyAttack() throws ResourceException {
        int currentCost = upgradeAttackTechnologyIronCost;
        if (iron < currentCost || wood < upgradeAttackTechnologyWoodCost) {
            throw new ResourceException("Not enough resources to upgrade attack technology.");
        }
        upgradeAttackTechnologyIronCost += (int) (currentCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeAttackTechnologyWoodCost += (int) (upgradeAttackTechnologyWoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
    }

    public void newSwordsman(int n) throws ResourceException {
        int availableUnits = Math.min(Math.min(food / FOOD_COST_SWORDSMAN, wood / WOOD_COST_SWORDSMAN), iron / IRON_COST_SWORDSMAN);
        int unitsToAdd = Math.min(n, availableUnits);
        if (unitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any swordsman.");
        }
    }

    public void newSpearman(int n) throws ResourceException {
        int availableUnits = Math.min(Math.min(food / FOOD_COST_SPEARMAN, wood / WOOD_COST_SPEARMAN), iron / IRON_COST_SPEARMAN);
        int unitsToAdd = Math.min(n, availableUnits);
        if (unitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any spearman.");
        }
    }

    public void newCrossbow(int n) throws ResourceException {
        int availableUnits = Math.min(wood / WOOD_COST_CROSSBOW, iron / IRON_COST_CROSSBOW);
        int UnitsToAdd = Math.min(n, availableUnits);
        if (UnitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any crossbow");
        }
    }

    public void newCannon(int n) throws ResourceException {
        int availableUnits = Math.min(wood / WOOD_COST_CANNON, iron / IRON_COST_CANNON);
        int UnitsToAdd = Math.min(n, availableUnits);
        if (UnitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any cannon");
        }
    }

    public void newMagician(int n) throws ResourceException, BuildingException {
        if (magicTower == 0) {
            throw new BuildingException("You need at least one magic tower to recruit magicians.");
        }
        int availableUnits = Math.min(wood / MAGICIAN_WOOD_COST, mana / MAGICIAN_MANA_COST);
        int unitsToAdd = Math.min(n, availableUnits);
        if (unitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any magicians.");
        }
        // Lógica para añadir unidades de magos
    }

    public void newPriest(int n) throws ResourceException, BuildingException {
        if (church == 0) {
            throw new BuildingException("You need at least one church to recruit priests.");
        }
        int availableUnits = Math.min(wood / PRIEST_WOOD_COST, iron / PRIEST_IRON_COST);
        int unitsToAdd = Math.min(n, availableUnits);
        if (unitsToAdd == 0) {
            throw new ResourceException("Not enough resources to recruit any priests.");
        }
        // Lógica para añadir unidades de sacerdotes
    }

    public void printStats() {
        System.out.println("***************************CIVILIZATION STATS***************************");
        System.out.println("--------------------------------------------------TECHNOLOGY----------------------------------------");
        System.out.println("Atack\tDefense");
        System.out.println(technologyAttack + "\t" + technologyDefense);
        System.out.println("---------------------------------------------------BUILDINGS----------------------------------------");
        System.out.println("Farm\tSmithy\tCarpentry\tMagic Tower\tChurch");
        System.out.println(farm + "\t" + smithy + "\t" + carpentry + "\t\t" + magicTower + "\t\t\t" + church);
        System.out.println("----------------------------------------------------DEFENSES----------------------------------------");
        System.out.println("Arrow Tower\tCatapult\tRocket Launcher");
        System.out.println(army[4].size() + "\t\t\t" + army[5].size() + "\t\t" + army[6].size());
        System.out.println("------------------------------------------------ATTACK UNITS----------------------------------------");
        System.out.println("Swordsman\tSpearman\tCrossbow\tCannon");
        System.out.println(army[0].size() + "\t\t\t" + army[1].size() + "\t\t\t" + army[2].size() + "\t\t" + army[3].size());
        System.out.println("----------------------------------------------ESPECIAL UNITS----------------------------------------");
        System.out.println("Magician\tPriest");
        System.out.println(army[7].size() + "\t\t\t" + army[8].size());
        System.out.println("---------------------------------------------------RESOURCES----------------------------------------");
        System.out.println("Food\t\tWood\t\tIron\t\tMana");
        System.out.println(food + "\t\t" + wood + "\t\t" + iron + "\t\t" + mana);
        System.out.println("----------------------------------------GENERATION RESOURCES----------------------------------------");
        System.out.println("Food\t\tWood\t\tIron\t\tMana");
        // Aquí deberías tener atributos que indiquen la generación de recursos, que no están definidos en la pregunta original
        System.out.println("8000\t\t5000\t\t1500\t\t0");
    }
}
