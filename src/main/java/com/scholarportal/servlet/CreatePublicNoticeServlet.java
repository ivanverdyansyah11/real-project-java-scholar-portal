package com.scholarportal.servlet;

import com.scholarportal.dao.PublicNoticeDAO;
import com.scholarportal.model.PublicNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CreatePublicNoticeServlet")
public class CreatePublicNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("create-public-notice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try {
            PublicNotice publicNotice = new PublicNotice();
            publicNotice.setName(name);
            publicNotice.setDescription(description);

            PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
            boolean success = publicNoticeDAO.createPublicNotice(publicNotice);

            if (success) {
                HttpSession session = request.getSession();
                session.setAttribute("successMessage", "Successfully created public notice record");
                response.sendRedirect("AllPublicNoticeServlet");

            } else {
                request.setAttribute("errorMessage", "Failed to create public notice record");
                request.getRequestDispatcher("create-public-notice.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("create-public-notice.jsp").forward(request, response);
        }
    }
}