package com.scholarportal.servlet;

import com.scholarportal.dao.PublicNoticeDAO;
import com.scholarportal.model.PublicNotice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/AllPublicNoticeServlet")
public class AllPublicNoticeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PublicNoticeDAO publicNoticeDAO = new PublicNoticeDAO();
        List<PublicNotice> publicNotices;

        String search = request.getParameter("search");
        if (search != null && !search.trim().isEmpty()) {
            publicNotices = publicNoticeDAO.searchPublicNotices(search);
            request.setAttribute("search", search);
        } else {
            publicNotices = publicNoticeDAO.getAllPublicNotices();
        }

        HttpSession session = request.getSession();
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage"); // agar tidak muncul terus
        }

        request.setAttribute("publicNotices", publicNotices);
        request.getRequestDispatcher("all-public-notice.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}