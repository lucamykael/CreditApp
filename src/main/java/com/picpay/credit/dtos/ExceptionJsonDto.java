package com.picpay.credit.dtos;

import java.time.LocalDateTime;

public record ExceptionJsonDto(LocalDateTime timestamp,
                               int status,
                               String error,
                               String message,
                               String path) {
}
