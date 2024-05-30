package com.project;

public class GameController {
    private GameInterface gameInterface;
    private CivilizationDao dao;
    private Civilization civilization;

    public GameController(GameInterface gameInterface, CivilizationDao dao, Civilization civilization) {
        this.gameInterface = gameInterface;
        this.dao = dao;
        this.civilization = civilization;
        gameInterface.setController(this);
    }

    public void createUnit(String unitName, int count, int food, int wood, int iron, int mana) {
        int totalFood = food * count;
        int totalWood = wood * count;
        int totalIron = iron * count;
        int totalMana = mana * count;

        if (dao.hasEnoughResources(totalFood, totalWood, totalIron, totalMana, civilization.getSavedId())) {
            // Restar recursos
            dao.updateResources(totalFood, totalWood, totalIron, totalMana, civilization.getSavedId());

            // Añadir unidad al ejército
            for (int i = 0; i < count; i++) {
                dao.insertUnit(unitName, civilization.getSavedId());
                civilization.getOwnArmy().add(createUnitInstance(unitName));
            }

            // Actualizar interfaz
            civilization.setFood(civilization.getFood() - totalFood);
            civilization.setWood(civilization.getWood() - totalWood);
            civilization.setIron(civilization.getIron() - totalIron);
            civilization.setMana(civilization.getMana() - totalMana);

            gameInterface.updateMaterials(civilization.getFood(), civilization.getWood(), civilization.getIron(), civilization.getMana());
        } else {
            gameInterface.showError("Not enough resources to create units.");
        }
    }

    private MilitaryUnit createUnitInstance(String unitName) {
        switch (unitName) {
            case "Swordsman":
                return new Swordsman();
            case "Spearman":
                return new Spearman();
            case "Crossbow":
                return new Crossbow();
            case "Cannon":
                return new Cannon();
            default:
                throw new IllegalArgumentException("Unknown unit type: " + unitName);
        }
    }
}
