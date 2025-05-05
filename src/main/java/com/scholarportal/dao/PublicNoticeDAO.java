package com.scholarportal.dao;

import com.scholarportal.model.PublicNotice;
import com.scholarportal.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublicNoticeDAO {
    public List<PublicNotice> getAllPublicNotices() {
        List<PublicNotice> publicNotices = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM public_notices";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                PublicNotice publicNotice = new PublicNotice();
                publicNotice.setId(rs.getInt("id"));
                publicNotice.setName(rs.getString("name"));
                publicNotice.setDescription(rs.getString("description"));
                publicNotices.add(publicNotice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return publicNotices;
    }

    public List<PublicNotice> searchPublicNotices(String keyword) {
        List<PublicNotice> publicNotices = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM public_notices WHERE id LIKE ? OR name LIKE ?";
            stmt = conn.prepareStatement(sql);
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PublicNotice publicNotice = new PublicNotice();
                publicNotice.setId(rs.getInt("id"));
                publicNotice.setName(rs.getString("name"));
                publicNotice.setDescription(rs.getString("description"));
                publicNotices.add(publicNotice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return publicNotices;
    }

    public PublicNotice getPublicNoticeById(int id) {
        PublicNotice publicNotice = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM public_notices WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                publicNotice = new PublicNotice();
                publicNotice.setId(rs.getInt("id"));
                publicNotice.setName(rs.getString("name"));
                publicNotice.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return publicNotice;
    }

    public boolean createPublicNotice(PublicNotice publicNotice) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO public_notices (name, description) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, publicNotice.getName());
            stmt.setString(2, publicNotice.getDescription());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public boolean updatePublicNotice(PublicNotice publicNotice) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "UPDATE public_notices SET name = ?, description = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, publicNotice.getName());
            stmt.setString(2, publicNotice.getDescription());
            stmt.setInt(3, publicNotice.getId());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public boolean deletePublicNotice(int id) {
        boolean success = false;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "DELETE FROM public_notices WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) DBUtil.closeConnection(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }
}