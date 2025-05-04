package com.scholarportal.servlet;

import com.scholarportal.dao.StudentDAO;
import com.scholarportal.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/CreateStudentServlet")
public class CreateStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("create-student.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gpaStr = request.getParameter("gpa");
        String subject = request.getParameter("subject");
        String enrollmentDateStr = request.getParameter("enrollment_date");

        try {
            double gpa = Double.parseDouble(gpaStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date enrollmentDate = dateFormat.parse(enrollmentDateStr);

            Student student = new Student();
            student.setName(name);
            student.setUsername(username);
            student.setPassword(password);
            student.setGpa(gpa);
            student.setSubject(subject);
            student.setEnrollmentDate(enrollmentDate);

            StudentDAO studentDAO = new StudentDAO();
            boolean success = studentDAO.createStudent(student);

            if (success) {
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Successfully created student record");
                response.sendRedirect("AllStudentServlet");

            } else {
                request.setAttribute("errorMessage", "Failed to create student record");
                request.getRequestDispatcher("create-student.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid GPA format");
            request.getRequestDispatcher("create-student.jsp").forward(request, response);
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format");
            request.getRequestDispatcher("create-student.jsp").forward(request, response);
        }
    }
}