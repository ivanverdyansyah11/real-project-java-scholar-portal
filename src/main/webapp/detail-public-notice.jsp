<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student, com.scholarportal.model.PublicNotice, java.text.SimpleDateFormat" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) request.getAttribute("student");
    PublicNotice publicNotice = (PublicNotice) request.getAttribute("publicNotice"); // Ambil object publicNotice dari request

    if (admin == null) {
        response.sendRedirect("login-admin.jsp");
        return;
    }

    if (publicNotice == null) {
        response.sendRedirect("AllPublicNoticeServlet");
        return;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Detail Public Notice Page | Student Management System</title>

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
                    <h4 class="header-title">Detail Public Notice</h4>
                </div>
                <form class="form" style="grid-template-columns: auto;">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="input" name="name" readonly placeholder="Enter public notice name..." value="<%= publicNotice.getName() %>">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea id="description" class="input" name="description" readonly placeholder="Enter public notice description..." rows="6"><%= publicNotice.getDescription() %></textarea>
                    </div>
                    <div class="button-group">
                        <a href="AllPublicNoticeServlet" class="button-secondary" style="width: 100%; text-align: center;">Back to All Public Notice Page</a>
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