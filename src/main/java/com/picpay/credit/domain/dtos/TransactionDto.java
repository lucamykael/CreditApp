package com.picpay.credit.domain.dtos;

import java.sql.Timestamp;

public record TransactionDto(String description,
                             String status,
                             String createdBy,
                             Timestamp createdAt) {
}
