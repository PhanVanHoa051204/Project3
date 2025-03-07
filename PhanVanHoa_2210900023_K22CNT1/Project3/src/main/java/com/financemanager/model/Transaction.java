package com.financemanager.model;

import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
	private int transactionId;
    private int userId;
    private int categoryId;
    private double amount;  // Sử dụng double thay vì BigDecimal
    private String description;
    private Date transactionDate;
    private Date createdAt;

    // Constructor mặc định
    public Transaction() {}

    // Constructor có tham số
    public Transaction(int transactionId, int userId, int categoryId, double amount, String description, Date transactionDate, Date createdAt) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.createdAt = createdAt;
    }

    // Getter và Setter
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getAmount() {
        return amount;  // Trả về giá trị double
    }

    public void setAmount(double amount) {
        this.amount = amount;  // Chỉnh sửa để nhận double
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactionDate() {
        return transactionDate.toLocalDate();
    }

    public void setTransactionDate(LocalDate transactionDate) {
        // Chuyển LocalDate thành java.sql.Date
        this.transactionDate = Date.valueOf(transactionDate);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", transactionDate=" + transactionDate +
                ", createdAt=" + createdAt +
                '}';
    }
}
