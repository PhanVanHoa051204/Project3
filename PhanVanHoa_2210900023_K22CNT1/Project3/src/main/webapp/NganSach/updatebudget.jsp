<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.BudgetDAO, com.financemanager.dao.UserDao, com.financemanager.dao.CategoryDAO, com.financemanager.model.Budget, com.financemanager.model.User, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sửa ngân sách</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Sửa ngân sách</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
        }
    %>

    <%
        // Get the budget ID from the request parameter and handle potential null value
        String budgetIdStr = request.getParameter("budget_id");

        // Check if budget_id is null or empty
        if (budgetIdStr == null || budgetIdStr.trim().isEmpty()) {
            out.println("<p>Budget ID is missing.</p>");
            return; // Stop processing if budget_id is missing
        }

        int budgetId = 0;
        try {
            budgetId = Integer.parseInt(budgetIdStr);
        } catch (NumberFormatException e) {
            out.println("<p>Invalid Budget ID format.</p>");
            return; // Stop processing if budget_id is invalid
        }

        // Fetch the budget from the database using the ID
        BudgetDAO budgetDAO = new BudgetDAO();
        Budget budget = budgetDAO.getBudgetById(budgetId);

        // Check if the budget exists
        if (budget == null) {
            out.println("<p>Budget not found.</p>");
            return; // Stop processing if budget is not found
        }
    %>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <% 
        String error = request.getParameter("error");
        if ("invalid_input".equals(error)) { %>
            <div class="alert alert-danger mt-3" role="alert">
                Dữ liệu nhập không hợp lệ. Vui lòng kiểm tra lại!
            </div>
    <% } %>

    <!-- Form để cập nhật ngân sách -->
    <form action="${pageContext.request.contextPath}/budgets" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="budget_id" value="<%= budget.getBudgetId() %>">

        <div class="form-group">
            <label for="user_id">User ID:</label>
            <select id="user_id" name="user_id" class="form-control" required>
                <option value="" disabled>Chọn User ID</option>
                <%
                    List<User> listUser = UserDao.getAllUsers();
                    if (listUser != null && !listUser.isEmpty()) {
                        for (User user : listUser) {
                            
                %>
                            <option value="<%= user.getUserId() %>"><%= user.getUserId() %> - <%= user.getUsername() %></option>
                <% 
                        }
                    } else {
                        out.println("<option value='' disabled>No users available</option>");
                    }
                %>
            </select>
        </div>
		
        <div class="form-group">
            <label for="category_id">Category ID:</label>
            <select id="category_id" name="category_id" class="form-control" required>
                <option value="" disabled>Chọn Category ID</option>
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    if (categories != null && !categories.isEmpty()) {
                        for (Category category : categories) {
                            String selected = (category.getCategoryId() == budget.getCategoryId()) ? "selected" : "";
                %>
                            <option value="<%= category.getCategoryId() %>" <%= selected %>><%= category.getCategoryId() %> - <%= category.getCategoryName() %></option>
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
            <input type="number" step="0.01" min="0" class="form-control" id="amount" name="amount" value="<%= budget.getAmount() %>" required>
            <small class="form-text text-muted">Nhập số tiền lớn hơn hoặc bằng 0.</small>
        </div>

        <div class="form-group">
            <label for="month">Tháng (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="month" name="month" value="<%= budget.getMonth() %>" required>
            <small class="form-text text-muted">Chọn ngày theo định dạng YYYY-MM-DD.</small>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>

    <!-- Nút quay lại -->
    <a href="budgets.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>