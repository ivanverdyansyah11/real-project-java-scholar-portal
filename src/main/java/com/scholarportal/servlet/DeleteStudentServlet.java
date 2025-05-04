package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                StudentDAO studentDAO = new StudentDAO();
                boolean success = studentDAO.deleteStudent(id);

                if (success) {
                    request.setAttribute("successMessage", "Successfully to delete student record");
                    request.getRequestDispatcher("AllStudentServlet").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to delete student record");
                    request.getRequestDispatcher("AllStudentServlet").forward(request, response);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("AllStudentServlet");
            }
        } else {
            response.sendRedirect("AllStudentServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}