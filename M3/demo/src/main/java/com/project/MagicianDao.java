package com.project.dao;

import com.project.magician;
import com.project.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MagicianDao {

    // SQL queries
    private static final String INSERT_MAGIGIAN_SQL = "INSERT INTO special_units_stats (civilization_id, unit_id, type_unit, armor, base_damage, experience) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MAGIGIAN_BY_ID = "SELECT civilization_id, unit_id, type_unit, armor, base_damage, experience FROM special_units_stats WHERE civilization_id = ? AND unit_id = ?";
    private static final String DELETE_MAGIGIAN_SQL = "DELETE FROM special_units_stats WHERE civilization_id = ? AND unit_id = ?";
    private static final String UPDATE_MAGIGIAN_SQL = "UPDATE special_units_stats SET armor = ?, base_damage = ?, experience = ? WHERE civilization_id = ? AND unit_id = ?";

    // Insert a new magician
    public void insertMagician(int civilizationId, String unitId, Magician magician) throws SQLException {
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAGIGIAN_SQL)) {
            preparedStatement.setInt(1, civilizationId);
            preparedStatement.setString(2, unitId);
            preparedStatement.setString(3, "Magician");
            preparedStatement.setInt(4, magician.getActualArmor());
            preparedStatement.setInt(5, magician.attack());
            preparedStatement.setInt(6, magician.getExperience());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    // Select magician by id
    public Magician selectMagician(int civilizationId, String unitId) {
        Magician magician = null;
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAGIGIAN_BY_ID)) {
            preparedStatement.setInt(1, civilizationId);
            preparedStatement.setString(2, unitId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int armor = rs.getInt("armor");
                int baseDamage = rs.getInt("base_damage");
                int experience = rs.getInt("experience");
                magician = new Magician(armor, baseDamage);
                magician.setExperience(experience);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return magician;
    }

    // Update magician
    public boolean updateMagician(int civilizationId, String unitId, Magician magician) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAGIGIAN_SQL)) {
            preparedStatement.setInt(1, magician.getActualArmor());
            preparedStatement.setInt(2, magician.attack());
            preparedStatement.setInt(3, magician.getExperience());
            preparedStatement.setInt(4, civilizationId);
            preparedStatement.setString(5, unitId);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    // Delete magician
    public boolean deleteMagician(int civilizationId, String unitId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DBConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MAGIGIAN_SQL)) {
            preparedStatement.setInt(1, civilizationId);
            preparedStatement.setString(2, unitId);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
