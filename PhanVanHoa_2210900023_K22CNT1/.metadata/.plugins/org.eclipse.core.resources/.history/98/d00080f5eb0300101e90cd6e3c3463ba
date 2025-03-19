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

@WebServlet("/budgets")
public class BudgetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BudgetDAO budgetDAO;

    // Khởi tạo BudgetDAO trong init()
    public void init() {
        try {
            budgetDAO = new BudgetDAO();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing BudgetDAO", e);
        }
    }

    // Xử lý yêu cầu GET (xóa ngân sách hoặc hiển thị danh sách)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int budgetId = Integer.parseInt(request.getParameter("budget_id"));
                boolean isDeleted = budgetDAO.deleteBudget(budgetId);
                if (isDeleted) {
                    response.sendRedirect("/Project3/NganSach/budgets.jsp");
                } else {
                    response.getWriter().println("Error while deleting budget.");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("/Project3/NganSach/budgets.jsp");
            }
        } else {
            // Hiển thị danh sách ngân sách
            List<Budget> budgets = budgetDAO.getAllBudgets();
            request.setAttribute("budgets", budgets);
            request.getRequestDispatcher("/NganSach/budgets.jsp").forward(request, response);
        }
    }

    // Xử lý yêu cầu POST (thêm hoặc cập nhật ngân sách)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project3/NganSach/error.jsp");
            return;
        }

        String userIdStr = request.getParameter("user_id");
        String categoryIdStr = request.getParameter("category_id");
        String amountStr = request.getParameter("amount");
        String monthStr = request.getParameter("month");
        String budgetIdStr = request.getParameter("budget_id");

        // Kiểm tra và parse các giá trị
        int userId = 0;
        int categoryId = 0;
        double amount = 0.0;
        Date month = null;
        int budgetId = 0;

        try {
            userId = Integer.parseInt(userIdStr != null ? userIdStr : "0");
            categoryId = Integer.parseInt(categoryIdStr != null ? categoryIdStr : "0");
            amount = Double.parseDouble(amountStr != null ? amountStr : "0.0");
            month = monthStr != null && !monthStr.trim().isEmpty() ? Date.valueOf(monthStr) : null;
            budgetId = Integer.parseInt(budgetIdStr != null ? budgetIdStr : "0");

            // Kiểm tra month không null
            if (month == null) {
                response.sendRedirect("/Project3/NganSach/budgets.jsp");
                return;
            }

            Budget budget = new Budget(budgetId, userId, categoryId, amount, month);
            if ("add".equals(action)) {
                boolean isAdded = budgetDAO.addBudget(budget);
                if (isAdded) {
                    response.sendRedirect("/Project3/NganSach/budgets.jsp");
                } else {
                    response.sendRedirect("/Project3/NganSach/budgets.jsp");
                }
            } else if ("update".equals(action)) {
                boolean isUpdated = budgetDAO.updateBudget(budget);
                if (isUpdated) {
                    response.sendRedirect("/Project3/NganSach/budgets.jsp");
                } else {
                    response.sendRedirect("/Project3/NganSach/budgets.jsp");
                }
            }
            
        }
        catch (NumberFormatException  e) {
            response.sendRedirect("/Project3/NganSach/budgets.jsp?error=invalid_input");
        }
    }
}