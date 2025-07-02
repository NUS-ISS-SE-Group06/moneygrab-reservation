package com.moola.fx.moneychanger.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    // Either annotate…
    @Autowired                      // optional if this is the only ctor
    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<TransactionDto> listByMoneyChanger(int moneyChangerId) {
        return repository.findByMoneyChangerId(moneyChangerId)
                         .stream()
                         .map(TransactionMapper::toDto)
                         .toList();
    }
}
