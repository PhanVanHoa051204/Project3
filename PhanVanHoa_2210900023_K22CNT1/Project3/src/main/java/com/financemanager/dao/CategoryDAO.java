package com.financemanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.financemanager.model.Category;

public class CategoryDAO {
	private static final String GET_ALL_CATEGORIES = "SELECT * FROM categories";
    private static final String ADD_CATEGORY = "INSERT INTO categories (category_name, user_id) VALUES (?, ?)";
    private static final String GET_CATEGORY_BY_ID = "SELECT * FROM categories WHERE category_id = ?";
    private static final String UPDATE_CATEGORY = "UPDATE categories SET category_name = ?, user_id = ? WHERE category_id = ?";
    private static final String DELETE_CATEGORY = "DELETE FROM categories WHERE category_id = ?";
    private Connection connection;

    // Constructor khởi tạo kết nối CSDL
    public CategoryDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Nạp MySQL JDBC Driver
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

	// Thêm danh mục mới vào cơ sở dữ liệu

    public boolean addCategory(Category category) {
        String query = "INSERT INTO categories (category_name, user_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, category.getCategoryName());
            pstmt.setInt(2, category.getUserId());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;  // Kiểm tra nếu có dòng được cập nhật
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	
	// Lấy tất cả danh mục từ cơ sở dữ liệu
	public List<Category> getAllCategories() {
	    List<Category> categories = new ArrayList<>();
	    
	    String query = "SELECT * FROM categories";
	    try (Statement stmt = connection.createStatement();
	         ResultSet rs = stmt.executeQuery(query)) {
	        while (rs.next()) {
	            Category category = new Category();
	            category.setCategoryId(rs.getInt("category_id"));
	            category.setCategoryName(rs.getString("category_name"));
	            category.setUserId(rs.getInt("user_id"));
	            categories.add(category);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return categories;
	}
	// Lấy danh sách danh mục theo userId
    public List<Category> getCategoriesByUserId(int userId) {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories WHERE user_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");
                int fetchedUserId = resultSet.getInt("user_id");

                Category category = new Category(categoryId, categoryName, fetchedUserId);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
	
	// Lấy danh mục theo ID
	public Category getCategoryById(int categoryId) {
	    String query = "SELECT * FROM categories WHERE category_id = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, categoryId);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                Category category = new Category();
	                category.setCategoryId(rs.getInt("category_id"));
	                category.setCategoryName(rs.getString("category_name"));
	                category.setUserId(rs.getInt("user_id"));
	                return category;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	// Cập nhật thông tin danh mục
	public boolean updateCategory(Category category) {
	    String query = "UPDATE categories SET category_name = ?, user_id = ? WHERE category_id = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setString(1, category.getCategoryName());
	        pstmt.setInt(2, category.getUserId());
	        pstmt.setInt(3, category.getCategoryId());
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	// Xóa danh mục theo ID
	public boolean deleteCategory(int categoryId) {
	    String query = "DELETE FROM categories WHERE category_id = ?";
	    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
	        pstmt.setInt(1, categoryId);
	        int rowsAffected = pstmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
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
