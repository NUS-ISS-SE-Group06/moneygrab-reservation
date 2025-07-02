package com.moola.fx.moneychanger.reservation.controller;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.model.Transaction;
import com.moola.fx.moneychanger.reservation.service.TransactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/transaction")
public class TransactionController {

    private final TransactionService service;
    
  
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    /**
     * GET /api/transaction
     * Returns all transactions as JSON.
     */
    @GetMapping
    public ResponseEntity<List<TransactionDto>> getByMoneyChanger(@RequestParam int moneyChangerId) {

        List<TransactionDto> list = service.listByMoneyChanger(moneyChangerId);

        if (list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(list);
    }
}
