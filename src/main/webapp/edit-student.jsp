<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Student Page | Student Management System</title>

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
                    <a href="<%=request.getContextPath()%>/all-student" class="link-item">Student</a>
                    <a href="<%=request.getContextPath()%>/" class="link-item">Logout</a>
                </div>
            </div>
        </nav>

        <div class="container container-content">
            <div class="content">
                <div class="content-header">
                    <h4 class="header-title">Edit Student</h4>
                </div>
                <p class="alert alert-failed">Lorem ipsum dolor sit amet.</p>
                <form class="form" style="grid-template-columns: auto auto;">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="input" name="name" required placeholder="Enter your name...">
                    </div>
                    <div class="form-group">
                        <label for="gpa">GPA</label>
                        <input type="text" id="gpa" class="input" name="gpa" required placeholder="Enter your gpa...">
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <input type="text" id="subject" class="input" name="subject" required placeholder="Enter your subject...">
                    </div>
                    <div class="form-group">
                        <label for="enrollment_date">Enrollment Date</label>
                        <input type="date" id="enrollment_date" class="input" name="enrollment_date" required>
                    </div>
                    <div class="button-group" style="grid-column: 1/3;">
                        <button type="submit" class="button-primary" style="width: 100%; text-align: center;">Save Changes</button>
                        <a href="<%=request.getContextPath()%>/all-student" class="button-secondary" style="width: 100%; text-align: center;">Cancel Edit</a>
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