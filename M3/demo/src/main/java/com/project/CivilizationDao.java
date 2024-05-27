package com.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class CivilizationDao {
    
    private String title(String str) {

        StringBuilder titleCase = new StringBuilder();
        boolean nextTitleCase = true;

        for (char c : str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) { 
                c = Character.toTitleCase(c);
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }

    public void addSave(SaveData save) {
        Connection con = AppData.getConnection();
        String sql = "INSERT INTO civilization_stats (name, wood_amount, iron_amount, food_amount, mana_amount, magicTower_counter, Church_counter, farm_counter, smithy_counter, carpentry_counter, technology_defense_level, technology_attack_level, battles_counter) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
        String sql2 = "SELECT civilization_id FROM civilization_stats ORDER BY civilization_id DESC FETCH FIRST 1 ROW ONLY";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int civId = -1;
        try {
            ps = con.prepareStatement(sql);
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
            ps.setInt(13, save.getWave());
            ps.executeUpdate();
            con.commit();
            Statement s = con.createStatement();
            rs = s.executeQuery(sql2);
            if (rs.next()) {
                civId = rs.getInt("civilization_id");
                save.setSavedId(civId);
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }


        for (MilitaryUnit unit : save.getOwnArmy()) {
            sql = "INSERT INTO attack_units_stats (civilization_id, unit_id, type_unit, armor, base_damage, experiencie, sanctified) VALUES(?, ?, ?, ?, ?, ?, ?)";
            try {
                ps = con.prepareStatement(sql);
                ps.setInt(1, civId);
                ps.setString(2, unit.getUnitID());
                ps.setString(3, title(unit.get));
            }
        }
    }
}
