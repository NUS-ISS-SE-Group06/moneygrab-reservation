package com.moola.fx.moneychanger.reservation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moola.fx.moneychanger.reservation.dto.ReservationDTO;
import com.moola.fx.moneychanger.reservation.exception.ResourceNotFoundException;
import com.moola.fx.moneychanger.reservation.mapper.ReservationMapper;
import com.moola.fx.moneychanger.reservation.model.Reservation;
import com.moola.fx.moneychanger.reservation.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReservationController.class)
class ReservationControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockitoBean private ReservationService service;
    @MockitoBean private ReservationMapper mapper;

    @Test
    void list_all_returns200() throws Exception {
        Reservation entity = new Reservation();
        entity.setId(1);

        ReservationDTO dto = new ReservationDTO();
        dto.setId(1);

        when(service.listAll()).thenReturn(List.of(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        mockMvc.perform(get("/v1/reservations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void get_byId_returns200() throws Exception {
        Reservation entity = new Reservation();
        entity.setId(5);

        ReservationDTO dto = new ReservationDTO();
        dto.setId(5);

        when(service.get(5)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);

        mockMvc.perform(get("/v1/reservations/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    void get_notFound_returns404() throws Exception {
        when(service.get(99)).thenThrow(new ResourceNotFoundException("Not found"));

        mockMvc.perform(get("/v1/reservations/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_valid_returns200() throws Exception {
        ReservationDTO inputDto = new ReservationDTO();
        inputDto.setCustomerId(1);
        inputDto.setMoneyChangerId(5);
        inputDto.setCurrencyId(3);
        inputDto.setExchangeRate(new BigDecimal("1.2345"));
        inputDto.setForeignAmount(new BigDecimal("100"));
        inputDto.setSgdAmount(new BigDecimal("123.45"));
        inputDto.setStatus("PENDING");
        inputDto.setExpiresAt(new Timestamp(System.currentTimeMillis()));

        Reservation entity = new Reservation();
        Reservation saved = new Reservation();
        saved.setId(10);

        ReservationDTO responseDto = new ReservationDTO();
        responseDto.setId(10);

        when(mapper.toEntity(inputDto)).thenReturn(entity);
        when(service.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(responseDto);

        mockMvc.perform(post("/v1/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10));
    }

    @Test
    void delete_success_returns204() throws Exception {
        doNothing().when(service).delete(77);

        mockMvc.perform(delete("/v1/reservations/77"))
                .andExpect(status().isNoContent());

        verify(service).delete(77);
    }

    @Test
    void update_valid_returns200() throws Exception {
        ReservationDTO inputDto = new ReservationDTO();
        inputDto.setId(10);
        inputDto.setCustomerId(1);
        inputDto.setMoneyChangerId(5);
        inputDto.setCurrencyId(3);
        inputDto.setExchangeRate(new BigDecimal("1.2345"));
        inputDto.setForeignAmount(new BigDecimal("100"));
        inputDto.setSgdAmount(new BigDecimal("123.45"));
        inputDto.setStatus("PENDING");
        inputDto.setExpiresAt(new Timestamp(System.currentTimeMillis()));

        Reservation entity = new Reservation();
        Reservation saved = new Reservation();
        saved.setId(10);

        ReservationDTO responseDto = new ReservationDTO();
        responseDto.setId(10);

        when(mapper.toEntity(inputDto)).thenReturn(entity);
        when(service.save(entity)).thenReturn(saved);
        when(mapper.toDTO(saved)).thenReturn(responseDto);

        mockMvc.perform(put("/v1/reservations/10")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10));
    }
}