<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.financemanager.model.Category, com.financemanager.dao.CategoryDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add New Category</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Add New Category</h2>

    <!-- Form để thêm danh mục mới -->
    <form action="${pageContext.request.contextPath }/categories" method="post">
        <div class="form-group">
            <label for="category_name">Category Name:</label>
            <input type="text" class="form-control" id="category_name" name="category_name" required>
        </div>

        <div class="form-group">
            <label for="user_id">User ID:</label>
            <input type="number" class="form-control" id="user_id" name="user_id" required>
        </div>
			
        <button type = "submit">thêm</button>
    </form>

    <!-- Quay lại danh sách danh mục -->
    <a href="categories.jsp" class="btn btn-secondary mt-3">Back to Category List</a>
</body>
</html>
