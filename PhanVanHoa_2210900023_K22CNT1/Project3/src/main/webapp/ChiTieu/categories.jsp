<%@ page import="java.util.List, com.financemanager.model.Category, com.financemanager.dao.CategoryDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chi Tiêu</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Danh mục chi tiêu</h2>
    
    <table class="table table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên Danh Mục</th>
                <th>ID Người dùng</th>
                <th>Chức năng</th>
            </tr>
        </thead>
        <tbody>
            <%
                CategoryDAO categoryDAO = new CategoryDAO();
                List<Category> categories = categoryDAO.getAllCategories();
                for (Category category : categories) {
            %>
            <tr>
                <td><%= category.getCategoryId() %></td>
                <td><%= category.getCategoryName() %></td>
                <td><%= category.getUserId() %></td>
                <td>
                    <a href="updateCategory.jsp?category_id=${category.categoryId}" class="btn btn-warning">Edit</a>
                    <a href="categories.jsp?id=<%= category.getCategoryId() %>" class="btn btn-danger btn-sm">Delete</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="addcategory.jsp" class="btn btn-primary">Thêm danh mục</a>
</body>
</html>
