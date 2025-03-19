package com.financemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.financemanager.model.RecurringTransaction;
import com.financemanager.util.DatabaseConnection;

public class RecurringTransactionDAO {
	private static final String ADD_RECURRING_TRANSACTION = "INSERT INTO recurring_transactions (user_id, category_id, amount, description, frequency, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_ALL_RECURRING_TRANSACTIONS = "SELECT * FROM recurring_transactions";
    private static final String GET_RECURRING_TRANSACTION_BY_ID = "SELECT * FROM recurring_transactions WHERE recurring_transaction_id = ?";
    private static final String UPDATE_RECURRING_TRANSACTION = "UPDATE recurring_transactions SET user_id = ?, category_id = ?, amount = ?, description = ?, frequency = ?, start_date = ?, end_date = ? WHERE recurring_transaction_id = ?";
    private static final String DELETE_RECURRING_TRANSACTION = "DELETE FROM recurring_transactions WHERE recurring_transaction_id = ?";
    private static final String GET_RECURRING_TRANSACTION_BY_USER_ID = "SELECT * FROM recurring_transactions WHERE user_id = ?";
    private Connection connection;

    // Constructor khởi tạo kết nối CSDL
    public RecurringTransactionDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            this.connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PhanVanHoa_K22CNT2", "root", "@Hoa2004"
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC Driver not found!");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to Database!");
        }
    }

    public boolean addRecurringTransaction(RecurringTransaction transaction) {
        try (PreparedStatement pstmt = connection.prepareStatement(ADD_RECURRING_TRANSACTION)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setString(5, transaction.getFrequency());
            pstmt.setDate(6, transaction.getStartDate());
            pstmt.setDate(7, transaction.getEndDate());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<RecurringTransaction> getAllRecurringTransactions() {
        List<RecurringTransaction> transactions = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_RECURRING_TRANSACTIONS)) {
            while (rs.next()) {
                RecurringTransaction transaction = new RecurringTransaction();
                transaction.setRecurringTransactionId(rs.getInt("recurring_transaction_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setCategoryId(rs.getInt("category_id"));
                transaction.setAmount(rs.getDouble("amount"));
                transaction.setDescription(rs.getString("description"));
                transaction.setFrequency(rs.getString("frequency"));
                transaction.setStartDate(rs.getDate("start_date"));
                transaction.setEndDate(rs.getDate("end_date"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public List<RecurringTransaction> getAllRecurringTransactionsByUserId(int Id) {
        List<RecurringTransaction> transactions = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(GET_RECURRING_TRANSACTION_BY_USER_ID)) {
            pstmt.setInt(1, Id); 
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { // Lặp qua tất cả bản ghi
                    RecurringTransaction transaction = new RecurringTransaction();
                    transaction.setRecurringTransactionId(rs.getInt("recurring_transaction_id"));
                    transaction.setUserId(rs.getInt("user_id"));
                    transaction.setCategoryId(rs.getInt("category_id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setDescription(rs.getString("description"));
                    transaction.setFrequency(rs.getString("frequency"));
                    transaction.setStartDate(rs.getDate("start_date"));
                    transaction.setEndDate(rs.getDate("end_date"));
                    transactions.add(transaction); // Thêm vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions; // Trả về danh sách
    }

    public RecurringTransaction getRecurringTransactionById(int transactionId) {
        try (PreparedStatement pstmt = connection.prepareStatement(GET_RECURRING_TRANSACTION_BY_ID)) {
            pstmt.setInt(1, transactionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    RecurringTransaction transaction = new RecurringTransaction();
                    transaction.setRecurringTransactionId(rs.getInt("recurring_transaction_id"));
                    transaction.setUserId(rs.getInt("user_id"));
                    transaction.setCategoryId(rs.getInt("category_id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setDescription(rs.getString("description"));
                    transaction.setFrequency(rs.getString("frequency"));
                    transaction.setStartDate(rs.getDate("start_date"));
                    transaction.setEndDate(rs.getDate("end_date"));
                    return transaction;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateRecurringTransaction(RecurringTransaction transaction) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_RECURRING_TRANSACTION)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount());
            pstmt.setString(4, transaction.getDescription());
            pstmt.setString(5, transaction.getFrequency());
            pstmt.setDate(6, transaction.getStartDate());
            pstmt.setDate(7, transaction.getEndDate());
            pstmt.setInt(8, transaction.getRecurringTransactionId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteRecurringTransaction(int recurringTransactionId) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM recurring_transactions WHERE recurring_transaction_id = ?")) {
            pstmt.setInt(1, recurringTransactionId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
