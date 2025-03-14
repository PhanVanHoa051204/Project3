package com.financemanager.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financemanager.dao.GoalDAO;
import com.financemanager.model.Goal;

/**
 * Servlet implementation class GoalServlet
 */
@WebServlet("/goals")
public class GoalServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GoalDAO goalDAO;

    public void init() {
        goalDAO = new GoalDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int goalId = Integer.parseInt(request.getParameter("goal_id"));
                boolean isDeleted = goalDAO.deleteGoal(goalId);
                if (isDeleted) {
                	response.sendRedirect("/Project3/TietKiem/goals.jsp");
                    
                } else {
                    response.getWriter().println("Error while deleting goal.");
                }
            } catch (NumberFormatException e) {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            }
        } else if ("edit".equals(action)) {
            try {
                int goalId = Integer.parseInt(request.getParameter("goal_id"));
                Goal goal = goalDAO.getGoalById(goalId);
                if (goal != null) {
                    request.setAttribute("goal", goal);
                    response.sendRedirect("/Project3/TietKiem/goals.jsp");
                } else {
                	response.sendRedirect("/Project3/TietKiem/goals.jsp");
                }
            } catch (NumberFormatException e) {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            }
        } else {
            // Hiển thị danh sách goals
            List<Goal> goals = goalDAO.getAllGoals();
            request.setAttribute("goals", goals);
            response.sendRedirect("/Project3/TietKiem/goals.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Đảm bảo xử lý ký tự tiếng Việt
        String action = request.getParameter("action");

        if (action == null) {
        	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            return;
        }

        // Lấy các tham số chung
        int userId;
        String goalName;
        Double targetAmount;
        Double currentAmount;
        Date targetDate;
        Date createdAt = new Date(System.currentTimeMillis()); // Mặc định createdAt là thời gian hiện tại

        try {
            userId = Integer.parseInt(request.getParameter("user_id"));
            goalName = request.getParameter("goal_name");
            targetAmount = Double.parseDouble(request.getParameter("target_amount"));
            currentAmount = Double.parseDouble(request.getParameter("current_amount"));
            targetDate = Date.valueOf(request.getParameter("target_date"));
        } catch (NumberFormatException  e) {
        	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            return;
        }

        if (goalName == null || goalName.trim().isEmpty()) {
        	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            return;
        }

        // Xử lý action
        if ("add".equals(action)) {
            Goal goal = new Goal(0, userId, goalName, targetAmount, currentAmount, targetDate, createdAt);
            boolean isAdded = goalDAO.addGoal(goal);
            if (isAdded) {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            } else {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            }
        } else if ("update".equals(action)) {
            int goalId = Integer.parseInt(request.getParameter("goal_id"));
            Goal goal = new Goal(goalId, userId, goalName, targetAmount, currentAmount, targetDate, createdAt);
            boolean isUpdated = goalDAO.updateGoal(goal);
            if (isUpdated) {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            } else {
            	response.sendRedirect("/Project3/TietKiem/goals.jsp");
            }
        } else {
        	response.sendRedirect("/Project3/TietKiem/goals.jsp");
        }
    }
}