package com.moola.fx.moneychanger.reservation.service;

import com.moola.fx.moneychanger.reservation.dto.TransactionDto;


import java.util.List;

import org.springframework.stereotype.Service;

@Service
public  interface TransactionService{
    List <TransactionDto> listAll();
    List <TransactionDto> listByMoneyChanger(int moneyChangerId);
    TransactionDto updateTransactionStatus(int id, String status, String comments, int userid);

}

