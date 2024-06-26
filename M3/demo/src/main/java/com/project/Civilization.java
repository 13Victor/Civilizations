package com.project;

import java.util.ArrayList;
import java.util.List;

public class Civilization implements Variables {
    private static final double UPGRADE_TECH_COST_INCREASE_PERCENTAGE = 0.10;
    private int upgradeDefenseTechnologyIronCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
    private int upgradeAttackTechnologyIronCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
    private int upgradeDefenseTechnologyWoodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
    private int upgradeAttackTechnologyWoodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;
    private int upgradeDefenseTechnologyFoodCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
    private int upgradeAttackTechnologyFoodCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST; 
    
    
    
    
    private int savedId;
    private String name;
    private int wood;
    private int iron;
    private int food;
    private int mana;
    private int magicTower;
    private int church;
    private int farm;
    private int smithy;
    private int carpentry;
    private int technologyDefense;
    private int technologyAttack;
    private int battles;
    private List<MilitaryUnit> ownArmy;
    private List<SpecialUnit> specialUnits;
    private List<DefenseUnit> defenseUnits;
    private List<MilitaryUnit>[] army;

    @SuppressWarnings("unchecked")
    public Civilization() {
        ownArmy = new ArrayList<>();
        specialUnits = new ArrayList<>();
        defenseUnits = new ArrayList<>();
    }

    public Civilization(String name) {
        this.name = name;
        this.food = 0;
        this.wood = 0;
        this.iron = 0;
        this.mana = 0;
        this.ownArmy = new ArrayList<>();
        this.specialUnits = new ArrayList<>();
        this.defenseUnits = new ArrayList<>();
    }


    public int getSavedId() {
        return savedId;
    }

    public void setSavedId(int savedId) {
        this.savedId = savedId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public List<MilitaryUnit> getOwnArmy() {
        return ownArmy;
    }

    public void setOwnArmy(List<MilitaryUnit> ownArmy) {
        this.ownArmy = ownArmy;
    }

    public List<SpecialUnit> getSpecialUnits() {
        return specialUnits;
    }

    public void setSpecialUnits(List<SpecialUnit> specialUnits) {
        this.specialUnits = specialUnits;
    }

    public List<DefenseUnit> getDefenseUnits() {
        return defenseUnits;
    }

    public void setDefenseUnits(List<DefenseUnit> defenseUnits) {
        this.defenseUnits = defenseUnits;
    }

    public String getStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("Civilization Stats:\n");
        stats.append("Food: ").append(food).append("\n");
        stats.append("Wood: ").append(wood).append("\n");
        stats.append("Iron: ").append(iron).append("\n");
        stats.append("Mana: ").append(mana).append("\n");
        stats.append("Technology Defense Level: ").append(technologyDefense).append("\n");
        stats.append("Technology Attack Level: ").append(technologyAttack).append("\n");
        stats.append("Army Size: ").append(getTotalArmySize()).append("\n");
        return stats.toString();
    }

