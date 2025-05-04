package com.scholarportal.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    // Pages accessible without login
    private static final List<String> publicPages = Arrays.asList(
            "/login-admin.jsp",
            "/login-student.jsp",
            "/AdminLoginServlet",
            "/StudentLoginServlet"
    );

    // Pages accessible only by admin
    private static final List<String> adminOnlyPages = Arrays.asList(
            "/all-student.jsp",
            "/create-student.jsp",
            "/detail-student.jsp",
            "/edit-student.jsp",
            "/AllStudentServlet",
            "/CreateStudentServlet",
            "/DetailStudentServlet",
            "/EditStudentServlet",
            "/DeleteStudentServlet"
    );

    // Pages accessible only by student
    private static final List<String> studentOnlyPages = Arrays.asList(
            "/profile-student.jsp",
            "/ProfileStudentServlet"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String requestURI = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = requestURI.substring(contextPath.length());

        // Check if requested resource is a static resource
        if (path.matches(".*\\.(css|jpg|jpeg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }

        // Default page is index.jsp
        if (path.equals("/") || path.equals("") || path.equals("/index.jsp")) {
            if (session == null || (session.getAttribute("admin") == null && session.getAttribute("student") == null)) {
                httpResponse.sendRedirect(contextPath + "/login-student.jsp");
                return;
            }
        }

        // Check if user is logged in
        boolean isLoggedIn = (session != null && (session.getAttribute("admin") != null || session.getAttribute("student") != null));
        boolean isAdmin = (session != null && session.getAttribute("admin") != null);
        boolean isStudent = (session != null && session.getAttribute("student") != null);
        boolean isPublicPage = isPublicPage(path);

        // Rule 1: If not logged in, redirect to login if trying to access protected page
        if (!isLoggedIn && !isPublicPage) {
            httpResponse.sendRedirect(contextPath + "/login-student.jsp");
            return;
        }

        // Rule 2: If logged in as admin, block access to login and student profile pages
        if (isAdmin && (isPublicPage || studentOnlyPages.contains(path))) {
            httpResponse.sendRedirect(contextPath + "/index.jsp");
            return;
        }

        // Rule 3: If logged in as student, block access to login and admin-only pages
        if (isStudent && (isPublicPage || adminOnlyPages.contains(path))) {
            httpResponse.sendRedirect(contextPath + "/index.jsp");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPage(String path) {
        return publicPages.contains(path);
    }

    @Override
    public void destroy() {
    }
}