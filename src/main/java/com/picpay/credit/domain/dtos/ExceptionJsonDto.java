package com.picpay.credit.domain.dtos;

import java.time.LocalDateTime;

public record ExceptionJsonDto(LocalDateTime timestamp,
                               int status,
                               String error,
                               String message,
                               String path) {
}
