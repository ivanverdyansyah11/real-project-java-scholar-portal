package com.scholarportal.servlet;

import com.scholarportal.dao.PublicNoticeDAO;
import com.scholarportal.model.PublicNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/EditPublicNoticeServlet")
public class EditPublicNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
                PublicNotice publicNotice = publicNoticeDAO.getPublicNoticeById(id);

                if (publicNotice != null) {
                    request.setAttribute("publicNotice", publicNotice);
                    request.getRequestDispatcher("edit-public-notice.jsp").forward(request, response);
                } else {
                    response.sendRedirect("AllPublicNoticeServlet");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("AllPublicNoticeServlet");
            }
        } else {
            response.sendRedirect("AllPublicNoticeServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        try {
            int id = Integer.parseInt(idStr);

            PublicNotice publicNotice = new PublicNotice();
            publicNotice.setId(id);
            publicNotice.setName(name);
            publicNotice.setDescription(description);

            PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
            boolean success = publicNoticeDAO.updatePublicNotice(publicNotice);

            if (success) {
                request.setAttribute("successMessage", "Successfully updated the public notice record.");
                request.getRequestDispatcher("AllPublicNoticeServlet").forward(request, response);
            } else {
                request.setAttribute("errorMessage", "Failed to update public notice record.");
                request.setAttribute("publicNotice", publicNotice);
                request.getRequestDispatcher("edit-public-notice.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid ID format.");
            response.sendRedirect("AllPublicNoticeServlet");
        }
    }
}