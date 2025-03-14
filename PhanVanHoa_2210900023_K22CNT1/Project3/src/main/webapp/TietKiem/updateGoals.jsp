<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.financemanager.dao.UserDao, com.financemanager.model.User,com.financemanager.dao.GoalDAO, com.financemanager.model.Goal, com.financemanager.dao.CategoryDAO, com.financemanager.model.Category, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cập nhật mục tiêu tài chính</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Cập nhật mục tiêu tài chính</h2>

    <% 
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            response.sendRedirect("users.jsp");
            return;
        }

        // Lấy goal_id từ request (URL hoặc form)
        String goalIdStr = request.getParameter("goal_id");

        // Kiểm tra goal_id có hợp lệ không
        if (goalIdStr == null || goalIdStr.isEmpty()) {
            response.sendRedirect("goals.jsp?error=missing_goal_id");
            return;
        }

        int goalId;
        try {
            goalId = Integer.parseInt(goalIdStr);  // Chuyển goal_id thành kiểu int
        } catch (NumberFormatException e) {
            response.sendRedirect("goals.jsp?error=invalid_goal_id");
            return;
        }

        GoalDAO goalDAO = new GoalDAO();  // Khởi tạo đối tượng GoalDAO
        Goal goal = goalDAO.getGoalById(goalId);  // Lấy mục tiêu từ database theo goal_id

        // Kiểm tra nếu không tìm thấy mục tiêu
        if (goal == null) {
            response.sendRedirect("goals.jsp?error=notfound");  // Chuyển hướng nếu không tìm thấy mục tiêu
            return;
        }

    %>

    <!-- Hiển thị thông báo lỗi nếu có -->
    <% 
        String error = request.getParameter("error");
        if ("invalid_input".equals(error)) { %>
            <div class="alert alert-danger mt-3" role="alert">
                Dữ liệu nhập không hợp lệ. Vui lòng kiểm tra lại!
            </div>
    <% } %>

    <!-- Form để cập nhật mục tiêu -->
    <form action="${pageContext.request.contextPath}/goals" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="goal_id" value="<%= goal.getGoalId() %>"> <!-- ID của mục tiêu cần cập nhật -->

        <div class="form-group">
            <label for="user_id">User ID:</label>
            <select id="user_id" name="user_id" class="form-control" required>
                <option value="" disabled>Chọn User ID</option>
                <%
                    List<User> listUser = UserDao.getAllUsers();
                    if (listUser != null && !listUser.isEmpty()) {
                        for (User user : listUser) {
                %>
                            <option value="<%= user.getUserId() %>" <%= user.getUserId() == goal.getUserId() ? "selected" : "" %> >
                                <%= user.getUserId() %> - <%= user.getUsername() %>
                            </option>
                <% 
                        }
                    } else {
                        out.println("<option value='' disabled>No users available</option>");
                    }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="goal_name">Tên mục tiêu:</label>
            <input type="text" class="form-control" id="goal_name" name="goal_name" value="<%= goal.getGoalName() %>" required>
        </div>

        <div class="form-group">
            <label for="target_amount">Số tiền mục tiêu:</label>
            <input type="number" step="0.01" min="0" class="form-control" id="target_amount" name="target_amount" value="<%= goal.getTargetAmount() %>" required>
            <small class="form-text text-muted">Nhập số tiền lớn hơn hoặc bằng 0.</small>
        </div>

        <div class="form-group">
            <label for="current_amount">Số tiền hiện tại:</label>
            <input type="number" step="0.01" min="0" class="form-control" id="current_amount" name="current_amount" value="<%= goal.getCurrentAmount() %>" required>
            <small class="form-text text-muted">Nhập số tiền hiện tại bạn đã tiết kiệm hoặc đã đầu tư.</small>
        </div>

        <div class="form-group">
            <label for="target_date">Ngày hoàn thành mục tiêu (YYYY-MM-DD):</label>
            <input type="date" class="form-control" id="target_date" name="target_date" value="<%= goal.getTargetDate() %>" required>
            <small class="form-text text-muted">Chọn ngày hoàn thành mục tiêu theo định dạng YYYY-MM-DD.</small>
        </div>

        <button type="submit" class="btn btn-primary">Cập nhật</button>
    </form>

    <!-- Nút quay lại -->
    <a href="goals.jsp" class="btn btn-secondary mt-3">Quay lại</a>
</body>
</html>
