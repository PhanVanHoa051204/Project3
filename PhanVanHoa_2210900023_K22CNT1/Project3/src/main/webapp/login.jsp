<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="login_style.css">
    <style>
        /* Reset default browser styles */

    </style>
</head>
<body>
    <h2>Đăng nhập</h2>
    <% 
        String error = request.getParameter("error");
        if ("1".equals(error)) {
    %>
        <p style="color: red;">Mật khẩu không đúng. Vui lòng kiểm tra lại mật khẩu.</p>
    <% } else if ("2".equals(error)) { %>
        <p style="color: red;">Tên người dùng không tồn tại. Vui lòng kiểm tra lại tên người dùng.</p>
    <% } %>
    <form action="user" method="post">
        <input type="hidden" name="action" value="login">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit">Đăng nhập</button>
    </form>
    <p>Bạn chưa có tài khoản ? <a href="register.jsp">Đăng ký</a></p>
</body>
</html>
