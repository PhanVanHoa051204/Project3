package com.financemanager.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.financemanager.dao.UserDao;
import com.financemanager.model.User;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDao();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            registerUser(request, response);
        } else if ("login".equals(action)) {
            loginUser(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("logout".equals(action)) {
            logoutUser(request, response);
        }
    }
    
    private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPasswordHash(request.getParameter("password")); // Nên hash password trong thực tế
        user.setEmail(request.getParameter("email"));
        user.setRole("user"); // Mặc định là user
        
        // Kiểm tra và tạo tài khoản admin mặc định
        if ("Admin".equals(user.getUsername()) && "admin".equals(user.getPasswordHash())) {
            user.setRole("admin");
        }
        
        if (userDao.addUser(user)) {
            response.sendRedirect("login.jsp?success=1");
        } else {
            response.sendRedirect("register.jsp?error=1");
        }
    }
    
    private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPasswordHash().equals(password)) { // Nên dùng hash checking trong thực tế
            HttpSession session = request.getSession();
            session.setAttribute("user_id", user.getUserId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("username", user.getUsername()); // Lưu username vào session

            // Điều hướng dựa trên role: admin -> dashboard.jsp, user -> profile.jsp
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("dashboard.jsp");
            } else {
            	request.setAttribute("username", username);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
                dispatcher.forward(request, response);
				/* response.sendRedirect("profile.jsp"); */
            }
        } else {
            response.sendRedirect("login.jsp?error=1");
        }
    }
      
    private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp?logout=1");
    }
    
    
}