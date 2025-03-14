<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <link rel="stylesheet" href="styles.css">
    <style>
        /* Reset default browser styles */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Body */
body {
    font-family: 'Arial', sans-serif;
    background-color: #e9eff1;  /* Facebook-like background color */
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    flex-direction: column;
}

/* Heading */
h2 {
    font-size: 24px;
    margin-bottom: 20px;
    color: #1877f2; /* Facebook's blue color */
    font-weight: bold;
    text-align: center;
}

/* Form container */
form {
    background-color: white;
    padding: 40px;
    border-radius: 8px;
    box-shadow: 0 2px 15px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    text-align: center;
}

/* Input fields */
input[type="text"],
input[type="password"] {
    width: 100%;
    padding: 12px;
    margin: 10px 0;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 16px;
    background-color: #f4f4f4;
}

input[type="text"]:focus,
input[type="password"]:focus {
    border-color: #1877f2;
    outline: none;
}

/* Submit button */
button {
    width: 100%;
    padding: 12px;
    background-color: #1877f2;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 18px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #166fe5;
}

/* Footer and Links */
p {
    font-size: 14px;
    margin-top: 20px;
    text-align: center;
}

a {
    color: #1877f2;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}

/* Responsive Design */
@media (max-width: 480px) {
    form {
        padding: 20px;
    }
}
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
