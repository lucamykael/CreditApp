package com.picpay.credit.controllers;

import com.picpay.credit.domain.dtos.LoginRequestDto;
import com.picpay.credit.domain.dtos.RecoveryJwtTokenDto;
import com.picpay.credit.domain.interfaces.IAuthService;
import com.picpay.credit.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtUtil jwtUtil;
  private final IAuthService service;

  @PostMapping("/login")
  public ResponseEntity<RecoveryJwtTokenDto> login(@RequestBody LoginRequestDto request){
    RecoveryJwtTokenDto token = service.authenticateUser(request);
    return new ResponseEntity<>(token, HttpStatus.OK);
  }

  @GetMapping("/hello")
  public ResponseEntity<String> helloWorld(){
    return ResponseEntity.ok("Hello World");
  }

  @GetMapping("/customer")
  public ResponseEntity<String> helloCustomer(){
    return ResponseEntity.ok("Hello Customer");
  }

  @GetMapping("/support")
  public ResponseEntity<String> helloSupport(){
    return ResponseEntity.ok("Hello Support");
  }

  @PostMapping("/refreshToken")
  public ResponseEntity<RecoveryJwtTokenDto> refreshToken(@RequestBody RecoveryJwtTokenDto token){
    return new ResponseEntity<>(service.refreshTokenUser(token), HttpStatus.OK);
  }
}
