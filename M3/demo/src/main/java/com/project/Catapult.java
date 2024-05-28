package com.project;

public class Catapult extends DefenseUnit {
    private static final int ARMOR_CATAPULT = 1200;
    private static final int BASE_DAMAGE_CATAPULT = 250;

    public Catapult(int technologyDefenseLevel, int technologyAttackLevel) {
        this.armor = ARMOR_CATAPULT + (int)(technologyDefenseLevel * PLUS_ARMOR_CATAPULT_BY_TECHNOLOGY) * ARMOR_CATAPULT / 100;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_CATAPULT + (int)(technologyAttackLevel * PLUS_ATTACK_CATAPULT_BY_TECHNOLOGY) * BASE_DAMAGE_CATAPULT / 100;
        this.experience = 0;
        this.sanctified = false;
        this.unitID = generateUnitID();
    }

    public Catapult() {
        this.armor = ARMOR_CATAPULT;
        this.initialArmor = this.armor;
        this.baseDamage = BASE_DAMAGE_CATAPULT;
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
        return FOOD_COST_CATAPULT;
    }

    @Override
    public int getWoodCost() {
        return WOOD_COST_CATAPULT;
    }

    @Override
    public int getIronCost() {
        return IRON_COST_CATAPULT;
    }

    @Override
    public int getManaCost() {
        return MANA_COST_CATAPULT;
    }

    @Override
    public int getChanceGeneratingWaste() {
        return CHANCE_GENERATNG_WASTE_CATAPULT;
    }

    @Override
    public int getChanceAttackAgain() {
        return CHANCE_ATTACK_AGAIN_CATAPULT;
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
        return "CTP" + System.currentTimeMillis();
    }
}
