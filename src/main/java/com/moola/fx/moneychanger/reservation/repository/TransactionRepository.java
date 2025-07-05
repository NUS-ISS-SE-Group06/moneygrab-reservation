package com.moola.fx.moneychanger.reservation.repository;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;
import com.moola.fx.moneychanger.reservation.model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // You can define custom queries here if needed, e.g.:
     List<Transaction> findByMoneyChangerId(int moneyChangerId);
   
}
