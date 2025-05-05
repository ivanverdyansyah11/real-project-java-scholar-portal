<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student, com.scholarportal.model.PublicNotice" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) session.getAttribute("student");

    if (admin == null) {
        response.sendRedirect("login-admin.jsp");
        return;
    }

    List<PublicNotice> publicNotices = (List<PublicNotice>) request.getAttribute("publicNotices");
    String search = (String) request.getAttribute("search");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>All Public Notice Page | Student Management System</title>

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
                    <h4 class="header-title">All Public Notices</h4>
                    <a href="CreatePublicNoticeServlet" class="button-primary">Add New Public Notice</a>
                </div>
                <% if (request.getAttribute("successMessage") != null) { %>
                <p class="alert alert-success"><%= request.getAttribute("successMessage") %></p>
                <% } %>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="alert alert-failed"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <form action="AllPublicNoticeServlet" method="get" class="content-search">
                    <input type="search" class="input" name="search" placeholder="Search by name or ID..."
                           value="<%= search != null ? search : "" %>" style="width: 100%;">
                    <button type="submit" class="button-secondary">Search</button>
                </form>
                <table class="table" style="display: inline-block;">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (publicNotices != null && !publicNotices.isEmpty()) { %>
                            <% for (PublicNotice s : publicNotices) { %>
                                <tr>
                                    <td><%= s.getId() %></td>
                                    <td><%= s.getName() %></td>
                                    <td><%= s.getDescription() %></td>
                                    <td>
                                        <div class="action-button">
                                            <a href="DetailPublicNoticeServlet?id=<%= s.getId() %>"
                                               class="button button-detail">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                                     alt="Detail Icon" class="icon">
                                            </a>
                                            <a href="EditPublicNoticeServlet?id=<%= s.getId() %>"
                                               class="button button-edit">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                                     alt="Edit Icon" class="icon">
                                            </a>
                                            <a href="DeletePublicNoticeServlet?id=<%= s.getId() %>"
                                               class="button button-delete"
                                               onclick="return confirm('Are you sure want to delete this public notice?');">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                                     alt="Delete Icon" class="icon">
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="4" style="text-align:center;">No public notices found.</td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>
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