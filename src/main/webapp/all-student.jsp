<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <a href="<%=request.getContextPath()%>/all-student" class="link-item">Student</a>
                    <a href="<%=request.getContextPath()%>/" class="link-item">Logout</a>
                </div>
            </div>
        </nav>

        <div class="container container-content">
            <div class="content">
                <div class="content-header">
                    <h4 class="header-title">All Students</h4>
                    <a href="<%=request.getContextPath()%>/create-student" class="button-primary">Add New Student</a>
                </div>
                <p class="alert alert-success">Lorem ipsum dolor sit amet.</p>
                <p class="alert alert-failed">Lorem ipsum dolor sit amet.</p>
                <form action="<%=request.getContextPath()%>/all-student" method="GET" class="content-search">
                    <input type="search" class="input" name="search" placeholder="Search by name or ID..."
                           value="${searchTerm}" style="width: 100%;">
                    <button type="submit" class="button-secondary">Search</button>
                </form>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>GPA</th>
                            <th>Subject</th>
                            <th>Enrollment Date</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
<%--                    <tr>--%>
<%--                        <td colspan="6" style="text-align:center;">No students found.</td>--%>
<%--                    </tr>--%>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Student</td>
                            <td>4.00</td>
                            <td>RPL</td>
                            <td>5/4/2025</td>
                            <td>
                                <div class="action-button">
                                    <a href="<%=request.getContextPath()%>/detail-student?id=1"
                                       class="button button-detail">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/detail.svg"
                                             alt="Detail Icon" class="icon">
                                    </a>
                                    <a href="<%=request.getContextPath()%>/edit-student?id=1"
                                       class="button button-edit">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/edit.svg"
                                             alt="Edit Icon" class="icon">
                                    </a>
                                    <button type="button" class="button button-delete" onclick="confirm('Are you sure want to delete this student?')">
                                        <img src="<%=request.getContextPath()%>/assets/image/icon/delete.svg"
                                             alt="Delete Icon" class="icon">
                                    </button>
                                </div>
                            </td>
                        </tr>
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