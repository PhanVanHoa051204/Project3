<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.List, com.financemanager.model.Budget, com.financemanager.dao.BudgetDAO"%>
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<h2>Danh sách Ngân sách</h2>

		<table class="table table-bordered mt-3">
			<tr>
				<th>ID</th>
				<th>ID Người dùng</th>
				<th>ID Danh mục</th>
				<th>Số tiền</th>
				<th>Thời gian</th>
				<th>Hành động</th>
			</tr>
			<%
			for (Budget budget : budgets) {
			%>
			<tr>
				<td><%=budget.getBudgetId()%></td>
				<td><%=budget.getUserId()%></td>
				<td><%=budget.getCategoryId()%></td>
				<td><%=budget.getAmount()%></td>
				<td><%=(budget.getMonth() != null) ? budget.getMonth().toString() : "N/A"%></td>
				<td><a
					href="updatebudget.jsp?budget_id=<%=budget.getBudgetId()%>"
					class="btn btn-warning btn-sm">Sửa</a> <a
					href="${pageContext.request.contextPath }/budgets?action=delete&budget_id=<%= budget.getBudgetId() %>"
					class="btn btn-danger btn-sm"
					onclick="return confirm ('Ban muon xoa chu');">Xoá</a></td>
			</tr>
			<%
			}
			%>
		</table>
		<a href="addbudget.jsp" class="btn btn-success">Thêm Ngân sách</a>
		 <a
			href="${pageContext.request.contextPath}/dashboard.jsp"
			class="btn btn-primary">Quay lại</a>
	</div>
</body>
</html>
