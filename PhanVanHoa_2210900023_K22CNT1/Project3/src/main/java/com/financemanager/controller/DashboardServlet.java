package com.financemanager.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/dashboard") // Cấu hình servlet cho URL "/dashboard"
public class DashboardServlet extends HttpServlet {
    private Connection connection;

    public void init() {
        try {
            // Kết nối đến MySQL database (Kiểm tra lại thông tin kết nối)
            String url = "jdbc:mysql://localhost:3306/PhanVanHoa_K22CNT2";  // Đảm bảo URL là chính xác
            String user = "root"; // Thay đổi tên người dùng nếu cần
            String password = "@Hoa2004"; // Thay đổi mật khẩu nếu cần

            this.connection = DriverManager.getConnection(url, user, password);
            
            if (this.connection != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công");
            } else {
                System.out.println("Kết nối cơ sở dữ liệu không thành công");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Đảm bảo ghi lại thông báo lỗi nếu không thể kết nối
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kiểm tra nếu kết nối là null
        if (connection == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Không thể kết nối tới cơ sở dữ liệu");
            return; // Dừng thực thi nếu không có kết nối
        }

        // Giả sử người dùng đã đăng nhập và có `user_id`
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("user_id");

        // Kiểm tra nếu không có userId trong session, chuyển hướng đến trang đăng nhập
        if (userId == null) {
            response.sendRedirect("login.jsp"); // Nếu người dùng chưa đăng nhập, chuyển hướng về trang đăng nhập
            return;
        }

        try {
            // Truy vấn tổng chi tiêu
            String totalExpensesQuery = "SELECT SUM(amount) FROM transactions WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(totalExpensesQuery);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            double totalExpenses = 0;
            if (resultSet.next()) {
                totalExpenses = resultSet.getDouble(1);
            }

            // Truy vấn chi tiêu tháng này
            String monthlyExpensesQuery = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND MONTH(transaction_date) = MONTH(CURRENT_DATE())";
            statement = connection.prepareStatement(monthlyExpensesQuery);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            double monthlyExpenses = 0;
            if (resultSet.next()) {
                monthlyExpenses = resultSet.getDouble(1);
            }

            // Truy vấn tổng tiết kiệm (Giả sử lấy từ bảng goals)
            String totalSavingsQuery = "SELECT SUM(target_amount - current_amount) FROM goals WHERE user_id = ?";
            statement = connection.prepareStatement(totalSavingsQuery);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            double totalSavings = 0;
            if (resultSet.next()) {
                totalSavings = resultSet.getDouble(1);
            }

            // Truy vấn ngân sách cho các danh mục (ví dụ: Food, Entertainment, Bills)
            String budgetQuery = "SELECT category_id, amount FROM budgets WHERE user_id = ?";
            statement = connection.prepareStatement(budgetQuery);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Lấy ngân sách cho từng danh mục (Food, Entertainment, Bills)
                int categoryId = resultSet.getInt("category_id");
                double amount = resultSet.getDouble("amount");
                request.setAttribute("budget_" + categoryId, amount);
            }

            // Chuyển hướng đến JSP
            request.setAttribute("totalExpenses", totalExpenses);
            request.setAttribute("monthlyExpenses", monthlyExpenses);
            request.setAttribute("totalSavings", totalSavings);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi truy vấn cơ sở dữ liệu");
        }
    }

}
