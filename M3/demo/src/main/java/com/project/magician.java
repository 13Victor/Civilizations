package com.project;

public class magician extends specialunit{

    public final int BASE_DAMAGE_MAGICIAN = 3000;

    public magician(int initialArmor, int attackmagicianlevel) {
        this.initialArmor = initialArmor;
        this.armor = 0; 
        this.baseDamage = BASE_DAMAGE_MAGICIAN + (int)(attackmagicianlevel*PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY)*BASE_DAMAGE_MAGICIAN/100;;
        this.experience = 0;
    }

  
    @Override
    public int attack(){
        int calculatedDamage = (int)(baseDamage + baseDamage * experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT / 100);
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
        return FOOD_COST_MAGICIAN;
    }

    @Override
    public int getWoodCost(){
        return WOOD_COST_MAGICIAN;
    }

    @Override
    public int getIronCost(){
        return IRON_COST_MAGICIAN;
    }

    @Override
    public int getManaCost(){
        return MANA_COST_MAGICIAN;
    }

    @Override
    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_MAGICIAN;
    }

    @Override
    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_MAGICIAN;
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
        return UnitTypes.CANNON;
    }

}
    
}
