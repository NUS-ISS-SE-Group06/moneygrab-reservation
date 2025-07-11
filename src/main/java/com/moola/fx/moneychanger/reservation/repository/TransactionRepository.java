package com.moola.fx.moneychanger.reservation.repository;

import com.moola.fx.moneychanger.reservation.model.Transaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // You can define custom queries here if needed, e.g.:
     List<Transaction> findByMoneyChangerId(int moneyChangerId);
   @Modifying
    @Transactional
    @Query("""
           update Transaction t
              set t.currentStatus = :status,
                  t.updatedBy     = :userId
            where t.id           = :id
           """)
    int updateStatus(int id, String status, int userId);
}
