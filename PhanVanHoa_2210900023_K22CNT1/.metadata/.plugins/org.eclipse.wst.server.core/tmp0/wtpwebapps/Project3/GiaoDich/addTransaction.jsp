<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.UserDao, com.financemanager.model.User, com.financemanager.dao.CategoryDAO, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm giao dịch mới</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Thêm giao dịch mới</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
        }
    %>

    <!-- Form để thêm giao dịch -->
    <form action="${pageContext.request.contextPath}/transactions" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="transaction_id" value="0"> <!-- Giá trị mặc định, tự sinh bởi database -->

        <div class="form-group">
            <label for="user_id">User ID:</label>
            <select id="user_id" name="user_id" class="form-control" required>
                <option value="" disabled selected>Chọn User ID</option>
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
                <option value="" disabled selected>Chọn Category ID</option>
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    if (categories != null && !categories.isEmpty()) {
                        for (Category category : categories) {
                %>
                            <option value="<%= category.getCategoryId() %>"><%= category.getCategoryId() %> - <%= category.getCategoryName() %></option>
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
            <input type="number" step="0.01" min="0" class="form-control" id="amount" name="amount" required>
            <small class="form-text text-muted">Nhập số tiền lớn hơn hoặc bằng 0.</small>
        </div>

        <div class="form-group">
            <label for="description">Mô tả:</label>
            <input type="text" class="form-control" id="description" name="description" required>
            <small class="form-text text-muted">Nhập mô tả giao dịch.</small>
        </div>

        <div class="form-group">
            <label for="transaction_date">Ngày giao dịch (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="transaction_date" name="transaction_date" required>
            <small class="form-text text-muted">Chọn ngày giao dịch.</small>
        </div>

        <div class="form-group">
            <label for="createdAt_date">Ngày tạo (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="createdAt_date" name="createdAt_date" required>
            <small class="form-text text-muted">Chọn ngày tạo giao dịch.</small>
        </div>

        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>

    <!-- Nút quay lại -->
   <a href="transactions.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>