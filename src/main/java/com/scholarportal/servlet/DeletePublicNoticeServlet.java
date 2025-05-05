package com.scholarportal.servlet;

import com.scholarportal.dao.PublicNoticeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeletePublicNoticeServlet")
public class DeletePublicNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
                boolean success = publicNoticeDAO.deletePublicNotice(id);

                if (success) {
                    request.setAttribute("successMessage", "Successfully to delete public notice record");
                    request.getRequestDispatcher("AllPublicNoticeServlet").forward(request, response);
                } else {
                    request.setAttribute("errorMessage", "Failed to delete public notice record");
                    request.getRequestDispatcher("AllPublicNoticeServlet").forward(request, response);
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("AllPublicNoticeServlet");
            }
        } else {
            response.sendRedirect("AllPublicNoticeServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}