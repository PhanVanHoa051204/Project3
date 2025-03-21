<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/js/all.min.js"></script>
<link rel="stylesheet" href="style.css">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<!-- Sidebar -->
			<div class="sidebar">
				<div class="logo">Admin</div>
				<ul>
					<li class="active"><a href="#"><i
							class="fas fa-tachometer-alt me-2 "></i> Quản trị</a></li>
					<li><a
						href="${pageContext.request.contextPath}/NguoiDung/users.jsp"><i
							class="fas fa-users me-2"></i> Quản lý người dùng</a></li>
					<li><a
						href="${pageContext.request.contextPath}/ChiTieu/categories.jsp"><i
							class="fas fa-tags me-2"></i> Quản lý chi tiêu</a></li>
					<li><a
						href="${pageContext.request.contextPath}/NganSach/budgets.jsp"><i
							class="fas fa-wallet me-2"></i> Quản lý ngân sách</a></li>
					<li><a
						href="${pageContext.request.contextPath}/GiaoDich/transactions.jsp"><i
							class="fas fa-exchange-alt me-2"></i> Quản lý giao dịch</a></li>
					<li><a
						href="${pageContext.request.contextPath}/GDDinhKy/recurringtransactions.jsp"><i
							class="fas fa-sync-alt me-2"></i> Quản lý giao dịch định kỳ</a></li>
					<li><a
						href="${pageContext.request.contextPath}/TietKiem/goals.jsp"><i
							class="fas fa-piggy-bank me-2"></i> Tiết kiệm</a></li>
					<li><a href="https://web.facebook.com/PhanHoa05122004"><i
							class="fas fa-address-book me-2"></i> Liên hệ</a></li>
				</ul>
			</div>

			<!-- Main Content -->
			<div class="main-content">
				<header>
					<div class="">
						<h1>Chào mừng đến với trang Admin</h1>
					</div>
					<div class="submit-section">
						<a href="login.jsp" class="logout-btn">Đăng xuất</a>
					</div>
				</header>

				<!-- Dashboard Content -->
				<div class="dashboard-content">
					<nav aria-label="breadcrumb">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="dashboard.jsp">Home</a></li>
							<li class="breadcrumb-item active" aria-current="page">Dashboard</li>
						</ol>
					</nav>

					<div class="cards">
						<div class="card stats-card">
							<div class="card-icon">
								<i class="fas fa-shopping-cart"></i>
							</div>
							<div class="card-content">
								<h3>Sales | Today</h3>
								<p class="stats-value">145</p>
								<span class="stats-change text-success">12% increase</span>
							</div>
						</div>
						<div class="card stats-card">
							<div class="card-icon">
								<i class="fas fa-dollar-sign"></i>
							</div>
							<div class="card-content">
								<h3>Revenue | This Month</h3>
								<p class="stats-value">$3,264</p>
								<span class="stats-change text-success">8% increase</span>
							</div>
						</div>
						<div class="card stats-card">
							<div class="card-icon">
								<i class="fas fa-users"></i>
							</div>
							<div class="card-content">
								<h3>Customers | This Year</h3>
								<p class="stats-value">1,244</p>
								<span class="stats-change text-warning">12% decrease</span>
							</div>
						</div>
						<div class="card stats-card">
							<div class="card-content">
								<a href="${pageContext.request.contextPath}/TietKiem/goals.jsp" class=""><i
									class="fas fa-piggy-bank"></i> Tiết kiệm</a>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-11">
							<div class="chart-section">
								<h3>Reports | Today</h3>
								<canvas id="reportsChart"></canvas>
							</div>
						</div>

					</div>

					<div class="recent-sales">
						<h3>Recent Sales | Today</h3>
						<table class="table table-bordered">
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
							<select class="form-control d-inline-block w-auto">
								<option>10 entries per page</option>
							</select> <input type="text"
								class="form-control d-inline-block w-auto ml-2"
								placeholder="Search...">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Scripts -->
	<script>
		// Reports Chart
		const reportsCtx = document.getElementById('reportsChart').getContext(
				'2d');
		new Chart(reportsCtx, {
			type : 'line',
			data : {
				labels : [ '00:00', '01:00', '02:00', '03:00', '04:00',
						'05:00', '06:00' ],
				datasets : [ {
					label : 'Sales',
					data : [ 20, 40, 60, 50, 70, 90, 80 ],
					borderColor : 'rgba(54, 162, 235, 1)',
					backgroundColor : 'rgba(54, 162, 235, 0.2)',
					fill : true,
					tension : 0.4
				}, {
					label : 'Revenue',
					data : [ 10, 30, 50, 40, 60, 80, 70 ],
					borderColor : 'rgba(75, 192, 192, 1)',
					backgroundColor : 'rgba(75, 192, 192, 0.2)',
					fill : true,
					tension : 0.4
				}, {
					label : 'Customers',
					data : [ 30, 20, 40, 30, 50, 40, 20 ],
					borderColor : 'rgba(255, 159, 64, 1)',
					backgroundColor : 'rgba(255, 159, 64, 0.2)',
					fill : true,
					tension : 0.4
				} ]
			},
			options : {
				scales : {
					y : {
						beginAtZero : true
					}
				}
			}
		});

		// Budget Chart (Radar Chart)
		const budgetCtx = document.getElementById('budgetChart').getContext(
				'2d');
		new Chart(budgetCtx, {
			type : 'radar',
			data : {
				labels : [ 'Administration', 'Marketing', 'Development',
						'Sales', 'on Technology' ],
				datasets : [ {
					label : 'Allocated Budget',
					data : [ 65, 59, 80, 81, 56 ],
					backgroundColor : 'rgba(54, 162, 235, 0.2)',
					borderColor : 'rgba(54, 162, 235, 1)',
					borderWidth : 2
				}, {
					label : 'Actual Spending',
					data : [ 28, 48, 40, 19, 86 ],
					backgroundColor : 'rgba(75, 192, 192, 0.2)',
					borderColor : 'rgba(75, 192, 192, 1)',
					borderWidth : 2
				} ]
			},
			options : {
				scales : {
					r : {
						beginAtZero : true
					}
				}
			}
		});
	</script>
</body>
</html>