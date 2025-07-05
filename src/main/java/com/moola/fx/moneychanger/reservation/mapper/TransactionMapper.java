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
    public Transaction toEntity(TransactionDto dto) {
        if (dto == null) return null;

        Transaction entity = new Transaction();

        entity.setId(dto.getId());
        entity.setTransactionDate(dto.getTransactionDate());
        entity.setCustomerId(dto.getCustomerId());
        entity.setMoneyChangerId(dto.getMoneyChangerId());
        entity.setCurrencyId(dto.getCurrencyId());
        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setEmail(dto.getEmail());
        entity.setComments(dto.getComments());
        entity.setExchangeRate(dto.getExchangeRate());
        entity.setForeignAmount(dto.getForeignAmount());
        entity.setSgdAmount(dto.getSgdAmount());
        entity.setReceivedCash(dto.getReceivedCash());
        entity.setCurrentStatus(dto.getCurrentStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());

        return entity;
    }
} 
