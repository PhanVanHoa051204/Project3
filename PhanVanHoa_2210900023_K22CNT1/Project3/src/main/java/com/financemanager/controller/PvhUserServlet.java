package com.financemanager.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financemanager.dao.UserDao;
import com.financemanager.model.User;

/**
 * Servlet implementation class PvhUserServlet
 */
@WebServlet("/PvhUserServlet")
public class PvhUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();

    // Xử lý yêu cầu POST (thêm và cập nhật user)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addUser(request, response);
        } else if ("update".equals(action)) {
            updateUser(request, response);
        }
    }

    // Xử lý yêu cầu GET (xóa user)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteUser(request, response);
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        // Kiểm tra dữ liệu đầu vào
        if (username == null || password == null || email == null || username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            response.getWriter().println("Please provide all required fields.");
            return;
        }

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(password); // Nên hash password trong thực tế
        user.setEmail(email);
        user.setRole(role != null ? role : "user");

        if (userDao.addUser(user)) {
            response.sendRedirect("/Project3/NguoiDung/users.jsp");
        } else {
            response.getWriter().println("Error while adding user.");
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");
        int userId = Integer.parseInt(request.getParameter("user_id"));

        // Khởi tạo User với dữ liệu từ request
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPasswordHash(password); // Nên hash password trong thực tế
        user.setEmail(email);
        user.setRole(role != null ? role : "user");

        if (userDao.updateUser(user)) {
            response.sendRedirect("/Project3/NguoiDung/users.jsp");
        } else {
            response.getWriter().println("Error while updating user.");
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect("login.jsp");
            return;
        }

        int userId = Integer.parseInt(request.getParameter("user_id"));
        if (userDao.deleteUser(userId)) {
            response.sendRedirect("/Project3/NguoiDung/users.jsp");
        } else {
            response.getWriter().println("Error while deleting user.");
        }
    }
}