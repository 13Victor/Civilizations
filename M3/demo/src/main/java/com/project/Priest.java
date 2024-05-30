package com.project;

public class Priest extends SpecialUnit {
    private static final int ARMOR_PRIEST = 30;
    private static final int BASE_DAMAGE_PRIEST = 100;

    public Priest(int technologyDefenseLevel, int technologyAttackLevel) {
        this.armor = ARMOR_PRIEST + (int)(technologyDefenseLevel * PLUS_ARMOR_PRIEST_BY_TECHNOLOGY) * ARMOR_PRIEST / 100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_PRIEST + (int)(technologyAttackLevel * PLUS_ATTACK_PRIEST_BY_TECHNOLOGY) * BASE_DAMAGE_PRIEST / 100;
        this.experience = 0;
        this.unitID = generateUnitID();
    }

    public Priest() {
        this.armor = ARMOR_PRIEST;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_PRIEST;
        this.experience = 0;
        this.sanctified = false;
        this.unitID = generateUnitID();
    }

    @Override
    public int attack() {
        int calculatedDamage = (int)(baseDamage + baseDamage * experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT / 100);
        return calculatedDamage;
    }

    @Override
    public void takeDamage(int receivedDamage) {
        armor -= receivedDamage;
    }

    @Override
    public int getActualArmor() {
        return (int)(armor + initialArmor * experience * PLUS_ARMOR_UNIT_PER_EXPERIENCE_POINT / 100);
    }

    @Override
    public int getFoodCost() {
        return FOOD_COST_PRIEST;
    }

    @Override
    public int getWoodCost() {
        return WOOD_COST_PRIEST;
    }

    @Override
    public int getIronCost() {
        return IRON_COST_PRIEST;
    }

    @Override
    public int getManaCost() {
        return MANA_COST_PRIEST;
    }

    @Override
    public int getChanceGeneratingWaste() {
        return CHANCE_GENERATNG_WASTE_PRIEST;
    }

    @Override
    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_PRIEST;
    }

    @Override
    public void resetArmor() {
        armor = initialArmor;
    }

    @Override
    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public int getExperience() {
        return experience;
    }

    private String generateUnitID() {
        return "PRS" + System.currentTimeMillis();
    }
}
