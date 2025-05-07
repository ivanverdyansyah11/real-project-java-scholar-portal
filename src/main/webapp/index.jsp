<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student, com.scholarportal.model.PublicNotice, com.scholarportal.dao.PublicNoticeDAO, java.util.List" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) session.getAttribute("student");

    if (admin == null && student == null) {
        response.sendRedirect("login-student.jsp");
        return;
    }

    PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
    List<PublicNotice> publicNotices = publicNoticeDAO.getAllPublicNotices();
%>

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
                <div class="content-main">
                    <h2 class="main-title">Public Notices</h2>
                    <% if (publicNotices != null && !publicNotices.isEmpty()) { %>
                        <% for (PublicNotice s : publicNotices) { %>
                            <div class="main-feature">
                                <img src="<%=request.getContextPath()%>/assets/image/icon/info-notice.svg" alt="Info Icon"
                                     class="feature-icon">
                                <h6 class="feature-title"><%= s.getName() %></h6>
                                <p class="feature-description"><%= s.getDescription() %></p>
                            </div>
                        <% } %>
                    <% } else { %>
                        <p>No public notices available.</p>
                    <% } %>
                </div>
            </div>
        </div>

        <footer class="footer container">
            <p class="footer-copyright">
                &copy; <%= new java.util.Date().getYear() + 1900 %> Scholar's Portal - Student Management System
            </p>
        </footer>

        <script src="<%=request.getContextPath()%>/assets/js/script.js"></script>
    </body>
</html>