<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.financemanager.dao.CategoryDAO,com.financemanager.dao.UserDao, com.financemanager.model.Category,com.financemanager.model.User , java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update danh mục chi tiêu</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
	<h2 class="mt-4">Sửa chi tiêu</h2>
	<% 
    String role = (String) session.getAttribute("role");
    if (role == null || !"admin".equals(role)) {
        response.sendRedirect("users.jsp");
        return;
    }
    %>
    
	<%
	// Get the category ID from the request parameter and handle potential null value
	String categoryIdStr = request.getParameter("category_id");

	// Check if category_id is null or empty
	if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
		out.println("<p>Category ID is missing.</p>");
		return; // Stop processing if category_id is missing
	}

	int categoryId = 0;
	try {
		categoryId = Integer.parseInt(categoryIdStr);
	} catch (NumberFormatException e) {
		out.println("<p>Invalid Category ID format.</p>");
		return; // Stop processing if category_id is invalid
	}

	// Fetch the category from the database using the ID
	CategoryDAO categoryDAO = new CategoryDAO();
	Category category = categoryDAO.getCategoryById(categoryId);

	// Check if the category exists
	if (category == null) {
		out.println("<p>Category not found.</p>");
		return; // Stop processing further if category is not found
	}
	%>

	<form action="${pageContext.request.contextPath }/categories"
		method="post">
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="category_id"
			value="<%=category.getCategoryId()%>">

		<div class="form-group">
			<label for="category_name">Tên chi tiêu</label> <input type="text"
				class="form-control" id="category_name" name="category_name"
				value="<%=category.getCategoryName()%>" required>
		</div>

		
		<div class="form-group">
			<label for="user_id">User ID:</label> <select id="user_id"
				name="user_id">
				<%
				List<User> listUser = UserDao.getAllUsers();
				for (User user : listUser) {
				%>
				<option value="<%=user.getUserId()%>"><%=user.getUserId()%> - <%= user.getUsername() %></option>
				<%
				}
				%>
			</select>

		</div>

		<button type="submit" class="btn btn-primary">Update</button>
	</form>

	<!-- Back to Category List -->
	<a href="categories.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>
