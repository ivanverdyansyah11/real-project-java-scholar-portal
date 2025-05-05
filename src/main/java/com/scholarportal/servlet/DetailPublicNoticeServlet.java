package com.scholarportal.servlet;

import com.scholarportal.dao.PublicNoticeDAO;
import com.scholarportal.model.PublicNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DetailPublicNoticeServlet")
public class DetailPublicNoticeServlet extends HttpServlet {
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
                    request.getRequestDispatcher("detail-public-notice.jsp").forward(request, response);
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
}