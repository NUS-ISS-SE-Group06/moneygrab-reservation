package com.moola.fx.moneychanger.reservation.mapper;

import com.moola.fx.moneychanger.reservation.dto.ReservationDTO;
import com.moola.fx.moneychanger.reservation.model.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

class ReservationMapperTest {

    @Test
    void convertReservationDTOToEntity() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(1);
        reservationDTO.setCustomerId(1);
        reservationDTO.setMoneyChangerId(5);
        reservationDTO.setCurrencyId(3);
        reservationDTO.setExchangeRate(new BigDecimal("1.2345"));
        reservationDTO.setForeignAmount(new BigDecimal("100"));
        reservationDTO.setSgdAmount(new BigDecimal("123.45"));
        reservationDTO.setStatus("PENDING");
        reservationDTO.setExpiresAt(new Timestamp(System.currentTimeMillis()));

        ReservationMapper reservationMapper = new ReservationMapper();
        reservationMapper.toEntity(reservationDTO);
    }

    @Test
    void convertReservationEntityToDTO() throws Exception {
        Reservation reservationEntity = new Reservation();
        reservationEntity.setId(1);
        reservationEntity.setCustomerId(1);
        reservationEntity.setMoneyChangerId(5);
        reservationEntity.setCurrencyId(3);
        reservationEntity.setExchangeRate(new BigDecimal("1.2345"));
        reservationEntity.setForeignAmount(new BigDecimal("100"));
        reservationEntity.setSgdAmount(new BigDecimal("123.45"));
        reservationEntity.setStatus("PENDING");
        reservationEntity.setExpiresAt(new Timestamp(System.currentTimeMillis()));

        ReservationMapper reservationMapper = new ReservationMapper();
        reservationMapper.toDTO(reservationEntity);
    }
}
