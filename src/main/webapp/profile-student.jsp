<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student, java.text.SimpleDateFormat" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) session.getAttribute("student");

    if (student == null) {
        response.sendRedirect("AllStudentServlet");
        return;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Profile Student Page | Student Management System</title>

        <%-- STYLE CSS --%>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
        <%-- END STYLE CSS --%>
    </head>
    <body>
        <nav class="navbar">
            <div class="container" style="width: 100%;">
                <a href="<%=request.getContextPath()%>/" class="navbar-brand">
                    <img src="<%=request.getContextPath()%>/assets/image/brand/brand-logo.png" alt="Brand Logo"
                         class="brand-logo">
                </a>
                <div class="navbar-link">
                    <%
                        if (admin != null) {
                    %>
                    <a class="link-item" href="AllStudentServlet">Student</a>
                    <% } else if (student != null) { %>
                    <a class="link-item" href="ProfileStudentServlet">My Profile</a>
                    <% } %>
                    <% if (admin != null || student != null) { %>
                    <a href="LogoutServlet" class="link-item">Logout</a>
                    <% } %>
                </div>
            </div>
        </nav>

        <div class="container container-content">
            <div class="content">
                <div class="content-header">
                    <h4 class="header-title">Profile Student</h4>
                </div>
                <% if (request.getAttribute("successMessage") != null) { %>
                <p class="alert alert-success"><%= request.getAttribute("successMessage") %></p>
                <% } %>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="alert alert-failed"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <form class="form" style="grid-template-columns: auto auto;" method="post" action="ProfileStudentServlet">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="input" name="name" required placeholder="Enter your name..." value="<%= student.getName() %>">
                    </div>
                    <div class="form-group">
                        <label for="gpa">GPA</label>
                        <input type="text" id="gpa" class="input" name="gpa" required placeholder="Enter your gpa..." value="<%= student.getGpa() %>">
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <input type="text" id="subject" class="input" name="subject" required placeholder="Enter your subject..." value="<%= student.getSubject() %>">
                    </div>
                    <div class="form-group">
                        <label for="enrollment_date">Enrollment Date</label>
                        <input type="date" id="enrollment_date" class="input" name="enrollment_date" required value="<%= dateFormat.format(student.getEnrollmentDate()) %>">
                    </div>
                    <div class="button-group" style="grid-column: 1/3;">
                        <button type="submit" class="button-primary" style="width: 100%; text-align: center;">Save Changes</button>
                    </div>
                </form>
            </div>
        </div>

        <footer class="footer container">
            <p class="footer-copyright">
                &copy; <%= new java.util.Date().getYear() + 1900 %> Scholar's Portal - Student
                Management System
            </p>
        </footer>

        <script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
    </body>
</html>