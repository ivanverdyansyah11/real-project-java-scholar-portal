package com.scholarportal.dao;

import com.scholarportal.model.Admin;
import com.scholarportal.util.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    // Login validation for admin
    public Admin validateLogin(String username, String password) {
        Admin admin = null;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM admins WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setUsername(rs.getString("username"));
                // We don't set password for security reasons
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return admin;
    }

    // Get admin by ID
    public Admin getAdminById(int id) {
        Admin admin = null;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM admins WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                admin = new Admin();
                admin.setId(rs.getInt("id"));
                admin.setName(rs.getString("name"));
                admin.setUsername(rs.getString("username"));
                // We don't set password for security reasons
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return admin;
    }
}