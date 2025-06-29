package com.moola.fx.moneychanger.reservation.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.Data;

@Data
@Entity
@Table(name = "`transaction`")   
public class Transaction {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                    

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "money_changer_id")
    private Integer moneyChangerId;

    @Column(name = "currency_id")          
    private Integer currencyId;



    @Column(name = "transaction_date")
    private Timestamp transactionDate;

    @Column(name = "current_status")
    private String currentStatus;

    private String email;
    private String comments;

  
    @Column(name = "exchange_rate", precision = 18, scale = 8)
    private BigDecimal exchangeRate;

    @Column(name = "foreign_amount", precision = 18, scale = 2)
    private BigDecimal foreignAmount;

    @Column(name = "sgd_amount", precision = 18, scale = 2)
    private BigDecimal sgdAmount;

    @Column(name = "received_cash", precision = 18, scale = 2)
    private BigDecimal receivedCash;

   

    @Column(name = "created_at",nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;
   

    @Column(name = "updated_at", nullable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "updated_by")
    private Integer updatedBy;

   

      @PrePersist
    protected void onCreate() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        this.createdAt = now;
        this.updatedAt = now;
        
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
