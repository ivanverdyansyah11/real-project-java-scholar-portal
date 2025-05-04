package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;
import com.scholarportal.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ProfileStudentServlet")
public class ProfileStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student student = (Student) session.getAttribute("student");

        if (student != null) {
            // Get fresh data from database
            StudentDAO studentDAO = new StudentDAO();
            Student refreshedStudent = studentDAO.getStudentById(student.getId());

            if (refreshedStudent != null) {
                // Update session with latest data
                session.setAttribute("student", refreshedStudent);
                request.getRequestDispatcher("profile-student.jsp").forward(request, response);
            } else {
                // Should never happen unless student was deleted
                session.invalidate();
                response.sendRedirect("login-student.jsp");
            }
        } else {
            response.sendRedirect("login-student.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Student sessionStudent = (Student) session.getAttribute("student");

        if (sessionStudent == null) {
            response.sendRedirect("login-student.jsp");
            return;
        }

        // Ambil parameter dari form
        String name = request.getParameter("name");
        String gpaStr = request.getParameter("gpa");
        String subject = request.getParameter("subject");
        String enrollmentDateStr = request.getParameter("enrollment_date");

        try {
            double gpa = Double.parseDouble(gpaStr);
            java.sql.Date enrollmentDate = java.sql.Date.valueOf(enrollmentDateStr);

            // Buat objek student yang baru dengan data update
            Student updatedStudent = new Student();
            updatedStudent.setId(sessionStudent.getId());
            updatedStudent.setUsername(sessionStudent.getUsername()); // tidak diinput user
            updatedStudent.setPassword(sessionStudent.getPassword()); // tidak diinput user
            updatedStudent.setName(name);
            updatedStudent.setGpa(gpa);
            updatedStudent.setSubject(subject);
            updatedStudent.setEnrollmentDate(enrollmentDate);

            StudentDAO studentDAO = new StudentDAO();
            boolean success = studentDAO.updateStudent(updatedStudent);

            if (success) {
                // Perbarui session student juga
                session.setAttribute("student", updatedStudent);
                request.setAttribute("successMessage", "Successfully updated your profile.");
                request.getRequestDispatcher("profile-student.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to update your profile.");
                request.setAttribute("student", sessionStudent);
                request.getRequestDispatcher("profile-student.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            request.setAttribute("student", sessionStudent);
            request.getRequestDispatcher("profile-student.jsp").forward(request, response);
        }
    }
}