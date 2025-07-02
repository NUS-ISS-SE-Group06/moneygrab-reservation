package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.model.Transaction;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    // public List<Transaction> getAllTransactions() {
    //     return repository.findAll();
    // }
     /** Find by money-changer and map entities → DTOs */
    public List<TransactionDto> listByMoneyChanger(int moneyChangerId) {
        return repository.findByMoneyChangerId(moneyChangerId)          // List<Transaction>
                         .stream()
                         .map(TransactionMapper::toDto)                // → TransactionDto
                         .collect(Collectors.toList());
    }
}
