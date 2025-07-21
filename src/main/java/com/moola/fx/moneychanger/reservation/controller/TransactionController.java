package com.moola.fx.moneychanger.reservation.controller;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.dto.TransactionStatusUpdateRequestDto;
import com.moola.fx.moneychanger.reservation.service.TransactionService;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class TransactionController {

    private final TransactionService service;
    
  
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    /**
     * GET /moneychanger/{moneyChangerId}/transactions
     * Returns all transactions as JSON for the moneychanger.
     */
    @GetMapping("/moneychanger/{moneyChangerId}/transactions")
  public ResponseEntity<List<TransactionDto>> listByMoneyChanger(
            @PathVariable @Positive int moneyChangerId) {

        List<TransactionDto> tx = service.listByMoneyChanger(moneyChangerId);
        return ResponseEntity.ok(tx);           // 200 even if empty []
    }
    /**
     * GET /transactions
     * Returns all transactions as JSON.
     */
    @GetMapping("/transactions")
    public ResponseEntity<List<TransactionDto>> listAllTransactions() {
        List<TransactionDto> all = service.listAll();
        return ResponseEntity.ok(all);
    }
    /**
     * PATCH /v1/transactions/{id}/status
     * Body: { "status": "COMPLETED" }
     * Header X-User-Id: caller’s userId (for audit)
     */
@PatchMapping("/transactions/{id}/status")
public ResponseEntity<TransactionDto> updateTransactionStatus(
        @PathVariable int id,
        @RequestBody TransactionStatusUpdateRequestDto request) {

    if (request.getStatus() == null || request.getStatus().isBlank() || request.getUserId() <= 0) {
        return ResponseEntity.badRequest().build();
    }

    TransactionDto dto = service.updateTransactionStatus(
        id,
        request.getStatus(),
        request.getComments(),
        request.getUserId()
    );

    return ResponseEntity.ok(dto);
}


}
