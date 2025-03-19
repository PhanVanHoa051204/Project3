package com.financemanager.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.financemanager.model.Goal;
import com.financemanager.util.DatabaseConnection;

public class GoalDAO {
	private static final String ADD_GOAL = "INSERT INTO goals (user_id, goal_name, target_amount, current_amount, target_date, created_at) VALUES (?, ?, ?, ?, ?, NOW())";
    private static final String GET_ALL_GOALS = "SELECT * FROM goals";
    private static final String GET_GOAL_BY_ID = "SELECT * FROM goals WHERE goal_id = ?";
    private static final String UPDATE_GOAL = "UPDATE goals SET goal_name = ?, target_amount = ?, current_amount = ?, target_date = ? WHERE goal_id = ?";
    private static final String DELETE_GOAL = "DELETE FROM goals WHERE goal_id = ?";
    private static final String GET_ALL_GOALS_BY_USER_ID = "SELECT * FROM goals WHERE user_id = ?";
    private Connection connection;

    public GoalDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean addGoal(Goal goal) {
        try (PreparedStatement pstmt = connection.prepareStatement(ADD_GOAL)) {
            pstmt.setInt(1, goal.getUserId());
            pstmt.setString(2, goal.getGoalName());
            pstmt.setDouble(3, goal.getTargetAmount());
            pstmt.setDouble(4, goal.getCurrentAmount());
            pstmt.setDate(5, goal.getTargetDate());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Goal> getAllGoals() {
        List<Goal> goals = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(GET_ALL_GOALS)) {
            while (rs.next()) {
                Goal goal = new Goal();
                goal.setGoalId(rs.getInt("goal_id"));
                goal.setUserId(rs.getInt("user_id"));
                goal.setGoalName(rs.getString("goal_name"));
                goal.setTargetAmount(rs.getDouble("target_amount"));
                goal.setCurrentAmount(rs.getDouble("current_amount"));
                goal.setTargetDate(rs.getDate("target_date"));
                goal.setCreatedAt(rs.getDate("created_at"));
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }
    
    public List<Goal> getAllGoalsByUserId(int Id) {
        List<Goal> goals = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(GET_ALL_GOALS_BY_USER_ID)) {
            pstmt.setInt(1, Id); 
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { // Lặp qua tất cả bản ghi
                    Goal goal = new Goal();
                    goal.setGoalId(rs.getInt("goal_id"));
                    goal.setUserId(rs.getInt("user_id"));
                    goal.setGoalName(rs.getString("goal_name"));
                    goal.setTargetAmount(rs.getDouble("target_amount"));
                    goal.setCurrentAmount(rs.getDouble("current_amount"));
                    goal.setTargetDate(rs.getDate("target_date"));
                    goal.setCreatedAt(rs.getDate("created_at"));
                    goals.add(goal); // Thêm vào danh sách
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals; // Trả về danh sách
    }

    public Goal getGoalById(int goalId) {
        try (PreparedStatement pstmt = connection.prepareStatement(GET_GOAL_BY_ID)) {
            pstmt.setInt(1, goalId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Goal goal = new Goal();
                    goal.setGoalId(rs.getInt("goal_id"));
                    goal.setUserId(rs.getInt("user_id"));
                    goal.setGoalName(rs.getString("goal_name"));
                    goal.setTargetAmount(rs.getDouble("target_amount"));
                    goal.setCurrentAmount(rs.getDouble("current_amount"));
                    goal.setTargetDate(rs.getDate("target_date"));
                    goal.setCreatedAt(rs.getDate("created_at"));
                    return goal;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateGoal(Goal goal) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_GOAL)) {
            pstmt.setString(1, goal.getGoalName());
            pstmt.setDouble(2, goal.getTargetAmount());
            pstmt.setDouble(3, goal.getCurrentAmount());
            pstmt.setDate(4, goal.getTargetDate());
            pstmt.setInt(5, goal.getGoalId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteGoal(int goalId) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_GOAL)) {
            pstmt.setInt(1, goalId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
