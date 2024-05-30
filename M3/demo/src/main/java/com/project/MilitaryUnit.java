package com.project;

public interface MilitaryUnit {
    
    public abstract int attack();

    public abstract void takeDamage(int receivedDamage);

    public abstract int getActualArmor();

    public abstract int getFoodCost();

    public abstract int getWoodCost();

    public abstract int getIronCost();

    public abstract int getManaCost();

    public abstract int getChanceGeneratingWaste();

    public abstract int getChanceAttackAgain();

    public abstract void resetArmor();

    public abstract void setExperience(int experience);

    public abstract int getExperience();

    public abstract String getUnitID(); 

    public abstract void setUnitID(String unitID); 

    public abstract int getArmor(); 

    public abstract void setArmor(int armor); 

    public abstract int getBaseDamage(); 

    public abstract void setBaseDamage(int baseDamage); 

    public abstract boolean isSanctified(); 

    public abstract void setSanctified(boolean sanctified); 
}

