package com.moola.fx.moneychanger.reservation.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class TransactionDto {

    private Integer id;
    private Timestamp transactionDate;
    private Integer customerId;
    private Integer moneyChangerId;
    private Integer currencyId;
    private String currentStatus;
    private String email;
    private String comments;
    private BigDecimal exchangeRate;
    private BigDecimal foreignAmount;
    private BigDecimal sgdAmount;
    private BigDecimal receivedCash;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }

    public Integer getMoneyChangerId() { return moneyChangerId; }
    public void setMoneyChangerId(Integer moneyChangerId) { this.moneyChangerId = moneyChangerId; }

    public Integer getCurrencyId() { return currencyId; }
    public void setCurrencyId(Integer currencyId) { this.currencyId = currencyId; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public BigDecimal getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(BigDecimal exchangeRate) { this.exchangeRate = exchangeRate; }

    public BigDecimal getForeignAmount() { return foreignAmount; }
    public void setForeignAmount(BigDecimal foreignAmount) { this.foreignAmount = foreignAmount; }

    public BigDecimal getSgdAmount() { return sgdAmount; }
    public void setSgdAmount(BigDecimal sgdAmount) { this.sgdAmount = sgdAmount; }

    public BigDecimal getReceivedCash() { return receivedCash; }
    public void setReceivedCash(BigDecimal receivedCash) { this.receivedCash = receivedCash; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public Integer getCreatedBy() { return createdBy; }
    public void setCreatedBy(Integer createdBy) { this.createdBy = createdBy; }

    public Integer getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(Integer updatedBy) { this.updatedBy = updatedBy; }
}
