<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.UserDao, com.financemanager.model.User, com.financemanager.dao.CategoryDAO, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm ngân sách mới</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Thêm ngân sách mới</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
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

    <!-- Form để thêm ngân sách -->
    <form action="${pageContext.request.contextPath}/budgets" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="budget_id" value="0"> <!-- Giá trị mặc định, tự sinh bởi database -->

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
                for (Category category : categories) {
                %>
                            <option value="<%= category.getCategoryId() %>"><%= category.getCategoryId() %> - <%= category.getCategoryName() %></option>
                <% 
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
            <label for="month">Tháng (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="month" name="month" required>
            <small class="form-text text-muted">Chọn ngày theo định dạng YYYY-MM-DD.</small>
        </div>

        <button type="submit" class="btn btn-primary">Thêm</button>
    </form>

    <!-- Nút quay lại -->
    <a href="budgets.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>