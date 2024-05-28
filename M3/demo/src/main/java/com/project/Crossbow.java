package com.project;

public class Crossbow extends AttackUnit {
    private static final int ARMOR_CROSSBOW = 6000;
    private static final int BASE_DAMAGE_CROSSBOW = 1000;

    public Crossbow(int technologyDefenseLevel, int technologyAttackLevel) {
        this.armor = ARMOR_CROSSBOW + (int)(technologyDefenseLevel * PLUS_ARMOR_CROSSBOW_BY_TECHNOLOGY) * ARMOR_CROSSBOW / 100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_CROSSBOW + (int)(technologyAttackLevel * PLUS_ATTACK_CROSSBOW_BY_TECHNOLOGY) * BASE_DAMAGE_CROSSBOW / 100;
        this.experience = 0;
        this.sanctified = false;
        this.unitID = generateUnitID();
    }

    @Override
    public int attack() {
        int calculatedDamage = (int)(baseDamage + baseDamage * experience * PLUS_ATTACK_UNIT_PER_EXPERIENCE_POINT / 100);
        if (sanctified) {
            calculatedDamage += baseDamage * PLUS_ATTACK_UNIT_SANCTIFIED / 100;
        }
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
        return FOOD_COST_CROSSBOW;
    }

    @Override
    public int getWoodCost() {
        return WOOD_COST_CROSSBOW;
    }

    @Override
    public int getIronCost() {
        return IRON_COST_CROSSBOW;
    }

    @Override
    public int getManaCost() {
        return MANA_COST_CROSSBOW;
    }

    @Override
    public int getChanceGeneratingWaste() {
        return CHANCE_GENERATNG_WASTE_CROSSBOW;
    }

    @Override
    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_CROSSBOW;
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
        return "CRB" + System.currentTimeMillis();
    }
}
