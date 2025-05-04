<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.scholarportal.model.Admin, com.scholarportal.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    Admin admin = (Admin) session.getAttribute("admin");
    Student student = (Student) session.getAttribute("student");

    if (admin == null) {
        response.sendRedirect("login-admin.jsp");
        return;
    }

    List<Student> students = (List<Student>) request.getAttribute("students");
    String search = (String) request.getAttribute("search");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>All Student Page | Student Management System</title>

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
                    <h4 class="header-title">All Students</h4>
                    <a href="CreateStudentServlet" class="button-primary">Add New Student</a>
                </div>
                <% if (request.getAttribute("successMessage") != null) { %>
                <p class="alert alert-success"><%= request.getAttribute("successMessage") %></p>
                <% } %>
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p class="alert alert-failed"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <form action="AllStudentServlet" method="get" class="content-search">
                    <input type="search" class="input" name="search" placeholder="Search by name or ID..."
                           value="<%= search != null ? search : "" %>" style="width: 100%;">
                    <button type="submit" class="button-secondary">Search</button>
                </form>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Username</th>
                            <th>GPA</th>
                            <th>Subject</th>
                            <th>Enrollment Date</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (students != null && !students.isEmpty()) { %>
                            <% for (Student s : students) { %>
                                <tr>
                                    <td><%= s.getId() %></td>
                                    <td><%= s.getName() %></td>
                                    <td><%= s.getUsername() %></td>
                                    <td><%= String.format("%.2f", s.getGpa()) %></td>
                                    <td><%= s.getSubject() %></td>
                                    <td><%= dateFormat.format(s.getEnrollmentDate()) %></td>
                                    <td>
                                        <div class="action-button">
                                            <a href="DetailStudentServlet?id=<%= s.getId() %>"
                                               class="button button-detail">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                                     alt="Detail Icon" class="icon">
                                            </a>
                                            <a href="EditStudentServlet?id=<%= s.getId() %>"
                                               class="button button-edit">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                                     alt="Edit Icon" class="icon">
                                            </a>
                                            <a href="DeleteStudentServlet?id=<%= s.getId() %>"
                                               class="button button-delete"
                                               onclick="return confirm('Are you sure want to delete this student?');">
                                                <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                                     alt="Delete Icon" class="icon">
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            <% } %>
                        <% } else { %>
                            <tr>
                                <td colspan="7" style="text-align:center;">No students found.</td>
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