package com.picpay.credit.utils;

import com.picpay.credit.domain.dtos.ExceptionJsonDto;
import com.picpay.credit.domain.exceptions.NotFoundException;
import com.picpay.credit.domain.exceptions.UniqueConstraintViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionsHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionJsonDto> handleNotFoundException(NotFoundException ex, HttpServletRequest request){
    ExceptionJsonDto exceptionFormat = new ExceptionJsonDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
    return new ResponseEntity<>(exceptionFormat, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UniqueConstraintViolationException.class)
  public ResponseEntity<ExceptionJsonDto> handleUniqueConstraintViolationException(UniqueConstraintViolationException ex, HttpServletRequest request){
    ExceptionJsonDto exceptionFormat = new ExceptionJsonDto(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );
    return new ResponseEntity<>(exceptionFormat, HttpStatus.CONFLICT);
  }
}
