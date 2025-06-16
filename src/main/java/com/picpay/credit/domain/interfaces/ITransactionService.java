package com.picpay.credit.domain.interfaces;

import com.picpay.credit.domain.dtos.TransactionDto;

public interface ITransactionService {
  Void createTransaction(TransactionDto dto);
}
