<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.RecurringTransactionDAO, com.financemanager.dao.UserDao, com.financemanager.dao.CategoryDAO, com.financemanager.model.RecurringTransaction, com.financemanager.model.User, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa giao dịch định kỳ</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Sửa giao dịch định kỳ</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
        }
    %>

    <%
        // Get the recurring transaction ID from the request parameter and handle potential null value
        String recurringTransactionIdStr = request.getParameter("recurring_transaction_id");

        // Check if recurring_transaction_id is null or empty
        if (recurringTransactionIdStr == null || recurringTransactionIdStr.trim().isEmpty()) {
            response.sendRedirect("${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        int recurringTransactionId = 0;
        try {
            recurringTransactionId = Integer.parseInt(recurringTransactionIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp");
            return;
        }

        // Fetch the transaction from the database using the ID
        RecurringTransactionDAO recurringTransactionDAO = new RecurringTransactionDAO();
        RecurringTransaction transaction = recurringTransactionDAO.getRecurringTransactionById(recurringTransactionId);

        // Check if the transaction exists
        if (transaction == null) {
            response.sendRedirect("${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp");
            return;
        }
    %>

    <!-- Form để cập nhật giao dịch định kỳ -->
    <form action="${pageContext.request.contextPath}/recurringtransactions" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="recurring_transaction_id" value="<%= transaction.getRecurringTransactionId() %>">

        <div class="form-group">
            <label for="user_id">User ID:</label>
            <select id="user_id" name="user_id" class="form-control" required>
                <option value="" disabled>Chọn User ID</option>
                <%
                    List<User> listUser = UserDao.getAllUsers();
                    if (listUser != null && !listUser.isEmpty()) {
                        for (User user : listUser) {
                            String selected = (user.getUserId() == transaction.getUserId()) ? "selected" : "";
                %>
                            <option value="<%= user.getUserId() %>" <%= selected %>><%= user.getUserId() %> - <%= user.getUsername() != null ? user.getUsername() : "" %></option>
                <% 
                        }
                    } else {
                        out.println("<option value='' disabled>No users available</option>");
                    }
                %>
            </select>
        </div>
		
        <div class="form-group">
            <label for="category_id">Chi tiêu ID:</label>
            <select id="category_id" name="category_id" class="form-control" required>
                <option value="" disabled>Chọn Chi Tiêu ID</option>
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    if (categories != null && !categories.isEmpty()) {
                        for (Category category : categories) {
                            String selected = (category.getCategoryId() == transaction.getCategoryId()) ? "selected" : "";
                %>
                            <option value="<%= category.getCategoryId() %>" <%= selected %>><%= category.getCategoryId() %> - <%= category.getCategoryName() != null ? category.getCategoryName() : "" %></option>
                <% 
                        }
                    } else {
                        out.println("<option value='' disabled>No categories available</option>");
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="amount">Số tiền:</label>
            <input type="number" step="0.01" min="0" class="form-control" id="amount" name="amount" value="<%= transaction.getAmount() != null ? transaction.getAmount() : "0.0" %>" required>
            <small class="form-text text-muted">Nhập số tiền lớn hơn hoặc bằng 0.</small>
        </div>

        <div class="form-group">
            <label for="description">Mô tả:</label>
            <input type="text" class="form-control" id="description" name="description" value="<%= transaction.getDescription() != null ? transaction.getDescription() : "" %>" required>
            <small class="form-text text-muted">Nhập mô tả giao dịch.</small>
        </div>

        <div class="form-group">
            <label for="frequency">Tần suất:</label>
            <select id="frequency" name="frequency" class="form-control" required>
                <option value="" disabled>Chọn tần suất</option>
                <option value="daily" <%= "daily".equals(transaction.getFrequency() != null ? transaction.getFrequency() : "") ? "selected" : "" %>>Hàng ngày</option>
                <option value="weekly" <%= "weekly".equals(transaction.getFrequency() != null ? transaction.getFrequency() : "") ? "selected" : "" %>>Hàng tuần</option>
                <option value="monthly" <%= "monthly".equals(transaction.getFrequency() != null ? transaction.getFrequency() : "") ? "selected" : "" %>>Hàng tháng</option>
                <option value="yearly" <%= "yearly".equals(transaction.getFrequency() != null ? transaction.getFrequency() : "") ? "selected" : "" %>>Hàng năm</option>
            </select>
        </div>

        <div class="form-group">
            <label for="start_date">Ngày bắt đầu (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="start_date" name="start_date" value="<%= transaction.getStartDate() != null ? transaction.getStartDate() : "" %>" required>
            <small class="form-text text-muted">Chọn ngày bắt đầu.</small>
        </div>

        <div class="form-group">
            <label for="end_date">Ngày kết thúc (YYYY-MM-DD, để trống nếu không có):</label>
            <input type="date" class="form-control" id="end_date" name="end_date" value="<%= transaction.getEndDate() != null ? transaction.getEndDate() : "" %>">
            <small class="form-text text-muted">Chọn ngày kết thúc (nếu có).</small>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>

    <!-- Nút quay lại -->
    <a href="${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>