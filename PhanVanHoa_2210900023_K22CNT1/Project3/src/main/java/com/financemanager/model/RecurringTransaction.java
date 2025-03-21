package com.financemanager.model;

import java.sql.Date;

public class RecurringTransaction {
	private int recurringTransactionId;
    private int userId;
    private int categoryId;
    private double amount;
    private String description;
    private String frequency;
    private Date startDate;
    private Date endDate;

    public RecurringTransaction() {}

    public RecurringTransaction(int recurringTransactionId, int userId, int categoryId, double amount, String description, String frequency, Date startDate, Date endDate) {
        this.recurringTransactionId = recurringTransactionId;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.description = description;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRecurringTransactionId() {
        return recurringTransactionId;
    }

    public void setRecurringTransactionId(int recurringTransactionId) {
        this.recurringTransactionId = recurringTransactionId;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double bigDecimal) {
        this.amount = bigDecimal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return "RecurringTransaction{" +
                "recurringTransactionId=" + recurringTransactionId +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", frequency='" + frequency + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
