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

@WebServlet("/login-student")
public class StudentLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO = new StudentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/login-student.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get login credentials
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials
        Student student = studentDAO.validateLogin(username, password);

        if (student != null) {
            // Create session for student
            HttpSession session = request.getSession();
            session.setAttribute("user", student);
            session.setAttribute("userType", "student");

            // Redirect to index page
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Show error message and redirect back to login page
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/login-student.jsp").forward(request, response);
        }
    }
}