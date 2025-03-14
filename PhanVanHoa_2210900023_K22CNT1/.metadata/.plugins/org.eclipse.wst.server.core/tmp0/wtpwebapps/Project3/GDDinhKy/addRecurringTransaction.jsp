<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.UserDao, com.financemanager.model.User, com.financemanager.dao.CategoryDAO, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm giao dịch định kỳ mới</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Thêm giao dịch định kỳ mới</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
        }
    %>

    <!-- Form để thêm giao dịch định kỳ -->
    <form action="${pageContext.request.contextPath}/recurringtransactions" method="post">
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
            <label for="frequency">Tần suất:</label>
            <select id="frequency" name="frequency" class="form-control" required>
                <option value="" disabled selected>Chọn tần suất</option>
                <option value="daily">Hàng ngày</option>
                <option value="weekly">Hàng tuần</option>
                <option value="monthly">Hàng tháng</option>
                <option value="yearly">Hàng năm</option>
            </select>
        </div>

        <div class="form-group">
            <label for="start_date">Ngày bắt đầu (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="start_date" name="start_date" required>
            <small class="form-text text-muted">Chọn ngày bắt đầu.</small>
        </div>

        <div class="form-group">
            <label for="end_date">Ngày kết thúc (YYYY-MM-DD, để trống nếu không có):</label>
            <input type="date" class="form-control" id="end_date" name="end_date">
            <small class="form-text text-muted">Chọn ngày kết thúc (nếu có).</small>
        </div>

        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>

    <!-- Nút quay lại -->
    <a href="recurringtransactions.jsp" class="btn btn-secondary mt-3">Quay lại</a>
    
</body>
</html>