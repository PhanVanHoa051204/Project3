<%@ page import="java.util.List, com.financemanager.model.RecurringTransaction, com.financemanager.dao.RecurringTransactionDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quản lý giao dịch định kỳ</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container">
    <h2 class="mt-4">Giao dịch định kỳ</h2>
    
    <table class="table table-bordered mt-3">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
				<th>ID Người dùng</th>
				<th>ID Danh mục</th>
				<th>Số tiền</th>
				<th>Mô tả</th>
				<th>Tần suất</th>
				<th>Ngày bắt đầu</th>
				<th>Ngày kết thúc</th>
				<th>Hành động</th>

            </tr>
        </thead>
        <tbody>
            <%
                RecurringTransactionDAO recurringTransactionDAO = new RecurringTransactionDAO();
                List<RecurringTransaction> recurringTransactions = recurringTransactionDAO.getAllRecurringTransactions();
                for (RecurringTransaction rt : recurringTransactions) {
            %>
            <tr>
                <td><%= rt.getRecurringTransactionId() %></td>
                <td><%= rt.getUserId() %></td>
                <td><%= rt.getCategoryId() %></td>
                <td><%= rt.getAmount() %></td>
                <td><%= rt.getDescription() %></td>
                <td><%= rt.getFrequency() %></td>
                <td><%= rt.getStartDate() %></td>
                <td><%= rt.getEndDate() %></td>
                <td>
                    <a href="updateRecurringTransaction.jsp?recurring_transaction_id=<%= rt.getRecurringTransactionId() %>" class="btn btn-warning btn-sm">Sửa</a>
                    <a
					href="${pageContext.request.contextPath }/recurringtransactions?action=delete&recurring_transaction_id=<%= rt.getRecurringTransactionId() %>"
					class="btn btn-danger btn-sm"
					onclick="return confirm ('Ban muon xoa chu');">Xoá</a>
                    
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <a href="addRecurringTransaction.jsp" class="btn btn-primary">Thêm giao dịch định kỳ mới</a>
    <a href="${pageContext.request.contextPath}/dashboard.jsp"
		class="btn btn-primary">Quay lại</a>
</body>
</html>
