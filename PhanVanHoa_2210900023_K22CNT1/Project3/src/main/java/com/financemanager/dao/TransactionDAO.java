package com.financemanager.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.financemanager.model.Transaction;
import com.financemanager.util.DatabaseConnection;

public class TransactionDAO {
	private static final String GET_ALL_TRANSACTIONS = "SELECT * FROM transactions";
    private static final String ADD_TRANSACTION = "INSERT INTO transactions (user_id, category_id, amount, description, transaction_date) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_TRANSACTION_BY_ID = "SELECT * FROM transactions WHERE transaction_id = ?";
    private static final String UPDATE_TRANSACTION = "UPDATE transactions SET user_id = ?, category_id = ?, amount = ?, description = ?, transaction_date = ? WHERE transaction_id = ?";
    private static final String DELETE_TRANSACTION = "DELETE FROM transactions WHERE transaction_id = ?";
    private static final String GET_TRANSACTION_BY_USER_ID = "SELECT * FROM transactions WHERE user_id = ?";

    private Connection connection;

    public TransactionDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    // Thêm giao dịch mới
    public boolean addTransaction(Transaction transaction) {
        try (PreparedStatement pstmt = connection.prepareStatement(ADD_TRANSACTION)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount()); // Dùng double thay vì BigDecimal
            pstmt.setString(4, transaction.getDescription());
            pstmt.setDate(5, Date.valueOf(transaction.getTransactionDate()));  // Đảm bảo transactionDate là LocalDate
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy tất cả giao dịch
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_TRANSACTIONS)) {
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setTransactionId(rs.getInt("transaction_id"));
                transaction.setUserId(rs.getInt("user_id"));
                transaction.setCategoryId(rs.getInt("category_id"));
                transaction.setAmount(rs.getDouble("amount"));  // Dùng double thay vì BigDecimal
                transaction.setDescription(rs.getString("description"));
                transaction.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public List<Transaction> getAllTransactionsByUserId(int Id) {
        List<Transaction> transactions = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(GET_TRANSACTION_BY_USER_ID)) {
            pstmt.setInt(1, Id); // Sử dụng tham số userId
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { // Lặp qua tất cả bản ghi
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setUserId(rs.getInt("user_id"));
                    transaction.setCategoryId(rs.getInt("category_id"));
                    transaction.setAmount(rs.getDouble("amount"));
                    transaction.setDescription(rs.getString("description"));
                    transaction.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                    transactions.add(transaction); // Thêm vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions; // Trả về danh sách
    }
    

    // Lấy giao dịch theo ID
    public Transaction getTransactionById(int transactionId) {
        try (PreparedStatement pstmt = connection.prepareStatement(GET_TRANSACTION_BY_ID)) {
            pstmt.setInt(1, transactionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Transaction transaction = new Transaction();
                    transaction.setTransactionId(rs.getInt("transaction_id"));
                    transaction.setUserId(rs.getInt("user_id"));
                    transaction.setCategoryId(rs.getInt("category_id"));
                    transaction.setAmount(rs.getDouble("amount"));  // Dùng double thay vì BigDecimal
                    transaction.setDescription(rs.getString("description"));
                    transaction.setTransactionDate(rs.getDate("transaction_date").toLocalDate());
                    return transaction;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cập nhật giao dịch
    public boolean updateTransaction(Transaction transaction) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_TRANSACTION)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setInt(2, transaction.getCategoryId());
            pstmt.setDouble(3, transaction.getAmount());  // Dùng double thay vì BigDecimal
            pstmt.setString(4, transaction.getDescription());
            pstmt.setDate(5, Date.valueOf(transaction.getTransactionDate()));
            pstmt.setInt(6, transaction.getTransactionId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa giao dịch
    public boolean deleteTransaction(int transactionId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_TRANSACTION)) {
            pstmt.setInt(1, transactionId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
