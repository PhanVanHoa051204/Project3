<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin</title>
<link rel="stylesheet" href="style.css">

</head>
<body>
	<div class="container">
		<!-- Sidebar -->
		<div class="sidebar">
			<div class="logo">Admin</div>
			<ul>
				<li class="active"><a href="#">Quản trị</a></li>
				<li><a class=""
					href="${pageContext.request.contextPath}/NguoiDung/users.jsp">Quản
						lý người dùng</a></li>
				<li><a class=""
					href="${pageContext.request.contextPath}/ChiTieu/categories.jsp">Quản
						lý chi tiêu</a></li>
				<li><a class=""
					href="${pageContext.request.contextPath}/NganSach/budgets.jsp">Quản
						lý ngân sách</a></li>
				<li><a
					href="${pageContext.request.contextPath}/GiaoDich/transactions.jsp">Quản
						lý giao dịch</a></li>
				<li><a
					href="${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp">Quản
						lý giao dịch định kỳ</a></li>
				<li><a
					href="${pageContext.request.contextPath}/TietKiem/goals.jsp">Tiết
						kiệm</a></li>
				<li><a href="https://web.facebook.com/PhanHoa05122004">Liên
						hệ</a></li>

			</ul>
		</div>

		<!-- Main Content -->
		<div class="main-content">
			<header>
				<div class="">
					<!-- <input type="" placeholder=""> -->
				</div>
				<div class="submit-section">
					<a href="login.jsp" class="logout-btn">Đăng xuất</a>
				</div>
			</header>

			<!-- <!-- Dashboard Content -->
			<div class="dashboard-content">
				<h2>Chào mừng đến với trang Admin</h2>


				<div class="cards">
					<div class="card">
						<h3>Sales Today</h3>
						<p>145</p>
						<span class="increase">12% increase</span>
					</div>
					<div class="card">
						<h3>Revenue This Month</h3>
						<p>$3,264</p>
						<span class="increase">8% increase</span>
					</div>
					<div class="card">
						<h3>Customers This Year</h3>
						<p>1,244</p>
						<span class="decrease">12% decrease</span>
					</div>
				</div>

				Reports Chart
				<div class="chart-section">
					<h3>Reports | Today</h3>
					<canvas id="reportsChart"></canvas>
				</div>

				Recent Activity
				<div class="recent-activity">
					<h3>Recent Activity | Today</h3>
					<ul>
						<li><span class="dot green"></span>32 min - Quia quaerere rem
							explicabo officiis beatae</li>
						<li><span class="dot red"></span>56 min - Voluptatem
							blanditiis blanditiis eveniet</li>
						<li><span class="dot blue"></span>2 hrs - Voluptates corrupti
							molestias voluptatem</li>
						<li><span class="dot yellow"></span>1 day - Tempore autem
							saepe occaecati voluptatem tempore</li>
					</ul>
				</div>

				Budget Report
				<div class="budget-report">
					<h3>Budget Report | This Month</h3>
					<canvas id="budgetChart"></canvas>
				</div>

				Recent Sales
				<div class="recent-sales">
					<h3>Recent Sales | Today</h3>
					<table>
						<thead>
							<tr>
								<th>ID</th>
								<th>Product</th>
								<th>Price</th>
								<th>Time</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>#123</td>
								<td>Product A</td>
								<td>$50</td>
								<td>10:00 AM</td>
							</tr>

						</tbody>
					</table>
					<div class="table-footer">
						<select>
							<option>10 entries per page</option>
						</select> <input type="text" placeholder="Search...">
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>