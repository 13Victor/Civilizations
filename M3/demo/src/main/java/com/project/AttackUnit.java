package com.project;

public abstract class AttackUnit implements MilitaryUnit, Variables {
    
    protected int armor;
    protected int initialArmor;
    protected int baseDamage;
    protected int experience;
    protected boolean sanctified;
    protected String unitID; 

    
    @Override
    public String getUnitID() {
        return unitID;
    }

    @Override
    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    @Override
    public int getArmor() {
        return armor;
    }

    @Override
    public void setArmor(int armor) {
        this.armor = armor;
    }

    @Override
    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    @Override
    public boolean isSanctified() {
        return sanctified;
    }

    @Override
    public void setSanctified(boolean sanctified) {
        this.sanctified = sanctified;
    }

   
    @Override
    public abstract int attack();

    @Override
    public abstract void takeDamage(int receivedDamage);

    @Override
    public abstract int getActualArmor();

    @Override
    public abstract int getFoodCost();

    @Override
    public abstract int getWoodCost();

    @Override
    public abstract int getIronCost();

    @Override
    public abstract int getManaCost();

    @Override
    public abstract int getChanceGeneratingWaste();

    @Override
    public abstract int getChanceAttackAgain();

    @Override
    public abstract void resetArmor();

    @Override
    public abstract void setExperience(int experience);

    @Override
    public abstract int getExperience();
}
