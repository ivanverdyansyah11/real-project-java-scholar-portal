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

@WebServlet("/EditStudentServlet")
public class EditStudentServlet extends HttpServlet {
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
                    request.getRequestDispatcher("edit-student.jsp").forward(request, response);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gpaStr = request.getParameter("gpa");
        String subject = request.getParameter("subject");
        String enrollmentDateStr = request.getParameter("enrollment_date");

        try {
            int id = Integer.parseInt(idStr);
            double gpa = Double.parseDouble(gpaStr);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date enrollmentDate = dateFormat.parse(enrollmentDateStr);

            Student student = new Student();
            student.setId(id);
            student.setName(name);
            student.setUsername(username);
            student.setPassword(password);
            student.setGpa(gpa);
            student.setSubject(subject);
            student.setEnrollmentDate(enrollmentDate);

            StudentDAO studentDAO = new StudentDAO();
            boolean success = studentDAO.updateStudent(student);

            HttpSession session = request.getSession();
            Student loggedInStudent = (Student) session.getAttribute("student");

            if (success) {
                if (loggedInStudent != null) {
                    if (loggedInStudent.getId() == id) {
                        session.setAttribute("student", student);
                    }
                    request.setAttribute("successMessage", "Successfully to update student record");
                    request.getRequestDispatcher("all-student.jsp").forward(request, response);
                } else {
                    session.setAttribute("successMessage", "Successfully update student record");
                    response.sendRedirect("AllStudentServlet");
                }
            } else {
                request.setAttribute("errorMessage", "Failed to update student record");
                request.setAttribute("student", student);
                request.getRequestDispatcher("edit-student.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid ID or GPA format");
            response.sendRedirect("AllStudentServlet");
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Invalid date format");
            response.sendRedirect("AllStudentServlet");
        }
    }
}