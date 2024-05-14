package com.project;

public class ArrowTower extends DefenseUnit{
  
    private final static int ARMOR_ARROWTOWER = 200;
    private final static int BASE_DAMAGE_ARROWTOWER = 80;

    
    public ArrowTower(int technologyDefenseLevel, int technologyAttackLevel){
        this.armor = ARMOR_ARROWTOWER + (int)(technologyDefenseLevel*PLUS_ARMOR_ARROWTOWER_BY_TECHNOLOGY)*ARMOR_ARROWTOWER/100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_ARROWTOWER + (int)(technologyAttackLevel*PLUS_ATTACK_ARROWTOWER_BY_TECHNOLOGY)*BASE_DAMAGE_ARROWTOWER/100;
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
        return FOOD_COST_ARROWTOWER;
    }

    @Override
    public int getWoodCost(){
        return WOOD_COST_ARROWTOWER;
    }

    @Override
    public int getIronCost(){
        return IRON_COST_ARROWTOWER;
    }

    @Override
    public int getManaCost(){
        return MANA_COST_ARROWTOWER;
    }

    @Override
    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_ARROWTOWER;
    }

    @Override
    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_ARROWTOWER;
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


}
