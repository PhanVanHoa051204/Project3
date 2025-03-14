package com.financemanager.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financemanager.dao.TransactionDAO;
import com.financemanager.model.Transaction;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/transactions")
public class TransactionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TransactionDAO transactionDAO;

    @Override
    public void init() throws ServletException {
        try {
            transactionDAO = new TransactionDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Error initializing TransactionDAO", e);
        }
    }

    // Xử lý GET: Hiển thị danh sách hoặc xóa giao dịch
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int transactionId = Integer.parseInt(request.getParameter("transaction_id"));
                boolean isDeleted = transactionDAO.deleteTransaction(transactionId);
                if (isDeleted) {
                    response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
                } else {
                    response.getWriter().println("Error while deleting transaction.");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            }
        } else {
            // Hiển thị danh sách giao dịch
            List<Transaction> transactions = transactionDAO.getAllTransactions();
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/Project3/GiaoDich/transactions.jsp").forward(request, response);
        }
    }

    // Xử lý POST: Thêm hoặc cập nhật giao dịch
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            return;
        }

        // Lấy các tham số từ form
        String userIdStr = request.getParameter("user_id");
        String categoryIdStr = request.getParameter("category_id");
        String amountStr = request.getParameter("amount");
        String description = request.getParameter("description");
        String transactionDateStr = request.getParameter("transaction_date");
        String createdAtStr = request.getParameter("createdAt_date");
        String transactionIdStr = request.getParameter("transaction_id");

        // Kiểm tra và parse các tham số
        int userId = 0;
        int categoryId = 0;
        double amount = 0.0;
        int transactionId = 0;
        Date transactionDate = null;
        Date createdAt = null;

        try {
            userId = Integer.parseInt(userIdStr != null ? userIdStr : "0");
            categoryId = Integer.parseInt(categoryIdStr != null ? categoryIdStr : "0");
            amount = Double.parseDouble(amountStr != null ? amountStr : "0.0");
            transactionId = Integer.parseInt(transactionIdStr != null ? transactionIdStr : "0");
            transactionDate = transactionDateStr != null && !transactionDateStr.isEmpty() ? Date.valueOf(transactionDateStr) : null;
            createdAt = createdAtStr != null && !createdAtStr.isEmpty() ? Date.valueOf(createdAtStr) : null;
        } catch (NumberFormatException e) {
            response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            return;
        }

        // Kiểm tra description
        if (description == null || description.trim().isEmpty()) {
            response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            return;
        }

        // Kiểm tra transactionDate và createdAt
        if (transactionDate == null || createdAt == null) {
            response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            return;
        }

        // Tạo đối tượng Transaction
        Transaction transaction = new Transaction(transactionId, userId, categoryId, amount, description, transactionDate, createdAt);

        // Xử lý action
        if ("add".equals(action)) {
            boolean isAdded = transactionDAO.addTransaction(transaction);
            if (isAdded) {
                response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            } else {
                response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            }
        } else if ("update".equals(action)) {
            boolean isUpdated = transactionDAO.updateTransaction(transaction);
            if (isUpdated) {
                response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            } else {
                response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
            }
        } else {
            response.sendRedirect("/Project3/GiaoDich/transactions.jsp");
        }
    }
}