package com.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData {
    private static AppData instance;
    private Connection conn;

    private AppData() {
        // Conecta al crear la primera instancia
        connect();
    }

    // Singleton
    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    private void connect() {
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; // Cambia esto según tu configuración
        String user = "your_username"; // Cambia esto según tu configuración
        String password = "your_password"; // Cambia esto según tu configuración
        try {
            // Cargar el driver de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false); // Desactiva el autocommit para permitir control manual de transacciones
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return conn;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String sql) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.commit(); // Confirma los cambios
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(); // Revierte los cambios en caso de error
            } catch (SQLException ex) {
                System.out.println("Error al hacer rollback.");
                ex.printStackTrace();
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int insertAndGetId(String sql) {
        int generatedId = -1;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            conn.commit();  // Asegúrate de confirmar la transacción si el autocommit está deshabilitado

            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Recupera el último ID insertado
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                conn.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException ex) {
                System.out.println("Error durante el rollback.");
                ex.printStackTrace();
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return generatedId;
    }

    // Esta función transforma el ResultSet en un Map<String, Object>
    // para hacer el acceso a la información más genérico
    public List<Map<String, Object>> query(String sql) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
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
            System.out.println(e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }
}
