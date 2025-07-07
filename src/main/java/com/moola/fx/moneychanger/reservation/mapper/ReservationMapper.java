package com.moola.fx.moneychanger.reservation.mapper;

import com.moola.fx.moneychanger.reservation.dto.ReservationDTO;
import com.moola.fx.moneychanger.reservation.model.Reservation;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {

    public ReservationDTO toDTO(Reservation entity) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(entity.getId());
        dto.setCustomerId(entity.getCustomerId());
        dto.setMoneyChangerId(entity.getMoneyChangerId());
        dto.setCurrencyId(entity.getCurrencyId());
        dto.setExchangeRate(entity.getExchangeRate());
        dto.setForeignAmount(entity.getForeignAmount());
        dto.setSgdAmount(entity.getSgdAmount());
        dto.setStatus(entity.getStatus());
        dto.setExpiresAt(entity.getExpiresAt());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setUpdatedBy(entity.getUpdatedBy());
        return dto;
    }

    public Reservation toEntity(ReservationDTO dto) {
        Reservation entity = new Reservation();
        entity.setId(dto.getId());
        entity.setCustomerId(dto.getCustomerId());
        entity.setMoneyChangerId(dto.getMoneyChangerId());
        entity.setCurrencyId(dto.getCurrencyId());
        entity.setExchangeRate(dto.getExchangeRate());
        entity.setForeignAmount(dto.getForeignAmount());
        entity.setSgdAmount(dto.getSgdAmount());
        entity.setStatus(dto.getStatus());
        entity.setExpiresAt(dto.getExpiresAt());
        entity.setCreatedBy(dto.getCreatedBy());
        entity.setUpdatedBy(dto.getUpdatedBy());
        return entity;
    }
}