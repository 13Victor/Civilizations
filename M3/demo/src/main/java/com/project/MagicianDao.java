package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MagicianDAO {

    // Obtener un magician específico por civilization_id y unit_id
    public static magician getItem(int civilizationId, String unitId) {
        String sql = String.format("SELECT civilization_id, unit_id, armor, base_damage, experience FROM special_units_stats WHERE civilization_id = %d AND unit_id = '%s'", civilizationId, unitId);
        AppData db = AppData.getInstance();
        List<Map<String, Object>> results = db.query(sql);

        if (!results.isEmpty()) {
            Map<String, Object> row = results.get(0);
            int armor = (int) row.get("armor");
            int baseDamage = (int) row.get("base_damage");
            int experience = (int) row.get("experience");
            magician magician = new magician(armor, baseDamage);
            magician.setExperience(experience);
            return magician;
        }
        return null;
    }

    // Agregar un nuevo magician
    public static void addItem(int civilizationId, String unitId, magician magician) {
        String sql = String.format(Locale.US,
            "INSERT INTO special_units_stats (civilization_id, unit_id, armor, base_damage, experience) VALUES (%d, '%s', %d, %d, %d)",
            civilizationId, unitId, magician.getActualArmor(), magician.attack(), magician.getExperience()
        );
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    // Actualizar un magician existente
    public static void updateItem(int civilizationId, String unitId, magician magician) {
        String sql = String.format(Locale.US,
            "UPDATE special_units_stats SET armor = %d, base_damage = %d, experience = %d WHERE civilization_id = %d AND unit_id = '%s'",
            magician.getActualArmor(), magician.attack(), magician.getExperience(), civilizationId, unitId
        );
        AppData db = AppData.getInstance();
        db.update(sql);

        // Si la armadura es 0 o menos, elimina el magician
        if (magician.getActualArmor() <= 0) {
            deleteItem(civilizationId, unitId);
        }
    }

    // Eliminar un magician
    public static void deleteItem(int civilizationId, String unitId) {
        String sql = String.format("DELETE FROM special_units_stats WHERE civilization_id = %d AND unit_id = '%s'", civilizationId, unitId);
        AppData db = AppData.getInstance();
        db.update(sql);
    }

    // Obtener todos los magicians
    public static ArrayList<magician> getAll() {
        String sql = "SELECT civilization_id, unit_id, armor, base_damage, experience FROM special_units_stats WHERE type_unit = 'magician'";
        AppData db = AppData.getInstance();
        ArrayList<magician> list = new ArrayList<>();
        List<Map<String, Object>> results = db.query(sql);

        for (Map<String, Object> row : results) {
            int armor = (int) row.get("armor");
            int baseDamage = (int) row.get("base_damage");
            int experience = (int) row.get("experience");
            magician magician = new magician(armor, baseDamage);
            magician.setExperience(experience);
            list.add(magician);
        }
        return list;
    }
}
