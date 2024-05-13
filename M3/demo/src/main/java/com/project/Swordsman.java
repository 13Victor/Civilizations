package com.project;

public class Swordsman extends AttackUnit{
  
    private final static int ARMOR_SWORDSMAN = 400;
    private final static int BASE_DAMAGE_SWORDSMAN = 80;

    
    public Swordsman(int technologyDefenseLevel, int technologyAttackLevel){
        this.armor = ARMOR_SWORDSMAN + (int)(technologyDefenseLevel*PLUS_ARMOR_SWORDSMAN_BY_TECHNOLOGY)*ARMOR_SWORDSMAN/100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_SWORDSMAN + (int)(technologyAttackLevel*PLUS_ATTACK_SWORDSMAN_BY_TECHNOLOGY)*BASE_DAMAGE_SWORDSMAN/100;
        this.experience = 0;
        this.sanctified = false;
    }

    public Swordsman(){
        this.armor = ARMOR_SWORDSMAN;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_SWORDSMAN;
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
        return FOOD_COST_SWORDSMAN;
    }

    @Override
    public int getWoodCost(){
        return WOOD_COST_SWORDSMAN;
    }

    @Override
    public int getIronCost(){
        return IRON_COST_SWORDSMAN;
    }

    @Override
    public int getManaCost(){
        return MANA_COST_SWORDSMAN;
    }

    @Override
    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_SWORDSMAN;
    }

    @Override
    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_SWORDSMAN;
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
        return UnitTypes.SWORDSMAN;
    }

}
