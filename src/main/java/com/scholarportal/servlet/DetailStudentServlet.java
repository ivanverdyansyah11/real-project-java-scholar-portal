package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;
import com.scholarportal.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DetailStudentServlet")
public class DetailStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                StudentDAO studentDAO = new StudentDAO();
                Student student = studentDAO.getStudentById(id);

                if (student != null) {
                    request.setAttribute("student", student);
                    request.getRequestDispatcher("detail-student.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AllStudentServlet");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("AllStudentServlet");
            }
        } else {
            response.sendRedirect("AllStudentServlet");
        }
    }
}