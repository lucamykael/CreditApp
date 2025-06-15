package com.picpay.credit.interfaces;

import com.picpay.credit.dtos.TransactionDto;

public interface ITransactionService {
  Void createTransaction(TransactionDto dto);
}