    private int getTotalArmySize() {
        int total = 0;
        for (List<MilitaryUnit> units : army) {
            total += units.size();
        }
        return total;
    }

    
    public void upgradeTechnologyDefense() throws ResourceException {
        int currentIronCost = upgradeDefenseTechnologyIronCost;
        int currentWoodCost = upgradeDefenseTechnologyWoodCost;
        int currentFoodCost = upgradeDefenseTechnologyFoodCost;
        
        if (!hasEnoughResources(currentFoodCost, currentWoodCost, currentIronCost, 0)) {
            throw new ResourceException("Not enough resources to upgrade defense technology.");
        }
        
        iron -= currentIronCost;
        wood -= currentWoodCost;
        food -= currentFoodCost;

        upgradeDefenseTechnologyIronCost += (int) (currentIronCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeDefenseTechnologyWoodCost += (int) (currentWoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeDefenseTechnologyFoodCost += (int) (currentFoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
    }

    public void upgradeTechnologyAttack() throws ResourceException {
        int currentIronCost = upgradeAttackTechnologyIronCost;
        int currentWoodCost = upgradeAttackTechnologyWoodCost;
        int currentFoodCost = upgradeAttackTechnologyFoodCost;
        
        if (!hasEnoughResources(currentFoodCost, currentWoodCost, currentIronCost, 0)) {
            throw new ResourceException("Not enough resources to upgrade attack technology.");
        }
        
        iron -= currentIronCost;
        wood -= currentWoodCost;
        food -= currentFoodCost;

        upgradeAttackTechnologyIronCost += (int) (currentIronCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeAttackTechnologyWoodCost += (int) (currentWoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
        upgradeAttackTechnologyFoodCost += (int) (currentFoodCost * UPGRADE_TECH_COST_INCREASE_PERCENTAGE);
    }

    public boolean hasEnoughResources(int foodCost, int woodCost, int ironCost, int manaCost) {
        return food >= foodCost && wood >= woodCost && iron >= ironCost && mana >= manaCost;
    }

    public void consumeResources(int foodCost, int woodCost, int ironCost, int manaCost) {
        this.food -= foodCost;
        this.wood -= woodCost;
        this.iron -= ironCost;
        this.mana -= manaCost;
    }

    public void newSwordsman(int n) throws ResourceException {
        int foodCost = FOOD_COST_SWORDSMAN * n;
        int woodCost = WOOD_COST_SWORDSMAN * n;
        int ironCost = IRON_COST_SWORDSMAN * n;
        int manaCost = 0; 

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to recruit swordsman.");
        }

        for (int i = 0; i < n; i++) {
            army[0].add(new Swordsman(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newSpearman(int n) throws ResourceException {
        int foodCost = FOOD_COST_SPEARMAN * n;
        int woodCost = WOOD_COST_SPEARMAN * n;
        int ironCost = IRON_COST_SPEARMAN * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to recruit spearman.");
        }

        for (int i = 0; i < n; i++) {
            army[1].add(new Spearman(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newCrossbow(int n) throws ResourceException {
        int foodCost = 0;
        int woodCost = WOOD_COST_CROSSBOW * n;
        int ironCost = IRON_COST_CROSSBOW * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to recruit crossbow.");
        }

        for (int i = 0; i < n; i++) {
            army[2].add(new Spearman(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newCannon(int n) throws ResourceException {
        int foodCost = 0;
        int woodCost = WOOD_COST_CANNON * n;
        int ironCost = IRON_COST_CANNON * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to recruit cannon.");
        }

        for (int i = 0; i < n; i++){
            army[3].add(new Cannon(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newArrowTower(int n) throws ResourceException {
        int foodCost = 0;
        int woodCost = WOOD_COST_ARROWTOWER * n;
        int ironCost = IRON_COST_ARROWTOWER * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build arrow tower.");
        }

        for (int i = 0; i < n; i++){
            army[4].add(new ArrowTower(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newCatapult(int n) throws ResourceException {
        int foodCost = 0;
        int woodCost = WOOD_COST_CATAPULT * n;
        int ironCost = IRON_COST_CATAPULT * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build a catapult.");

        }

        for (int i = 0; i < n; i++){
            army[5].add(new Catapult(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newRocketLauncherTower(int n) throws ResourceException {
        int foodCost = 0;
        int woodCost = WOOD_COST_ROCKETLAUNCHERTOWER * n;
        int ironCost = IRON_COST_ROCKETLAUNCHERTOWER * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)){
            throw new ResourceException("Not enough resources to build a rocket launcher tower.");
        }

        for (int i = 0; i < n; i++){
            army[6].add(new RocketLauncherTower(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newPriest(int n) throws ResourceException, BuildingException {
        int foodCost = FOOD_COST_PRIEST * n;
        int woodCost = WOOD_COST_PRIEST * n;
        int ironCost = IRON_COST_PRIEST * n;
        int manaCost = MANA_COST_PRIEST * n;
    
        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to recruit priests.");
        }
    
        if (church == 0) {
            throw new BuildingException("You need at least one church to recruit priests.");
        }
    
        for (int i = 0; i < n; i++) {
            army[7].add(new Priest(technologyDefense, technologyAttack));
        }
    
        consumeResources(foodCost, woodCost, ironCost, manaCost);
    }

    public void newMagician(int n) throws ResourceException, BuildingException {
        int foodCost = FOOD_COST_MAGICIAN * n;
        int woodCost = WOOD_COST_MAGICIAN * n;
        int ironCost = 0;
        int manaCost = MANA_COST_MAGICIAN * n;


        if (magicTower == 0) {
            throw new BuildingException("You need at least one magic tower to recruit magicians.");
        }

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)){
            throw new ResourceException("Not enough resources to recruit magician.");
        }
        
        for (int i = 0; i < n; i++){
            army[8].add(new Magician(technologyDefense, technologyAttack));
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        
    }

    public void newSmithy(int n) throws ResourceException {
        int foodCost = FOOD_COST_SMITHY * n;
        int woodCost = WOOD_COST_SMITHY * n;
        int ironCost = IRON_COST_SMITHY * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build a smithy.");
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        smithy+= n;
    }

    public void newCarpentry(int n) throws ResourceException { 
        int foodCost = FOOD_COST_CARPENTRY * n;
        int woodCost = WOOD_COST_CARPENTRY * n;
        int ironCost = IRON_COST_CARPENTRY * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build a carpentry.");
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        carpentry+= n;
    }

    public void newFarm(int n) throws ResourceException {
        int foodCost = FOOD_COST_FARM * n;
        int woodCost = WOOD_COST_FARM * n;
        int ironCost = IRON_COST_FARM * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build a Farm.");
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        farm+= n;
    }

    public void newMagicTower(int n) throws ResourceException {
        int foodCost = FOOD_COST_MAGICTOWER * n;
        int woodCost = WOOD_COST_MAGICTOWER * n;
        int ironCost = IRON_COST_MAGICTOWER * n;
        int manaCost = 0;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough resources to build a Magic Tower.");
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        magicTower+= n;
    }


    public void newChurch(int n) throws ResourceException {

        int foodCost = FOOD_COST_CHURCH * n;
        int woodCost = WOOD_COST_CHURCH * n;
        int ironCost = IRON_COST_CHURCH * n;
        int manaCost = MANA_COST_CHURCH * n;

        if (!hasEnoughResources(foodCost, woodCost, ironCost, manaCost)) {
            throw new ResourceException("Not enough materials to build a church.");
        }

        consumeResources(foodCost, woodCost, ironCost, manaCost);
        church+= n;
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
        
        System.out.println("8000\t\t5000\t\t1500\t\t0");
    }
}
