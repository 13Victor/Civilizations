package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class PriestDao {

    // Obtener un Priest específico por civilization_id y unit_id
    public static Priest getItem(int civilizationId, String unitId) {
        String sql = String.format("SELECT civilization_id, unit_id, armor, base_damage, experience FROM special_units_stats WHERE civilization_id = %d AND unit_id = '%s'", civilizationId, unitId);
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        if (!results.isEmpty()) {
            Map<String, Object> row = results.get(0);
            int armor = (int) row.get("armor");
            int baseDamage = (int) row.get("base_damage");
            int experience = (int) row.get("experience");
            Priest priest = new Priest(armor, baseDamage);
            priest.setExperience(experience);
            return priest;
        }
        return null;
    }

    // Agregar un nuevo Priest
    public static void addItem(int civilizationId, String unitId, Priest priest) {
        String sql = String.format(Locale.US,
            "INSERT INTO special_units_stats (civilization_id, unit_id, armor, base_damage, experience) VALUES (%d, '%s', %d, %d, %d)",
            civilizationId, unitId, priest.getActualArmor(), priest.attack(), priest.getExperience()
        );
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    // Actualizar un Priest existente
    public static void updateItem(int civilizationId, String unitId, Priest priest) {
        String sql = String.format(Locale.US,
            "UPDATE special_units_stats SET armor = %d, base_damage = %d, experience = %d WHERE civilization_id = %d AND unit_id = '%s'",
            priest.getActualArmor(), priest.attack(), priest.getExperience(), civilizationId, unitId
        );
        AppData db = AppData.getInstance();
        db.update(sql);

        // Si la armadura es 0 o menos, elimina el Priest
        if (priest.getActualArmor() <= 0) {
            deleteItem(civilizationId, unitId);
        }
    }

    // Eliminar un Priest
    public static void deleteItem(int civilizationId, String unitId) {
        String sql = String.format("DELETE FROM special_units_stats WHERE civilization_id = %d AND unit_id = '%s'", civilizationId, unitId);
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    // Obtener todos los Priests
    public static ArrayList<Priest> getAll() {
        String sql = "SELECT civilization_id, unit_id, armor, base_damage, experience FROM special_units_stats WHERE type_unit = 'Priest'";
        AppData db = AppData.getInstance();
        ArrayList<Priest> list = new ArrayList<>();
        List<Map<String, Object>> results = db.query(sql);

        for (Map<String, Object> row : results) {
            int armor = (int) row.get("armor");
            int baseDamage = (int) row.get("base_damage");
            int experience = (int) row.get("experience");
            Priest priest = new Priest(armor, baseDamage);
            priest.setExperience(experience);
            list.add(priest);
        }
        return list;
    }
}
