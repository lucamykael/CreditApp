package com.picpay.credit.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.credit.dtos.UserDto;
import com.picpay.credit.entities.User;
import com.picpay.credit.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final IUserService service;

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody UserDto dto) throws JsonProcessingException {
    service.createUser(dto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
    return ok(service.deleteUser(id));
  }

  @GetMapping
  public ResponseEntity<Page<User>> get(){
    return ok().body(service.getUsers());
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> put(@PathVariable(name = "id") Long id){
    return ok().body(service.updatePassword(id));
  }
}
