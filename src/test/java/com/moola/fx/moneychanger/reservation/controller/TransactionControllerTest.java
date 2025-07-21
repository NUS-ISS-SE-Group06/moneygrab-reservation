package com.moola.fx.moneychanger.reservation.controller;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.service.TransactionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TransactionService service;

    private TransactionDto mockDto(int id) {
        TransactionDto dto = new TransactionDto();
        dto.setId(id);
        dto.setMoneyChangerId(101);
        dto.setCustomerId(202);
        dto.setCustomerName("John Doe");
        dto.setTransactionDate(new Timestamp(System.currentTimeMillis()));
        dto.setCurrentStatus("PENDING");
        dto.setCurrency("USD");
        dto.setCurrencyId(1);
        dto.setEmail("test@example.com");
        dto.setComments("Initial Comment");
        dto.setExchangeRate(BigDecimal.valueOf(1.34));
        dto.setForeignAmount(BigDecimal.valueOf(100));
        dto.setSgdAmount(BigDecimal.valueOf(134));
        dto.setReceivedCash(BigDecimal.ZERO);
        dto.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        dto.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        dto.setCreatedBy(9);
        dto.setUpdatedBy(9);
        return dto;
    }

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
    @DisplayName("GET /v1/transactions/{id} returns transactions for a money changer")
    void testListByMoneyChanger() throws Exception {
        int moneyChangerId = 5;
        List<TransactionDto> mockList = List.of(mockDto(10));
        when(service.listByMoneyChanger(moneyChangerId)).thenReturn(mockList);

        mockMvc.perform(get("/v1/transactions/{moneyChangerId}", moneyChangerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("PATCH /transactions/{id}/status updates status")
    void testUpdateTransactionStatus() throws Exception {
        int id = 1;
        String newStatus = "COMPLETED";
        int userId = 9;
        String comments = "Customer did not show up";

        // Setup mock return DTO
        TransactionDto updated = mockDto(id);
        updated.setCurrentStatus(newStatus);
        updated.setComments(comments);
        assertNotNull(updated.getId()); 

        String requestBody = """
            {
              "status": "%s",
              "comment": "%s",
              "userId": %d
            }
            """.formatted(newStatus, comments, userId);

        when(service.updateTransactionStatus(id, newStatus, comments, userId)).thenReturn(updated);

        mockMvc.perform(patch("/v1/transactions/{id}/status", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andDo(result -> System.out.println("Response JSON: " + result.getResponse().getContentAsString()))
                .andExpect(status().isOk());
    }
}
