package com.scholarportal.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();

        // Public resources that don't need authentication
        boolean isStaticResource = uri.contains("/assets/");
        boolean isLoginPage = uri.equals(contextPath + "/login-admin.jsp") ||
                uri.equals(contextPath + "/login-student.jsp") ||
                uri.equals(contextPath + "/login-admin") ||
                uri.equals(contextPath + "/login-student");

        // Check if the user is logged in
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdmin = isLoggedIn && session.getAttribute("userType").equals("admin");
        boolean isStudent = isLoggedIn && session.getAttribute("userType").equals("student");

        // Rules based on user roles and requested pages
        if (isStaticResource) {
            // Allow access to static resources for everyone
            chain.doFilter(request, response);
            return;
        } else if (!isLoggedIn && !isLoginPage) {
            // If not logged in, redirect to the appropriate login page, default to student login
            httpResponse.sendRedirect(contextPath + "/login-student.jsp");
            return;
        } else if (isLoggedIn && isLoginPage) {
            // If already logged in, redirect to index
            httpResponse.sendRedirect(contextPath + "/");
            return;
        } else if (isAdmin) {
            // Admin access restrictions
            if (uri.contains("/profile-student")) {
                httpResponse.sendRedirect(contextPath + "/");
                return;
            }
        } else if (isStudent) {
            // Student access restrictions
            if (uri.contains("/all-student") ||
                    uri.contains("/create-student") ||
                    uri.contains("/detail-student") ||
                    uri.contains("/edit-student")) {

                httpResponse.sendRedirect(contextPath + "/");
                return;
            }
        }

        // Continue the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}