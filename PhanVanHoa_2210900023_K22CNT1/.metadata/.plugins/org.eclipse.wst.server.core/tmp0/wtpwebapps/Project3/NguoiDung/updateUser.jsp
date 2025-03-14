<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.UserDao, com.financemanager.model.User, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Cập nhật User</h2>

    <%
        UserDao userDao = new UserDao();
        int userId = Integer.parseInt(request.getParameter("user_id"));
        User user = userDao.getUserById(userId); // Giả định có phương thức này
        if (user == null) {
            response.sendRedirect("users.jsp?error=user_not_found");
            return;
        }
    %>

    <!-- Form để cập nhật người dùng -->
    <form action="${pageContext.request.contextPath}/PvhUserServlet" method="post" class="mt-3">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" value="<%= user.getUsername() %>" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" value="<%= user.getPasswordHash() %>" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" name="email" value="<%= user.getEmail() %>" required>
        </div>
        <div class="form-group">
            <label for="role">Role:</label>
            <select class="form-control" id="role" name="role">
                <option value="user" <%= "user".equals(user.getRole()) ? "selected" : "" %>>User</option>
                <option value="admin" <%= "admin".equals(user.getRole()) ? "selected" : "" %>>Admin</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>

    <!-- Quay lại danh sách người dùng -->
    <a href="users.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>