package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;
import com.scholarportal.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/all-student")
public class AllStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if admin is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null ||
                !session.getAttribute("userType").equals("admin")) {
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        // Get search term from request parameter
        String searchTerm = request.getParameter("search");
        List<Student> students;

        if (searchTerm != null && !searchTerm.trim().isEmpty()) {
            // Search students by name or ID
            students = studentDAO.searchStudents(searchTerm);
            request.setAttribute("searchTerm", searchTerm);
        } else {
            // Get all students
            students = studentDAO.getAllStudents();
        }

        // Set students in request attribute
        request.setAttribute("students", students);

        // Show success message if present in the session
        if (session.getAttribute("successMessage") != null) {
            request.setAttribute("successMessage", session.getAttribute("successMessage"));
            session.removeAttribute("successMessage");
        }

        // Show error message if present in the session
        if (session.getAttribute("errorMessage") != null) {
            request.setAttribute("errorMessage", session.getAttribute("errorMessage"));
            session.removeAttribute("errorMessage");
        }

        // Forward to all-student.jsp
        request.getRequestDispatcher("/all-student.jsp").forward(request, response);
    }
}