package com.moola.fx.moneychanger.reservation.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionDto {

    private Integer id;
    private Timestamp transactionDate;
    private Integer customerId;
    private String customerName; 
    private Integer moneyChangerId;
    private String moneyChanger;
    private Integer currencyId;
    private String currency;
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
}
