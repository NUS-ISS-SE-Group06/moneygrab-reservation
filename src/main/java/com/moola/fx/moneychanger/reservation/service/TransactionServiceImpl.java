package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository; // <-- choose one name and keep it

    // Spring injects the repository via constructor
    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransactionDto> listAll() {
        return repository.findAll().stream()
                .map(TransactionMapper::toDto)
                .toList();
    }

    @Override
    public List<TransactionDto> listByMoneyChanger(int moneyChangerId) {
        return repository.findByMoneyChangerId(moneyChangerId).stream()
                .map(TransactionMapper::toDto)
                .toList();
    }

}
