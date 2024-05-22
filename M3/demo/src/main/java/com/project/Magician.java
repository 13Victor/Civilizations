package com.project;

public class Magician extends Specialunit{
    
    public Magician(int initialArmor, int baseDamage) {
        this.initialArmor = initialArmor;
        this.armor = 0; 
        this.baseDamage = baseDamage;
        this.experience = 0;
    }

    @Override
    public int attack(){
        int calculatedDamage = 0;
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
        return FOOD_COST_PRIEST;
    }

    @Override
    public int getWoodCost(){
        return WOOD_COST_PRIEST;
    }

    @Override
    public int getIronCost(){
        return IRON_COST_PRIEST;
    }

    @Override
    public int getManaCost(){
        return MANA_COST_PRIEST;
    }

    @Override
    public int getChanceGeneratinWaste(){
        return CHANCE_GENERATNG_WASTE_PRIEST;
    }

    @Override
    public int getChanceAttackAgain(){
        return CHANCE_ATTACK_AGAIN_PRIEST;
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
