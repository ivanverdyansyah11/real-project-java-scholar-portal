<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Main Page | Student Management System</title>

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
                <div class="content-main">
                    <h2 class="main-title">Public Notices</h2>
                    <div class="main-feature">
                        <img src="<%=request.getContextPath()%>/assets/image/icon/info-notice.svg" alt="Info Icon"
                             class="feature-icon">
                        <h6 class="feature-title">Notice 1</h6>
                        <p class="feature-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur
                            consectetur molestiae inventore animi quia rerum eius quibusdam illum deserunt suscipit?</p>
                    </div>
                    <div class="main-feature">
                        <img src="<%=request.getContextPath()%>/assets/image/icon/info-notice.svg" alt="Info Icon"
                             class="feature-icon">
                        <h6 class="feature-title">Notice 2</h6>
                        <p class="feature-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur
                            consectetur molestiae inventore animi quia rerum eius quibusdam illum deserunt suscipit?</p>
                    </div>
                    <div class="main-feature">
                        <img src="<%=request.getContextPath()%>/assets/image/icon/info-notice.svg" alt="Info Icon"
                             class="feature-icon">
                        <h6 class="feature-title">Notice 3</h6>
                        <p class="feature-description">Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur
                            consectetur molestiae inventore animi quia rerum eius quibusdam illum deserunt suscipit?</p>
                    </div>
                </div>
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