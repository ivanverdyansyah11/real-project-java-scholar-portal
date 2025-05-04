package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;
import com.scholarportal.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllStudentServlet")
public class AllStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentDAO studentDAO = new StudentDAO();
        List<Student> students;

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            students = studentDAO.searchStudents(search);
            request.setAttribute("search", search);
        } else {
            students = studentDAO.getAllStudents();
        }

        HttpSession session = request.getSession();
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage"); // agar tidak muncul terus
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("all-student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}