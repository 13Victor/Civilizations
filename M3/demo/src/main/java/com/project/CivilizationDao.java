package com.project;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class CivilizationDao {

    private String title(String str) {
        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;
        for (char c : str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }
        return titleCase.toString();
    }


    public int getCivilizationIdByName(String name) {
        String sql = "SELECT civilization_id FROM civilization_stats WHERE name = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("civilization_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Indica que no se encontró
    }

    public List<String> getSavedGames() {
        List<String> savedGames = new ArrayList<>();
        String sql = "SELECT name FROM civilization_stats";
        try (Connection connection = AppData.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                savedGames.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedGames;
    }

    public boolean hasEnoughResources(int food, int wood, int iron, int mana, int civilizationId) {
        String sql = "SELECT food_amount, wood_amount, iron_amount, mana_amount FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    int currentFood = rs.getInt("food_amount");
                    int currentWood = rs.getInt("wood_amount");
                    int currentIron = rs.getInt("iron_amount");
                    int currentMana = rs.getInt("mana_amount");
                    return currentFood >= food && currentWood >= wood && currentIron >= iron && currentMana >= mana;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateResources(int food, int wood, int iron, int mana, int civilizationId) {
        String sql = "UPDATE civilization_stats SET food_amount = food_amount - ?, wood_amount = wood_amount - ?, iron_amount = iron_amount - ?, mana_amount = mana_amount - ? WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, food);
            statement.setInt(2, wood);
            statement.setInt(3, iron);
            statement.setInt(4, mana);
            statement.setInt(5, civilizationId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUnit(String unitName, int civilizationId) {
        String sql = "INSERT INTO attack_units_stats (civilization_id, unit_id, type_unit, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            statement.setString(2, generateUnitId());
            statement.setString(3, unitName);
            statement.setInt(4, 0); // Armor por defecto
            statement.setInt(5, 0); // Base damage por defecto
            statement.setInt(6, 0); // Experience por defecto
            statement.setString(7, "NO"); // Sanctified por defecto
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateUnitId() {
        return "UNIT" + System.currentTimeMillis();
    }

    public int getFood(int civilizationId) {
        return getResourceAmount("food_amount", civilizationId);
    }

    public int getWood(int civilizationId) {
        return getResourceAmount("wood_amount", civilizationId);
    }

    public int getIron(int civilizationId) {
        return getResourceAmount("iron_amount", civilizationId);
    }

    public int getMana(int civilizationId) {
        return getResourceAmount("mana_amount", civilizationId);
    }

    private int getResourceAmount(String resource, int civilizationId) {
        String sql = "SELECT " + resource + " FROM civilization_stats WHERE civilization_id = ?";
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(resource);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Civilization getSaveByName(String name) {
        Connection con = AppData.getInstance().getConnection();
        String civilizationSql = "SELECT * FROM civilization_stats WHERE name = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Civilization civ = null;

        try {
            ps = con.prepareStatement(civilizationSql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                civ = new Civilization();
                civ.setSavedId(rs.getInt("civilization_id"));
                civ.setName(rs.getString("name"));
                civ.setWood(rs.getInt("wood_amount"));
                civ.setIron(rs.getInt("iron_amount"));
                civ.setFood(rs.getInt("food_amount"));
                civ.setMana(rs.getInt("mana_amount"));
                civ.setMagicTower(rs.getInt("magicTower_counter"));
                civ.setChurch(rs.getInt("church_counter"));
                civ.setFarm(rs.getInt("farm_counter"));
                civ.setSmithy(rs.getInt("smithy_counter"));
                civ.setCarpentry(rs.getInt("carpentry_counter"));
                civ.setTechnologyDefense(rs.getInt("technology_defense_level"));
                civ.setTechnologyAttack(rs.getInt("technology_attack_level"));
                civ.setBattles(rs.getInt("battles_counter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (con != null) con.close(); // Cerrar la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Cargar unidades de ataque
        if (civ != null) {
            civ.setOwnArmy(getUnits(con, civ.getSavedId()));
            civ.setSpecialUnits(getSpecialUnits(con, civ.getSavedId()));
            civ.setDefenseUnits(getDefenseUnits(con, civ.getSavedId()));
        }

        return civ;
    }

    public void addSave(Civilization save) {
        Connection con = AppData.getInstance().getConnection();
        String insertCivilizationSql = "INSERT INTO civilization_stats (name, wood_amount, iron_amount, food_amount, mana_amount, magicTower_counter, church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String getLastIdSql = "SELECT civilization_id FROM civilization_stats ORDER BY civilization_id DESC FETCH FIRST 1 ROW ONLY";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int civId = -1;

        try {
            ps = con.prepareStatement(insertCivilizationSql);
            ps.setString(1, save.getName());
            ps.setInt(2, save.getWood());
            ps.setInt(3, save.getIron());
            ps.setInt(4, save.getFood());
            ps.setInt(5, save.getMana());
            ps.setInt(6, save.getMagicTower());
            ps.setInt(7, save.getChurch());
            ps.setInt(8, save.getFarm());
            ps.setInt(9, save.getSmithy());
            ps.setInt(10, save.getCarpentry());
            ps.setInt(11, save.getTechnologyDefense());
            ps.setInt(12, save.getTechnologyAttack());
            ps.setInt(13, save.getBattles());
            ps.executeUpdate();
            con.commit();

            Statement s = con.createStatement();
            rs = s.executeQuery(getLastIdSql);
            if (rs.next()) {
                civId = rs.getInt("civilization_id");
                save.setSavedId(civId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (con != null) con.close(); // Cerrar la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Insertar unidades de ataque
        insertUnits(con, civId, save.getOwnArmy());

        // Insertar unidades especiales
        insertSpecialUnits(con, civId, save.getSpecialUnits());

        // Insertar unidades de defensa
        insertDefenseUnits(con, civId, save.getDefenseUnits());
    }

    private void insertUnits(Connection con, int civId, List<MilitaryUnit> units) {
        String unitSql = "INSERT INTO attack_units_stats (civilization_id, unit_id, type_unit, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        for (MilitaryUnit unit : units) {
            try (PreparedStatement ps = con.prepareStatement(unitSql)) {
                ps.setInt(1, civId);
                ps.setString(2, unit.getUnitID());
                ps.setString(3, title(unit.getClass().getSimpleName()));
                ps.setInt(4, unit.getArmor());
                ps.setInt(5, unit.getBaseDamage());
                ps.setInt(6, unit.getExperience());
                ps.setString(7, unit.isSanctified() ? "YES" : "NO");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertSpecialUnits(Connection con, int civId, List<SpecialUnit> units) {
        String specialUnitSql = "INSERT INTO special_units_stats (civilization_id, unit_id, type_unit, armor, base_damage, experience) VALUES (?, ?, ?, ?, ?, ?)";
        for (SpecialUnit unit : units) {
            try (PreparedStatement ps = con.prepareStatement(specialUnitSql)) {
                ps.setInt(1, civId);
                ps.setString(2, unit.getUnitID());
                ps.setString(3, title(unit.getClass().getSimpleName()));
                ps.setInt(4, unit.getArmor());
                ps.setInt(5, unit.getBaseDamage());
                ps.setInt(6, unit.getExperience());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertDefenseUnits(Connection con, int civId, List<DefenseUnit> units) {
        String defenseUnitSql = "INSERT INTO defense_unit_stats (civilization_id, unit_id, type_unit, armor, base_damage, experience, sanctified) VALUES (?, ?, ?, ?, ?, ?, ?)";
        for (DefenseUnit unit : units) {
            try (PreparedStatement ps = con.prepareStatement(defenseUnitSql)) {
                ps.setInt(1, civId);
                ps.setString(2, unit.getUnitID());
                ps.setString(3, title(unit.getClass().getSimpleName()));
                ps.setInt(4, unit.getArmor());
                ps.setInt(5, unit.getBaseDamage());
                ps.setInt(6, unit.getExperience());
                ps.setString(7, unit.isSanctified() ? "YES" : "NO");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Civilization getSave(int civilizationId) {
        Connection con = AppData.getInstance().getConnection();
        String civilizationSql = "SELECT * FROM civilization_stats WHERE civilization_id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Civilization civ = null;

        try {
            ps = con.prepareStatement(civilizationSql);
            ps.setInt(1, civilizationId);
            rs = ps.executeQuery();
            if (rs.next()) {
                civ = new Civilization();
                civ.setSavedId(rs.getInt("civilization_id"));
                civ.setName(rs.getString("name"));
                civ.setWood(rs.getInt("wood_amount"));
                civ.setIron(rs.getInt("iron_amount"));
                civ.setFood(rs.getInt("food_amount"));
                civ.setMana(rs.getInt("mana_amount"));
                civ.setMagicTower(rs.getInt("magicTower_counter"));
                civ.setChurch(rs.getInt("church_counter"));
                civ.setFarm(rs.getInt("farm_counter"));
                civ.setSmithy(rs.getInt("smithy_counter"));
                civ.setCarpentry(rs.getInt("carpentry_counter"));
                civ.setTechnologyDefense(rs.getInt("technology_defense_level"));
                civ.setTechnologyAttack(rs.getInt("technology_attack_level"));
                civ.setBattles(rs.getInt("battles_counter"));
            }

            // Cargar unidades de ataque
            if (civ != null) {
                civ.setOwnArmy(getUnits(con, civilizationId));
                civ.setSpecialUnits(getSpecialUnits(con, civilizationId));
                civ.setDefenseUnits(getDefenseUnits(con, civilizationId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (rs != null) rs.close();
                if (con != null) con.close(); // Cerrar la conexión aquí
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return civ;
    }

    private List<MilitaryUnit> getUnits(Connection con, int civilizationId) {
        String unitSql = "SELECT * FROM attack_units_stats WHERE civilization_id = ?";
        List<MilitaryUnit> units = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(unitSql)) {
            ps.setInt(1, civilizationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MilitaryUnit unit = createAttackUnit(rs.getString("type_unit"));
                    unit.setUnitID(rs.getString("unit_id"));
                    unit.setArmor(rs.getInt("armor"));
                    unit.setBaseDamage(rs.getInt("base_damage"));
                    unit.setExperience(rs.getInt("experience"));
                    unit.setSanctified(rs.getString("sanctified").equals("YES"));
                    units.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    private List<SpecialUnit> getSpecialUnits(Connection con, int civilizationId) {
        String specialUnitSql = "SELECT * FROM special_units_stats WHERE civilization_id = ?";
        List<SpecialUnit> units = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(specialUnitSql)) {
            ps.setInt(1, civilizationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SpecialUnit unit = createSpecialUnit(rs.getString("type_unit"));
                    unit.setUnitID(rs.getString("unit_id"));
                    unit.setArmor(rs.getInt("armor"));
                    unit.setBaseDamage(rs.getInt("base_damage"));
                    unit.setExperience(rs.getInt("experience"));
                    units.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    private List<DefenseUnit> getDefenseUnits(Connection con, int civilizationId) {
        String defenseUnitSql = "SELECT * FROM defense_unit_stats WHERE civilization_id = ?";
        List<DefenseUnit> units = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(defenseUnitSql)) {
            ps.setInt(1, civilizationId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DefenseUnit unit = createDefenseUnit(rs.getString("type_unit"));
                    unit.setUnitID(rs.getString("unit_id"));
                    unit.setArmor(rs.getInt("armor"));
                    unit.setBaseDamage(rs.getInt("base_damage"));
                    unit.setExperience(rs.getInt("experience"));
                    unit.setSanctified(rs.getString("sanctified").equals("YES"));
                    units.add(unit);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return units;
    }

    private MilitaryUnit createAttackUnit(String type) {
        switch (type) {
            case "Swordsman":
                return new Swordsman();
            case "Spearman":
                return new Spearman();
            case "Crossbow":
                return new Crossbow();
            case "Cannon":
                return new Cannon();
            default:
                throw new IllegalArgumentException("Unknown attack unit type: " + type);
        }
    }

    private SpecialUnit createSpecialUnit(String type) {
        switch (type) {
            case "Magician":
                return new Magician();
            case "Priest":
                return new Priest();
            default:
                throw new IllegalArgumentException("Unknown special unit type: " + type);
        }
    }

    private DefenseUnit createDefenseUnit(String type) {
        switch (type) {
            case "ArrowTower":
                return new ArrowTower();
            case "Catapult":
                return new Catapult();
            case "RocketLauncherTower":
                return new RocketLauncherTower();
            default:
                throw new IllegalArgumentException("Unknown defense unit type: " + type);
        }
    }

    // Añadimos el nuevo método para insertar un jugador
    public void insertPlayer(String playerName) {
        String sql = "INSERT INTO players (name) VALUES (?)";

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    // Otros métodos existentes

    public List<Map<String, Object>> getCivilizationUnits(int civilizationId) {
        String sql = "SELECT type_unit, COUNT(*) AS unit_count FROM attack_units_stats WHERE civilization_id = ? GROUP BY type_unit";
        return queryWithParams(sql, civilizationId);
    }

    public List<Map<String, Object>> getEnemyUnits() {
        String sql = "SELECT type_unit, COUNT(*) AS unit_count FROM enemy_units_stats GROUP BY type_unit";
        return query(sql);
    }


    private List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection connection = AppData.getInstance().getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnLabel(i), rs.getObject(i));
                }
                resultList.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private List<Map<String, Object>> queryWithParams(String sql, int param) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, param);
            try (ResultSet rs = stmt.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnLabel(i), rs.getObject(i));
                    }
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Map<String, Object>> getBattleLog(int civilizationId) {
        String sql = "SELECT type_unit, COUNT(*) AS unit_count FROM enemy_units_stats WHERE civilization_id = ? GROUP BY type_unit";
        return queryWithParameters(sql, civilizationId);
    }

    private List<Map<String, Object>> queryWithParameters(String sql, int civilizationId) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            try (ResultSet rs = statement.executeQuery()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        row.put(metaData.getColumnLabel(i), rs.getObject(i));
                    }
                    resultList.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public List<Map<String, Object>> getBattleData(int civilizationId) {
        String sql = "SELECT type_unit, COUNT(*) AS unit_count, 'enemy' AS enemy_type_unit, COUNT(*) AS enemy_unit_count FROM attack_units_stats WHERE civilization_id = ? GROUP BY type_unit";
        List<Map<String, Object>> battleData = new ArrayList<>();

        try (Connection connection = AppData.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, civilizationId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("type_unit", rs.getString("type_unit"));
                    row.put("unit_count", rs.getInt("unit_count"));
                    row.put("enemy_type_unit", rs.getString("enemy_type_unit"));
                    row.put("enemy_unit_count", rs.getInt("enemy_unit_count"));
                    battleData.add(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return battleData;
    }

    
}
