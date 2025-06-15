package com.picpay.credit.dtos;

import java.sql.Timestamp;

public record TransactionDto(String description,
                             String status,
                             String createdBy,
                             Timestamp createdAt) {
}
