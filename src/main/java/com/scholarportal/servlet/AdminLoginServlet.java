package com.scholarportal.servlet;

import com.scholarportal.dao.AdminDAO;
import com.scholarportal.model.Admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login-admin")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO = new AdminDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to login page
        request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get login credentials
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate credentials
        Admin admin = adminDAO.validateLogin(username, password);

        if (admin != null) {
            // Create session for admin
            HttpSession session = request.getSession();
            session.setAttribute("user", admin);
            session.setAttribute("userType", "admin");

            // Redirect to index page
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            // Show error message and redirect back to login page
            request.setAttribute("errorMessage", "Invalid username or password");
            request.getRequestDispatcher("/login-admin.jsp").forward(request, response);
        }
    }
}