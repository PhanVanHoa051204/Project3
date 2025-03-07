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
	private GoalDAO goalDAO = new GoalDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Goal> goals = goalDAO.getAllGoals();
        request.setAttribute("goals", goals);
        request.getRequestDispatcher("/TietKiem/goals.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        String goalName = request.getParameter("goal_name");
        Double targetAmount = Double.parseDouble(request.getParameter("target_amount"));
        Double currentAmount = Double.parseDouble(request.getParameter("current_amount"));
        Date targetDate = Date.valueOf(request.getParameter("target_date"));
        Date createdAt = Date.valueOf(request.getParameter("createdAt_date"));

        Goal goal = new Goal(0, userId, goalName, targetAmount, currentAmount, targetDate, createdAt);
        goalDAO.addGoal(goal);
        response.sendRedirect("goals");
    }

}
