package com.financemanager.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financemanager.dao.RecurringTransactionDAO;
import com.financemanager.model.RecurringTransaction;

/**
 * Servlet implementation class RecurringTransactionServlet
 */
@WebServlet("/recurringtransactions")
public class RecurringTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RecurringTransactionDAO recurringTransactionDAO;

    public void init() {
        try {
            recurringTransactionDAO = new RecurringTransactionDAO();
        } catch (Exception e) {
            throw new RuntimeException("Error initializing RecurringTransactionDAO", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<RecurringTransaction> transactions = recurringTransactionDAO.getAllRecurringTransactions();
        request.setAttribute("transactions", transactions);
        request.getRequestDispatcher("/GDDinhKy/recurringtransactions.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("user_id"));
        int categoryId = Integer.parseInt(request.getParameter("category_id"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        String description = request.getParameter("description");
        String frequency = request.getParameter("frequency");
        Date startDate = Date.valueOf(request.getParameter("start_date"));
        Date endDate = request.getParameter("end_date").isEmpty() ? null : Date.valueOf(request.getParameter("end_date"));

        RecurringTransaction transaction = new RecurringTransaction(0, userId, categoryId, amount, description, frequency, startDate, endDate);
        recurringTransactionDAO.addRecurringTransaction(transaction);
        response.sendRedirect("recurring-transactions");
    }


}
