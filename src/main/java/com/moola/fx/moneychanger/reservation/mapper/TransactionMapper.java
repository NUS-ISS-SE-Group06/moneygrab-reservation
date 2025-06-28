package com.moola.fx.moneychanger.reservation.mapper;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.model.Transaction;

public class TransactionMapper {

    public static TransactionDto toDto(Transaction entity) {
        TransactionDto dto = new TransactionDto();
        dto.setId(entity.getId());
        dto.setTransactionDate(entity.getTransactionDate());
        dto.setCustomerId(entity.getCustomerId());
        dto.setMoneyChangerId(entity.getMoneyChangerId());
        dto.setCurrencyId(entity.getCurrencyId());
        dto.setCurrentStatus(entity.getCurrentStatus());
        dto.setEmail(entity.getEmail());
        dto.setComments(entity.getComments());
        dto.setExchangeRate(entity.getExchangeRate());
        dto.setForeignAmount(entity.getForeignAmount());
        dto.setSgdAmount(entity.getSgdAmount());
        dto.setReceivedCash(entity.getReceivedCash());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        return dto;
    }
} 
