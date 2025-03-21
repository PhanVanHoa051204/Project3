package com.financemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.financemanager.model.Budget;
import com.financemanager.util.DatabaseConnection;

public class BudgetDAO {
	private static final String ADD_BUDGET = "INSERT INTO budgets (user_id, category_id, amount, month) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_BUDGETS = "SELECT * FROM budgets";
    private static final String GET_BUDGET_BY_ID = "SELECT * FROM budgets WHERE budget_id = ?";
    private static final String GET_BUDGET_BY_USER_ID = "SELECT * FROM budgets WHERE user_id = ?";
    private static final String UPDATE_BUDGET = "UPDATE budgets SET user_id = ?, category_id = ?, amount = ?, month = ? WHERE budget_id = ?";
    private static final String DELETE_BUDGET = "DELETE FROM budgets WHERE budget_id = ?";
    private Connection connection;

    // Constructor lấy kết nối từ DBConnection
    public BudgetDAO() {
        try {
            this.connection = DatabaseConnection.getConnection(); // Lấy kết nối
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean addBudget(Budget budget) {
        try (PreparedStatement pstmt = connection.prepareStatement(ADD_BUDGET)) {
            pstmt.setInt(1, budget.getUserId());
            pstmt.setInt(2, budget.getCategoryId());
            pstmt.setDouble(3, budget.getAmount());
            pstmt.setDate(4, budget.getMonth());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Budget> getAllBudgets() {
        List<Budget> budgets = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_BUDGETS)) {
            while (rs.next()) {
                Budget budget = new Budget();
                budget.setBudgetId(rs.getInt("budget_id"));
                budget.setUserId(rs.getInt("user_id"));
                budget.setCategoryId(rs.getInt("category_id"));
                budget.setAmount(rs.getDouble("amount"));
                budget.setMonth(rs.getDate("month"));
                budgets.add(budget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgets;
    }
    public List<Budget> getBudgetByUserId(int id) {
        List<Budget> budgets = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(GET_BUDGET_BY_USER_ID)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { // Thay if bằng while để lấy tất cả bản ghi
                    Budget budget = new Budget();
                    budget.setBudgetId(rs.getInt("budget_id"));
                    budget.setUserId(rs.getInt("user_id"));
                    budget.setCategoryId(rs.getInt("category_id"));
                    budget.setAmount(rs.getDouble("amount"));
                    budget.setMonth(rs.getDate("month"));
                    budgets.add(budget);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgets;
    }

    public Budget getBudgetById(int budgetId) {
        try (PreparedStatement pstmt = connection.prepareStatement(GET_BUDGET_BY_ID)) {
            pstmt.setInt(1, budgetId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Budget budget = new Budget();
                    budget.setBudgetId(rs.getInt("budget_id"));
                    budget.setUserId(rs.getInt("user_id"));
                    budget.setCategoryId(rs.getInt("category_id"));
                    budget.setAmount(rs.getDouble("amount"));
                    budget.setMonth(rs.getDate("month"));
                    return budget;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateBudget(Budget budget) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_BUDGET)) {
            pstmt.setInt(1, budget.getUserId());
            pstmt.setInt(2, budget.getCategoryId());
            pstmt.setDouble(3, budget.getAmount());
            pstmt.setDate(4, budget.getMonth());
            pstmt.setInt(5, budget.getBudgetId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteBudget(int budgetId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_BUDGET)) {
            pstmt.setInt(1, budgetId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
