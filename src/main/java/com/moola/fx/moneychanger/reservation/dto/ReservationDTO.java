package com.moola.fx.moneychanger.reservation.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class ReservationDTO {
    private Integer id;
    private Integer customerId;
    private Integer moneyChangerId;
    private Integer currencyId;
    private BigDecimal exchangeRate;
    private BigDecimal foreignAmount;
    private BigDecimal sgdAmount;
    private String status;
    private Timestamp expiresAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Integer createdBy;
    private Integer updatedBy;
}