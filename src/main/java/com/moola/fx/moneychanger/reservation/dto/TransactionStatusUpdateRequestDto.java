package com.moola.fx.moneychanger.reservation.dto;
import lombok.Data;

@Data
public class TransactionStatusUpdateRequestDto {
    private String status;
    private String comments;
    private int userId;

}
