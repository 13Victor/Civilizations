package com.project;

//Clases Magician, Priest
//Para cada tipo de unidad especial, crearemos una clase que heredará de la clase SpecialUnit.
//Todas ellas dispondrán de un único constructor al que le pasaremos como argumentos int armor , int
//baseDamage.
//El funcionamiento del constructor de nuestras unidades especiales, será igual que el de nuestro 
//ejército de ataque. 
//Cada una de estas clases tendrá que implementar los métodos definidos en la interfaz MilitaryUnit.
public class Priest extends specialunit{

    public final int CHANCE_ATTACK_AGAIN_PRIEST = 0;

    public Priest(int initialArmor, int attackmagicianlevel) {
        this.initialArmor = initialArmor;
        this.armor = 0; 
        this.baseDamage = 0;
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

    @Override
    public UnitTypes getType() {
        return UnitTypes.CANNON;
    }

}

}
