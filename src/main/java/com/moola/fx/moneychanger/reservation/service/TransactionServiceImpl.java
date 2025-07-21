package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.mapper.TransactionMapper;
import com.moola.fx.moneychanger.reservation.model.Transaction;
import com.moola.fx.moneychanger.reservation.repository.TransactionRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Override
    @Transactional   // only the 2nd call is read-only
    public TransactionDto updateTransactionStatus(int id, String status,String comments, int userId) {

        int rows = repository.updateStatus(id, status, comments, userId);
        if (rows == 0) {
            throw new EntityNotFoundException("Transaction " + id);
        }

        // Fetch the (now-updated) entity so we can return a DTO
        return TransactionMapper.toDto(repository.getReferenceById(id));
    }

  @Override
  @Transactional
  public TransactionDto createTransaction(TransactionDto transactionDto) {
    Transaction tx = new Transaction();

        // Map fields from DTO
        tx.setCustomerId(transactionDto.getCustomerId());
        tx.setMoneyChangerId(transactionDto.getMoneyChangerId());
        tx.setCurrencyId(transactionDto.getCurrencyId());
        tx.setTransactionDate(transactionDto.getTransactionDate());
        tx.setCurrentStatus(transactionDto.getCurrentStatus());
        tx.setEmail(transactionDto.getEmail());
        tx.setComments(transactionDto.getComments());
        tx.setExchangeRate(transactionDto.getExchangeRate());
        tx.setForeignAmount(transactionDto.getForeignAmount());
        tx.setSgdAmount(transactionDto.getSgdAmount());
        tx.setReceivedCash(transactionDto.getReceivedCash());
        tx.setCreatedBy(transactionDto.getCreatedBy());
        tx.setUpdatedBy(transactionDto.getUpdatedBy());

        // Save to DB
        Transaction saved = repository.save(tx);

        // Map back to DTO
        TransactionDto result = new TransactionDto();
        result.setId(saved.getId());
        result.setCustomerId(saved.getCustomerId());
        result.setMoneyChangerId(saved.getMoneyChangerId());
        result.setCurrencyId(saved.getCurrencyId());
        result.setTransactionDate(saved.getTransactionDate());
        result.setCurrentStatus(saved.getCurrentStatus());
        result.setEmail(saved.getEmail());
        result.setComments(saved.getComments());
        result.setExchangeRate(saved.getExchangeRate());
        result.setForeignAmount(saved.getForeignAmount());
        result.setSgdAmount(saved.getSgdAmount());
        result.setReceivedCash(saved.getReceivedCash());
        result.setCreatedAt(saved.getCreatedAt());
        result.setUpdatedAt(saved.getUpdatedAt());
        result.setCreatedBy(saved.getCreatedBy());
        result.setUpdatedBy(saved.getUpdatedBy());

        // Optional: Fetch customerName if needed
        // customerRepository.findById(saved.getCustomerId()).ifPresent(c -> result.setCustomerName(c.getName()));

        return result;

  }


}
