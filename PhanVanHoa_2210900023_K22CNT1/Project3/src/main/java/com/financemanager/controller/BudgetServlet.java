package com.financemanager.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financemanager.dao.BudgetDAO;
import com.financemanager.model.Budget;

/**
 * Servlet implementation class BudgetServlet
 */
@WebServlet("/budgets")
public class BudgetServlet extends HttpServlet {
	private BudgetDAO budgetDAO = new BudgetDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Budget> budgets = budgetDAO.getAllBudgets();
        request.setAttribute("budgets", budgets);
        request.getRequestDispatcher("/NganSach/budgets.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Date month = Date.valueOf(request.getParameter("month"));

        Budget budget = new Budget(0, userId, categoryId, amount, month);
        budgetDAO.addBudget(budget);
        response.sendRedirect("budgets");
    }


}
