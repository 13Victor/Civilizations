package com.project;

import java.util.ArrayList;

public class Civilization {
    private static Civilization instance;
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
    private ArrayList<MilitaryUnit> army;

    private Civilization() {
        army = new ArrayList<>(9);
    }

    public static Civilization getInstance() {
        if (instance == null) {
            instance = new Civilization();
        }
        return instance;
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

    public ArrayList<MilitaryUnit> getArmy() {
        return army;
    }

    public void setArmy(ArrayList<MilitaryUnit> army) {
        this.army = army;
    }


    public void newChurch() {
        try {
            if (food < Variables.FOOD_COST_CHURCH) {
                throw new ResourceException("Food",  Variables.FOOD_COST_CHURCH, food);
            }
            if (wood < Variables.WOOD_COST_CHURCH) {
                throw new ResourceException("wood",  Variables.WOOD_COST_CHURCH, wood);
            }
            if (iron < Variables.IRON_COST_CHURCH) {
                throw new ResourceException("Iron",  Variables.IRON_COST_CHURCH, iron);
            }
            if (mana < Variables.WOOD_COST_CHURCH) {
                throw new ResourceException("Mana",  Variables.MANA_COST_CHURCH, mana);
            }
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        church++;
        wood -= Variables.WOOD_COST_CHURCH;
        iron -= Variables.IRON_COST_CHURCH;
        food -= Variables.FOOD_COST_CHURCH;
        mana -= Variables.MANA_COST_CHURCH;
    }

    public void newMagicTower() {
        try {
            if (food < Variables.FOOD_COST_MAGICTOWER) {
                throw new ResourceException("Food", Variables.FOOD_COST_MAGICTOWER, food);
            }
            if (wood < Variables.WOOD_COST_MAGICTOWER) {
                throw new ResourceException("wood", Variables.WOOD_COST_MAGICTOWER, wood);
            }
            if (iron < Variables.IRON_COST_MAGICTOWER) {
                throw new ResourceException("Iron", Variables.IRON_COST_MAGICTOWER, iron);
            }
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        magicTower++;
        food -= Variables.FOOD_COST_MAGICTOWER;
        wood -= Variables.WOOD_COST_MAGICTOWER;
        iron -= Variables.IRON_COST_MAGICTOWER;
    }

    public void newFarm() {
        try {
            if (food < Variables.FOOD_COST_FARM) {
                throw new ResourceException("Food", Variables.FOOD_COST_FARM, food);
            }
            if (wood < Variables.WOOD_COST_FARM) {
                throw new ResourceException("wood", Variables.WOOD_COST_FARM, wood);
            }
            if (iron < Variables.IRON_COST_FARM) {
                throw new ResourceException("Iron", Variables.IRON_COST_FARM, iron);
            }
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        farm++;
        food -= Variables.FOOD_COST_FARM;
        wood -= Variables.WOOD_COST_FARM;
        iron -= Variables.IRON_COST_FARM;
    }

    public void newCarpentry() {
        try {
            if (food < Variables.FOOD_COST_CARPENTRY) {
                throw new ResourceException("Food", Variables.FOOD_COST_CARPENTRY, food);
            }
            if (wood < Variables.WOOD_COST_CARPENTRY) {
                throw new ResourceException("wood", Variables.WOOD_COST_CARPENTRY, wood);
            }
            if (iron < Variables.IRON_COST_CARPENTRY) {
                throw new ResourceException("Iron", Variables.IRON_COST_CARPENTRY, iron);
            }

        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        carpentry++;
        food -= Variables.FOOD_COST_CARPENTRY;
        wood -= Variables.WOOD_COST_CARPENTRY;
        iron -= Variables.IRON_COST_CARPENTRY;
    }

    public void newSmithy() {
        try{
            if (iron < Variables.IRON_COST_SMITHY) {
                throw new ResourceException("Iron", Variables.IRON_COST_SMITHY, iron);
            }
        }catch(ResourceException e){
            System.out.println(e.getMessage());
            return;
        }
        smithy++;
        iron -= Variables.IRON_COST_SMITHY;
    }

    public void upgradeTechnologyDefense() {
        if (technologyDefense >= 50) {
            System.out.println("Defense technology level has reached the limit");
            return;
        }
        int baseFoodCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_FOOD_COST;
        int plusFoodPercentage = Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_FOOD_COST * technologyDefense;
        int totalFoodCost = baseFoodCost + baseFoodCost*plusFoodPercentage/100;
        
        int baseWoodCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_WOOD_COST;
        int plusWoodPercentage = Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_WOOD_COST * technologyDefense;
        int totalWoodCost = baseWoodCost + baseWoodCost*plusWoodPercentage/100;

        int baseIronCost = Variables.UPGRADE_BASE_DEFENSE_TECHNOLOGY_IRON_COST;
        int plusIronPercentage = Variables.UPGRADE_PLUS_DEFENSE_TECHNOLOGY_IRON_COST * technologyDefense;
        int totalIronCost = baseIronCost + baseIronCost*plusIronPercentage/100;

        try {
            if (food < totalFoodCost) {
                throw new ResourceException("Food", totalFoodCost, food);
            }
            if (wood < totalWoodCost) {
                throw new ResourceException("wood", totalWoodCost, wood);
            }
            if (iron < totalIronCost) {
                throw new ResourceException("Iron", totalIronCost, iron);
            }
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        technologyDefense++;
        food -= totalFoodCost;
        wood -= totalWoodCost;
        iron -= totalIronCost;
    }

    public void upgradeTechnologyAttack() {
        if (technologyAttack >= 50) {
            System.out.println("Attack technology level has reached the limit");
            return;
        }
        int baseFoodCost = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_FOOD_COST;
        int plusFoodPercentage = Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_FOOD_COST * technologyDefense;
        int totalFoodCost = baseFoodCost + baseFoodCost*plusFoodPercentage/100;
        
        int baseWoodCost = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_WOOD_COST;
        int plusWoodPercentage = Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_WOOD_COST * technologyDefense;
        int totalWoodCost = baseWoodCost + baseWoodCost*plusWoodPercentage/100;

        int baseIronCost = Variables.UPGRADE_BASE_ATTACK_TECHNOLOGY_IRON_COST;
        int plusIronPercentage = Variables.UPGRADE_PLUS_ATTACK_TECHNOLOGY_IRON_COST * technologyDefense;
        int totalIronCost = baseIronCost + baseIronCost*plusIronPercentage/100;

        try {
            if (food < totalFoodCost) {
                throw new ResourceException("Food", totalFoodCost, food);
            }
            if (wood < totalWoodCost) {
                throw new ResourceException("wood", totalWoodCost, wood);
            }
            if (iron < totalIronCost) {
                throw new ResourceException("Iron", totalIronCost, iron);
            }
        } catch (ResourceException e) {
            System.out.println(e.getMessage());
            return;
        }
        technologyAttack++;
        food -= totalFoodCost;
        wood -= totalWoodCost;
        iron -= totalIronCost;
    }

    public int AddUnit(UnitTypes unitType, int amount) {
        int foodCost = 0;
        int woodCost = 0;
        int ironCost = 0;
        int manaCost = 0;

        int total = 0;
        
        for(int i = 0;i<amount;i++){
            try {
                if (unitType == UnitTypes.PRIEST && CountUnits(unitType) >= church) {
                    throw new BuildingException("To Train a new Priest","Churches");
                }
                if (unitType == UnitTypes.MAGICIAN && CountUnits(unitType) >= magicTower) {
                    throw new BuildingException("To Train a new Magician", "Magicians");
                }
            }
            catch (BuildingException e) {
                System.out.println(e.getMessage());
                return total;
            }
            switch (unitType) {
                case SWORDSMAN:
                    foodCost = Variables.FOOD_COST_SWORDSMAN;
                    woodCost = Variables.WOOD_COST_SWORDSMAN;
                    ironCost = Variables.IRON_COST_SWORDSMAN;
                    break;
                case SPEARMAN:
                    foodCost = Variables.FOOD_COST_SPEARMAN;
                    woodCost = Variables.WOOD_COST_SPEARMAN;
                    ironCost = Variables.IRON_COST_SPEARMAN;
                    break;
                case CROSSBOW:
                    foodCost = Variables.FOOD_COST_CROSSBOW;
                    woodCost = Variables.WOOD_COST_CROSSBOW;
                    ironCost = Variables.IRON_COST_CROSSBOW;
                    break;
                case CANNON:
                    foodCost = Variables.FOOD_COST_CANNON;
                    woodCost = Variables.WOOD_COST_CANNON;
                    ironCost = Variables.IRON_COST_CANNON;
                    break;
                case ARROWTOWER:
                    foodCost = Variables.FOOD_COST_ARROWTOWER;
                    woodCost = Variables.WOOD_COST_ARROWTOWER;
                    ironCost = Variables.IRON_COST_ARROWTOWER;
                    break;
                case CATAPULT:
                    foodCost = Variables.FOOD_COST_CATAPULT;
                    woodCost = Variables.WOOD_COST_CATAPULT;
                    ironCost = Variables.IRON_COST_CATAPULT;
                    break;
                case ROCKETLAUNCHERTOWER:
                    foodCost = Variables.FOOD_COST_ROCKETLAUNCHERTOWER;
                    woodCost = Variables.WOOD_COST_ROCKETLAUNCHERTOWER;
                    ironCost = Variables.IRON_COST_ROCKETLAUNCHERTOWER;
                    break;
                case MAGICIAN:
                    foodCost = Variables.FOOD_COST_MAGICIAN;
                    woodCost = Variables.WOOD_COST_MAGICIAN;
                    ironCost = Variables.IRON_COST_MAGICIAN;
                    manaCost = Variables.MANA_COST_MAGICIAN;             
                    break;
                case PRIEST:
                    foodCost = Variables.FOOD_COST_PRIEST;
                    woodCost = Variables.WOOD_COST_PRIEST;
                    ironCost = Variables.IRON_COST_PRIEST;
                    manaCost = Variables.MANA_COST_PRIEST;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + unitType);
            }
            try {
                if (wood < woodCost) {
                    throw new ResourceException("wood", woodCost, wood);
                }
                if (food < foodCost) {
                    throw new ResourceException("food", foodCost, food);
                }
                if (iron < ironCost) {
                    throw new ResourceException("iron", ironCost, iron);
                }
                if (mana < manaCost) {
                    throw new ResourceException("mana", manaCost, mana);
                }
            } catch (ResourceException e) {
                System.out.println(e.getMessage());
                return total;
            }
            switch (unitType) {
                case SWORDSMAN:
                    army.add(new Swordsman());
                    break;
                case SPEARMAN:
                    army.add(new Spearman());
                    break;
                case CROSSBOW:
                    army.add(new Crossbow());
                    break;
                case CANNON:
                    army.add(new Cannon()); 
                    break;               
                case ARROWTOWER:
                    army.add(new ArrowTower());
                    break;
                case CATAPULT:
                    army.add(new Catapult());
                    break;
                case ROCKETLAUNCHERTOWER:
                    army.add(new RocketLauncherTower());
                    break;
                case MAGICIAN:
                    //army.add(new Magician());
                    break;
                case PRIEST:
                    System.out.println("ENTRA PRIEST");
                    army.add(new Priest());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + unitType);
            }

            food -= foodCost;
            wood -= woodCost;
            iron -= ironCost;
            mana -= manaCost;
            total++;
        }
        return total;
    }

    public int CountUnits(UnitTypes unitType) {
        int result = 0;
        for (MilitaryUnit unit : army) {
            if (unit.getType() == unitType) {
                result++;
            }
        }
        return result;
    }

    public void GenerateResources(float deltaTime) {
        food += Math.ceil((Variables.CIVILIZATION_FOOD_GENERATED+Variables.CIVILIZATION_FOOD_GENERATED_PER_FARM*farm)/60.0*deltaTime);
        wood += Math.ceil((Variables.CIVILIZATION_WOOD_GENERATED+Variables.CIVILIZATION_WOOD_GENERATED_PER_CARPENTRY*carpentry)/60.0*deltaTime);
        iron += Math.ceil((Variables.CIVILIZATION_IRON_GENERATED+Variables.CIVILIZATION_IRON_GENERATED_PER_SMITHY*smithy)/60.0*deltaTime);
        mana += Math.ceil((Variables.CIVILIZATION_MANA_GENERATED_PER_MAGIC_TOWER*magicTower)/60.0*deltaTime);
    }

//Función  de momento no necesaria    private int calculateCost(int level) {
//        return 100 + (10 * level);
//    }

}