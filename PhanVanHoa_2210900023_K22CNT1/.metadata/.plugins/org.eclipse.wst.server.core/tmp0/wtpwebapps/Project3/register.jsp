<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng ký</title>
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
    background-color: #f9f9f9;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    flex-direction: column;
}

/* Heading */
h2 {
    font-size: 28px;
    margin-bottom: 20px;
    color: #333;
}

/* Form container */
form {
    background-color: #ffffff;
    padding: 30px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
    text-align: center;
}

/* Input fields */
input[type="text"],
input[type="email"],
input[type="password"] {
    width: 100%;
    padding: 10px;
    margin: 10px 0 20px 0;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
}

/* Submit button */
button {
    width: 100%;
    padding: 12px;
    background-color: #4154f1;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 18px;
    cursor: pointer;
}

button:hover {
    background-color: #2eca6a;
}

/* Link to Login */
p {
    font-size: 14px;
    margin-top: 20px;
}

a {
    color: #4154f1;
    text-decoration: none;
}

a:hover {
    text-decoration: underline;
}
    	
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