<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.financemanager.model.Goal, com.financemanager.dao.GoalDAO" %>
<%
    GoalDAO goalDAO = new GoalDAO();
    List<Goal> goals = null;

    try {
        goals = goalDAO.getAllGoals();
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<p class='text-danger'>Lỗi khi lấy dữ liệu từ cơ sở dữ liệu!</p>");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Mục tiêu tài chính</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Danh sách Mục tiêu tài chính</h2>
    
        <% if (goals == null || goals.isEmpty()) { %>
            <p class="mt-3 text-danger">Chưa có mục tiêu nào hoặc lỗi kết nối!</p>
        <% } else { %>
            <table class="table table-bordered mt-3">
                <tr>
                    <th>ID</th>
                    <th>Người dùng</th>
                    <th>Tên Mục tiêu</th>
                    <th>Số tiền</th>
                    <th>Ngày hoàn thành</th>
                    <th>Hành động</th>
                </tr>
                <% for (Goal goal : goals) { %>
                    <tr>
                        <td><%= goal.getGoalId() %></td> 
                        <td><%= goal.getUserId() %></td>
                        <td><%= goal.getGoalName() %></td> 
                        <td><%= goal.getTargetAmount() %></td>
                        <td><%= goal.getTargetDate() %></td>
                        <td>
                            <a href="updateGoals.jsp?goal_id=<%= goal.getGoalId() %>" class="btn btn-warning btn-sm">Sửa</a>
                            <a
								href="${pageContext.request.contextPath }/goals?action=delete&goal_id=<%= goal.getGoalId() %>"
								class="btn btn-danger btn-sm"
								onclick="return confirm ('Ban muon xoa chu');">Xoá</a></td>
                            
                        </td>
                    </tr>
                <% } %>
            </table>
            <a href="${pageContext.request.contextPath}/TietKiem/addGoal.jsp" class="btn btn-success">Thêm Mục tiêu</a>
            <a href="${pageContext.request.contextPath}/dashboard.jsp" class="btn btn-primary">Quay lại</a>
        <% } %>
    </div>
</body>
</html>
