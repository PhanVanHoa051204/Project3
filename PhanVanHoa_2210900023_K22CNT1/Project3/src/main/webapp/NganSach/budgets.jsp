<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List, com.financemanager.model.Budget, com.financemanager.dao.BudgetDAO" %>
<%
    BudgetDAO budgetDAO = new BudgetDAO();
    List<Budget> budgets = budgetDAO.getAllBudgets();
    
    if (budgets.isEmpty()) {
        out.println("<p class='text-danger'>Không có dữ liệu ngân sách hoặc lỗi kết nối.</p>");
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Quản lý Ngân sách</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Danh sách Ngân sách</h2>
        <a href="add_budget.jsp" class="btn btn-success">Thêm Ngân sách</a>
        <table class="table table-bordered mt-3">
            <tr>
                <th>ID</th>
                <th>Người dùng</th>
                <th>Danh mục</th>
                <th>Số tiền</th>
                <th>Thời gian</th>
                <th>Hành động</th>
            </tr>
            <% for (Budget budget : budgets) { %>
                <tr>
                    <td><%= budget.getBudgetId() %></td>
                    <td><%= budget.getUserId() %></td>
                    <td><%= budget.getCategoryId() %></td>
                    <td><%= budget.getAmount() %></td>
                    <td><%= (budget.getMonth() != null) ? budget.getMonth().toString() : "N/A" %></td>
                    <td>
                        <a href="edit_budget.jsp?id=<%= budget.getBudgetId() %>" class="btn btn-warning">Sửa</a>
                        <a href="delete_budget?id=<%= budget.getBudgetId() %>" class="btn btn-danger">Xóa</a>
                    </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
