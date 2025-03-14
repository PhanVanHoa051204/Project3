package com.financemanager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.financemanager.dao.CategoryDAO;
import com.financemanager.model.Category;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;

	public void init() {
		try {
			categoryDAO = new CategoryDAO();
		} catch (Exception e) {
			throw new RuntimeException("Error initializing CategoryDAO", e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		int cateId = Integer.parseInt(request.getParameter("category_id"));
		if (action.equals("delete")) {
			boolean isDelete = categoryDAO.deleteCategory(cateId);
			if (isDelete) {
				response.sendRedirect("/Project3/ChiTieu/categories.jsp");
			} else {

				response.getWriter().println("Error while delete category.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String action = request.getParameter("action");
	    if (action == null) {
	        response.sendRedirect("/Project3/ChiTieu/error.jsp");
	        return;
	    }

	    String categoryName = request.getParameter("category_name");
	    String userIdStr = request.getParameter("user_id");
	    String cateIdStr = request.getParameter("category_id");

	    // Kiểm tra và parse userId, cateId
	    int userId = 0;
	    int cateId = 0;
	    try {
	        userId = Integer.parseInt(userIdStr != null ? userIdStr : "0");
	        cateId = Integer.parseInt(cateIdStr != null ? cateIdStr : "0");
	    } catch (NumberFormatException e) {
	        response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        return;
	    }

	    // Kiểm tra categoryName
	    if (categoryName == null || categoryName.trim().isEmpty()) {
	        response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        return;
	    }

	    Category category = new Category(cateId, categoryName, userId);
	    if (action.equals("add")) {
	        boolean isAdded = categoryDAO.addCategory(category);
	        if (isAdded) {
	            response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        } else {
	            response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        }
	    } else if (action.equals("update")) {
	        boolean isUpdate = categoryDAO.updateCategory(category);
	        if (isUpdate) {
	            response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        } else {
	            response.sendRedirect("/Project3/ChiTieu/categories.jsp");
	        }
	    }
	}
}
