<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) session.getAttribute("student");

    if (admin == null) {
        response.sendRedirect("login-admin.jsp");
        return;
    }
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Create Student Page | Student Management System</title>

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
                    <a class="link-item" href="AllPublicNoticeServlet">Public Notice</a>
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
                    <h4 class="header-title">Create Student</h4>
                </div>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="alert alert-failed"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <form action="CreateStudentServlet" method="post" class="form" style="grid-template-columns: auto auto;">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="input" name="name" required placeholder="Enter student name...">
                    </div>
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" id="username" class="input" name="username" required placeholder="Enter student username...">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" class="input" name="password" required placeholder="Enter student password...">
                    </div>
                    <div class="form-group">
                        <label for="gpa">GPA</label>
                        <input type="text" id="gpa" class="input" name="gpa" required placeholder="Enter student gpa...">
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <input type="text" id="subject" class="input" name="subject" required placeholder="Enter student subject...">
                    </div>
                    <div class="form-group">
                        <label for="enrollment_date">Enrollment Date</label>
                        <input type="date" id="enrollment_date" class="input" name="enrollment_date" required>
                    </div>
                    <div class="button-group" style="grid-column: 1/3;">
                        <button type="submit" class="button-primary" style="width: 100%; text-align: center;">Create Student</button>
                        <a href="AllStudentServlet" class="button-secondary" style="width: 100%; text-align: center;">Cancel Create</a>
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