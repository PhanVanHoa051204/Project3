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
	private TransactionDAO transactionDAO;

    @Override
    public void init() throws ServletException {
        try {
            transactionDAO = new TransactionDAO();
        } catch (SQLException e) {
            throw new ServletException("Error initializing TransactionDAO", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transaction> transactions = transactionDAO.getAllTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/GiaoDich/transactions.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        String description = request.getParameter("description");
        Date transactionDate = Date.valueOf(request.getParameter("transaction_date"));
        Date createdAt = Date.valueOf(request.getParameter("createdAt_date"));

        Transaction transaction = new Transaction(0, userId, categoryId, amount, description, transactionDate, createdAt);
        transactionDAO.addTransaction(transaction);
        response.sendRedirect("transactions");
    }


}
