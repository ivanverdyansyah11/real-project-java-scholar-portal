package com.scholarportal.dao;

import com.scholarportal.model.Student;
import com.scholarportal.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // Login validation for student
    public Student validateLogin(String username, String password) {
        Student student = null;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM students WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setUsername(rs.getString("username"));
                student.setGpa(rs.getDouble("gpa"));
                student.setSubject(rs.getString("subject"));
                student.setEnrollmentDate(rs.getDate("enrollment_date"));
                // We don't set password for security reasons
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return student;
    }

    // Get student by ID
    public Student getStudentById(int id) {
        Student student = null;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setUsername(rs.getString("username"));
                student.setGpa(rs.getDouble("gpa"));
                student.setSubject(rs.getString("subject"));
                student.setEnrollmentDate(rs.getDate("enrollment_date"));
                // We don't set password for security reasons
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return student;
    }

    // Get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setUsername(rs.getString("username"));
                student.setGpa(rs.getDouble("gpa"));
                student.setSubject(rs.getString("subject"));
                student.setEnrollmentDate(rs.getDate("enrollment_date"));
                // We don't set password for security reasons

                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Search students by name or ID
    public List<Student> searchStudents(String searchTerm) {
        List<Student> students = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "SELECT * FROM students WHERE id LIKE ? OR name LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + searchTerm + "%");
            stmt.setString(2, "%" + searchTerm + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setUsername(rs.getString("username"));
                student.setGpa(rs.getDouble("gpa"));
                student.setSubject(rs.getString("subject"));
                student.setEnrollmentDate(rs.getDate("enrollment_date"));
                // We don't set password for security reasons

                students.add(student);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Create new student
    public boolean createStudent(Student student) {
        boolean success = false;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "INSERT INTO students (name, username, password, gpa, subject, enrollment_date) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getUsername());
            stmt.setString(3, student.getPassword());
            stmt.setDouble(4, student.getGpa());
            stmt.setString(5, student.getSubject());
            stmt.setDate(6, new java.sql.Date(student.getEnrollmentDate().getTime()));

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return success;
    }

    // Update student
    public boolean updateStudent(Student student) {
        boolean success = false;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "UPDATE students SET name = ?, gpa = ?, subject = ?, enrollment_date = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getName());
            stmt.setDouble(2, student.getGpa());
            stmt.setString(3, student.getSubject());
            stmt.setDate(4, new java.sql.Date(student.getEnrollmentDate().getTime()));
            stmt.setInt(5, student.getId());

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return success;
    }

    // Delete student
    public boolean deleteStudent(int id) {
        boolean success = false;

        try (Connection conn = DatabaseConfig.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            success = rowsAffected > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return success;
    }
}