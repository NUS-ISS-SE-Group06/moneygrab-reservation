package com.moola.fx.moneychanger.reservation.controller;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;


@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService service;

    private TransactionDto mockDto(int id) {
        TransactionDto dto = new TransactionDto();
        dto.setId(id);
        dto.setMoneyChangerId(100);
        dto.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        dto.setCurrentStatus("PENDING"); // added field for test validation
        return dto;
    }

    /* ------------------------------------------------------------------
     * Tests for GET endpoints
     * ------------------------------------------------------------------ */

    @Test
    @DisplayName("GET /v1/transactions returns list of all transactions")
    void testListAllTransactions() throws Exception {
        List<TransactionDto> mockList = List.of(mockDto(1), mockDto(2));
        when(service.listAll()).thenReturn(mockList);

        mockMvc.perform(get("/v1/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("GET /v1/moneychanger/{id}/transactions returns transactions for a money changer")
    void testListByMoneyChanger() throws Exception {
        int moneyChangerId = 5;
        List<TransactionDto> mockList = List.of(mockDto(10));
        when(service.listByMoneyChanger(moneyChangerId)).thenReturn(mockList);

        mockMvc.perform(get("/v1/moneychanger/{moneyChangerId}/transactions", moneyChangerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    /* ------------------------------------------------------------------
     * Tests for PATCH /status update
     * ------------------------------------------------------------------ */

 @Test
@DisplayName("PATCH /v1/transactions/{id}/status updates status")
void testUpdateTransactionStatus() throws Exception {
    int id = 1;
    String newStatus = "COMPLETED";
    int userId = 9;
    String comments = "Customer did not show up.";

    TransactionDto updated = mockDto(id);
    updated.setCurrentStatus(newStatus);

    // JSON request body
    String requestBody = """
        {
          "status": "%s",
          "comment": "%s",
          "userId": %d
        }
        """.formatted(newStatus, comments, userId);

    when(service.updateTransactionStatus(id, newStatus, comments, userId))
            .thenReturn(updated);

    mockMvc.perform(patch("/v1/transactions/{id}/status", id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.currentStatus").value(newStatus));
}

}
