package com.project;

public interface MilitaryUnit {
    
    public abstract int attack();

    public abstract void takeDamage(int recivedDamage);

    public abstract int getAcActualArmor();

    public abstract int getFoodCost();

    public abstract int getWoodCost();

    public abstract int getIronCost();

    public abstract int getManaCost();

    public abstract int getChanceGeneratinWaste();

    public abstract int getChanceAttackAgain();

    public abstract void resetArmor();

    public abstract void setExperience(int n);

    public abstract void getExperience();
}
