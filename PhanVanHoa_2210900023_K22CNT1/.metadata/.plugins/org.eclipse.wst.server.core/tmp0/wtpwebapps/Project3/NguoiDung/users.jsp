<%@ page import="java.util.List, com.financemanager.model.User, com.financemanager.dao.UserDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý người dùng</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container">
	
    <h2 class="mt-4">Danh sách người dùng</h2>
    
    <table class="table table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên người dùng</th>
                <th>Email</th>
                <th>Vai trò</th>
                <th>Chức năng</th>
            </tr>
        </thead>
        <tbody>
            <%
                UserDao userDao = new UserDao();
                List<User> users = userDao.getAllUsers();
                for (User user : users) {
            %>
            <tr>
                <td><%= user.getUserId() %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getRole() %></td>
                <td>
                    <a href="updateUser.jsp?user_id=<%= user.getUserId() %>" class="btn btn-warning btn-sm">Sửa</a>
                    <a href="${pageContext.request.contextPath}/PvhUserServlet?action=delete&user_id=<%= user.getUserId() %>" 
                       class="btn btn-danger btn-sm" onclick="return confirm('Bạn muốn xóa người dùng này không?');">Xóa</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="addUser.jsp" class="btn btn-primary">Thêm người dùng</a>
    <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn btn-primary">Quay lại</a>
</body>
</html>