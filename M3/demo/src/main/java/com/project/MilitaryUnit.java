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

    public abstract String getUnitID(); // Añadir método para obtener el ID de la unidad

    public abstract void setUnitID(String unitID); // Añadir método para establecer el ID de la unidad

    public abstract int getArmor(); // Añadir método para obtener la armadura actual

    public abstract void setArmor(int armor); // Añadir método para establecer la armadura

    public abstract int getBaseDamage(); // Añadir método para obtener el daño base

    public abstract void setBaseDamage(int baseDamage); // Añadir método para establecer el daño base

    public abstract boolean isSanctified(); // Añadir método para verificar si la unidad está santificada

    public abstract void setSanctified(boolean sanctified); // Añadir método para establecer si la unidad está santificada
}

