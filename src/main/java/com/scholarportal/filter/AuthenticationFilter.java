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

    private static final List<String> publicPages = Arrays.asList(
            "/login-admin.jsp",
            "/login-student.jsp",
            "/AdminLoginServlet",
            "/StudentLoginServlet"
    );

    private static final List<String> adminOnlyPages = Arrays.asList(
            "/all-student.jsp",
            "/create-student.jsp",
            "/detail-student.jsp",
            "/edit-student.jsp",
            "/all-public-notice.jsp",
            "/create-public-notice.jsp",
            "/detail-public-notice.jsp",
            "/edit-public-notice.jsp",
            "/AllStudentServlet",
            "/CreateStudentServlet",
            "/DetailStudentServlet",
            "/EditStudentServlet",
            "/DeleteStudentServlet",
            "/AllPublicNoticeServlet",
            "/CreatePublicNoticeServlet",
            "/DetailPublicNoticeServlet",
            "/EditPublicNoticeServlet",
            "/DeletePublicNoticeServlet"
    );

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

        if (path.matches(".*\\.(css|jpg|jpeg|png|gif|js)")) {
            chain.doFilter(request, response);
            return;
        }

        if (path.equals("/") || path.equals("") || path.equals("/index.jsp")) {
            if (session == null || (session.getAttribute("admin") == null && session.getAttribute("student") == null)) {
                httpResponse.sendRedirect(contextPath + "/login-student.jsp");
                return;
            }
        }

        boolean isLoggedIn = (session != null && (session.getAttribute("admin") != null || session.getAttribute("student") != null));
        boolean isAdmin = (session != null && session.getAttribute("admin") != null);
        boolean isStudent = (session != null && session.getAttribute("student") != null);
        boolean isPublicPage = isPublicPage(path);

        if (!isLoggedIn && !isPublicPage) {
            httpResponse.sendRedirect(contextPath + "/login-student.jsp");
            return;
        }

        if (isAdmin && (isPublicPage || studentOnlyPages.contains(path))) {
            httpResponse.sendRedirect(contextPath + "/index.jsp");
            return;
        }

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