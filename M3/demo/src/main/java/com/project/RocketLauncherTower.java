package com.project;

public class RocketLauncherTower extends DefenseUnit{
  
    private final static int ARMOR_ROCKETLAUNCHERTOWER = 7000;
    private final static int BASE_DAMAGE_ROCKETLAUNCHERTOWER = 2000;

    
    public RocketLauncherTower(int defenseTechnologyLevel, int attackTechnologyLevel){
        this.armor = ARMOR_ROCKETLAUNCHERTOWER + (int)(defenseTechnologyLevel*PLUS_ARMOR_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*ARMOR_ROCKETLAUNCHERTOWER/100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_ROCKETLAUNCHERTOWER + (int)(attackTechnologyLevel*PLUS_ATTACK_ROCKETLAUNCHERTOWER_BY_TECHNOLOGY)*BASE_DAMAGE_ROCKETLAUNCHERTOWER/100;
        this.experience = 0;
        this.sanctified = false;
    }

    @Override
    public int attack(){
        int calculatedDamage = (int)(baseDamage + baseDamage * experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT / 100);
        if (sanctified){
            calculatedDamage += baseDamage * PLUS_ATTACK_UNIT_SANCTIFIED /100;
        }
        return calculatedDamage;  
    }

    @Override
    public void takeDamage(int receivedDamage) {
        armor -= receivedDamage;
    }

    @Override
    public int getActualArmor() {
        return (int)(armor+initialArmor*experience*PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT/100);
    }

    @Override
    public int getFoodCost(){
        return FOOD_COST_ROCKETLAUNCHERTOWER;
    }

    @Override
    public int getWoodCost(){
        return WOOD_COST_ROCKETLAUNCHERTOWER;
    }

    @Override
    public int getIronCost(){
        return IRON_COST_ROCKETLAUNCHERTOWER;
    }

    @Override
    public int getManaCost(){
        return MANA_COST_ROCKETLAUNCHERTOWER;
    }

    @Override
    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_ROCKETLAUNCHERTOWER;
    }

    @Override
    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ROCKETLAUNCHERTOWER;
    }

    @Override
    public void resetArmor() {
        armor = initialArmor;
    }

    @Override
    public void setExperience(int exp) {
        experience = exp;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    @Override
    public UnitTypes getType() {
        return UnitTypes.ROCKETLAUNCHERTOWER;
    }

}
