<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="com.financemanager.dao.CategoryDAO,com.financemanager.dao.UserDao, com.financemanager.model.Category,com.financemanager.model.User , java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới chi tiêu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Thêm mới chi tiêu</h2>

    <!-- Form để thêm danh mục mới -->
    <form action="${pageContext.request.contextPath }/categories" method="post">
    <input type = "hidden" name = "action" value = "add">
        <div class="form-group">
            <label for="category_name">Tên chi tiêu</label>
            <input type="text" class="form-control" id="category_name" name="category_name" required>
        </div>

        <div class="form-group">
			<label for="user_id">User ID:</label> <select id="user_id"
				name="user_id">
				<% List<User> listUser = UserDao.getAllUsers();
            	for(User user : listUser ){
            	%>
				<option value="<%= user.getUserId()%>"><%= user.getUserId()%> - <%= user.getUsername() %></option>
				<%} %>
			</select>

		</div>
			
        <button type = "submit">Thêm</button>
    </form>

    <!-- Quay lại danh sách danh mục -->
    <a href="categories.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>
