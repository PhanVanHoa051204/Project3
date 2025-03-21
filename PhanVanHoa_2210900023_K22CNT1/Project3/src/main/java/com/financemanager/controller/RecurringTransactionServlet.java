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

    // Xử lý GET: Hiển thị danh sách hoặc xóa giao dịch định kỳ
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int recurringTransactionId = Integer.parseInt(request.getParameter("recurring_transaction_id"));
                boolean isDeleted = recurringTransactionDAO.deleteRecurringTransaction(recurringTransactionId);
                if (isDeleted) {
                    response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
                } else {
                    response.getWriter().println("Error while deleting recurring transaction.");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            }
        } else {
            // Hiển thị danh sách giao dịch định kỳ
            List<RecurringTransaction> transactions = recurringTransactionDAO.getAllRecurringTransactions();
            request.setAttribute("transactions", transactions);
            request.getRequestDispatcher("/Project3/GDDinhKy/recurringtransactions.jsp").forward(request, response);
        }
    }

    // Xử lý POST: Thêm hoặc cập nhật giao dịch định kỳ
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // Đảm bảo xử lý ký tự tiếng Việt
        String action = request.getParameter("action");
        if (action == null) {
            response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        // Lấy các tham số từ form
        String userIdStr = request.getParameter("user_id");
        String categoryIdStr = request.getParameter("category_id");
        String amountStr = request.getParameter("amount");
        String description = request.getParameter("description");
        String frequency = request.getParameter("frequency");
        String startDateStr = request.getParameter("start_date");
        String endDateStr = request.getParameter("end_date");
        String recurringTransactionIdStr = request.getParameter("recurring_transaction_id");

        // Kiểm tra và parse các tham số
        int userId = 0;
        int categoryId = 0;
        double amount = 0.0;
        int recurringTransactionId = 0;
        Date startDate = null;
        Date endDate = null;

        try {
            userId = Integer.parseInt(userIdStr != null ? userIdStr : "0");
            categoryId = Integer.parseInt(categoryIdStr != null ? categoryIdStr : "0");
            amount = Double.parseDouble(amountStr != null ? amountStr : "0.0");
            recurringTransactionId = Integer.parseInt(recurringTransactionIdStr != null ? recurringTransactionIdStr : "0");
            startDate = startDateStr != null && !startDateStr.isEmpty() ? Date.valueOf(startDateStr) : null;
            endDate = endDateStr != null && !endDateStr.isEmpty() ? Date.valueOf(endDateStr) : null;
        } catch (NumberFormatException e) {
            response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        // Kiểm tra description và frequency
        if (description == null || description.trim().isEmpty() || 
            frequency == null || frequency.trim().isEmpty()) {
            response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        // Kiểm tra startDate
        if (startDate == null) {
            response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        // Tạo đối tượng RecurringTransaction
        RecurringTransaction transaction = new RecurringTransaction(recurringTransactionId, userId, categoryId, amount, description, frequency, startDate, endDate);

        // Xử lý action
        if ("add".equals(action)) {
            boolean isAdded = recurringTransactionDAO.addRecurringTransaction(transaction);
            if (isAdded) {
                response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            } else {
                response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            }
        } else if ("update".equals(action)) {
            boolean isUpdated = recurringTransactionDAO.updateRecurringTransaction(transaction);
            if (isUpdated) {
                response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            } else {
                response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
            }
        } else {
            response.sendRedirect("/Project3/GDDinhKy/recurringtransactions.jsp");
        }
    }
}