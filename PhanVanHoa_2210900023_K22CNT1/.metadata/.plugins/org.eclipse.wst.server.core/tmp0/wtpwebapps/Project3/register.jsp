<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
    <link rel="stylesheet" href="register_style.css">
    <style>

    </style>
</head>
<body>
    <h2>Đăng ký</h2>
    <form action="user" method="post">
        <input type="hidden" name="action" value="register">
        <label>Username:</label>
        <input type="text" name="username" required>
        <label>Email:</label>
        <input type="email" name="email" required>
        <label>Password:</label>
        <input type="password" name="password" required>
        <button type="submit">Ok</button>
    </form>
    <p>Bạn đã có tài khoản ? <a href="login.jsp">Đăng nhập</a></p>
</body>
</html>