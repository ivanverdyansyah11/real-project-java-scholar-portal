<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Detail Student Page | Student Management System</title>

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
                    <h4 class="header-title">Detail Student</h4>
                </div>
                <p class="alert alert-failed">Lorem ipsum dolor sit amet.</p>
                <form class="form" style="grid-template-columns: auto auto;">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="input" name="name" readonly placeholder="Enter your name...">
                    </div>
                    <div class="form-group">
                        <label for="gpa">GPA</label>
                        <input type="text" id="gpa" class="input" name="gpa" readonly placeholder="Enter your gpa...">
                    </div>
                    <div class="form-group">
                        <label for="subject">Subject</label>
                        <input type="text" id="subject" class="input" name="subject" readonly placeholder="Enter your subject...">
                    </div>
                    <div class="form-group">
                        <label for="enrollment_date">Enrollment Date</label>
                        <input type="date" id="enrollment_date" class="input" name="enrollment_date" readonly>
                    </div>
                    <div class="button-group" style="grid-column: 1/3;">
                        <a href="<%=request.getContextPath()%>/all-student" class="button-secondary" style="width: 100%; text-align: center;">Back to All Student Page</a>
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