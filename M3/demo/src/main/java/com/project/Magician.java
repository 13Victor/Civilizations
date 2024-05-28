package com.project;

public class Magician extends SpecialUnit {
    private static final int ARMOR_MAGICIAN = 50;
    private static final int BASE_DAMAGE_MAGICIAN = 200;

    public Magician(int technologyDefenseLevel, int technologyAttackLevel) {
        this.armor = ARMOR_MAGICIAN + (int)(technologyDefenseLevel * PLUS_ARMOR_MAGICIAN_BY_TECHNOLOGY) * ARMOR_MAGICIAN / 100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_MAGICIAN + (int)(technologyAttackLevel * PLUS_ATTACK_MAGICIAN_BY_TECHNOLOGY) * BASE_DAMAGE_MAGICIAN / 100;
        this.experience = 0;
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
        return FOOD_COST_MAGICIAN;
    }

    @Override
    public int getWoodCost() {
        return WOOD_COST_MAGICIAN;
    }

    @Override
    public int getIronCost() {
        return IRON_COST_MAGICIAN;
    }

    @Override
    public int getManaCost() {
        return MANA_COST_MAGICIAN;
    }

    @Override
    public int getChanceGeneratingWaste() {
        return CHANCE_GENERATNG_WASTE_MAGICIAN;
    }

    @Override
    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_MAGICIAN;
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
        return "MAG" + System.currentTimeMillis();
    }
}
