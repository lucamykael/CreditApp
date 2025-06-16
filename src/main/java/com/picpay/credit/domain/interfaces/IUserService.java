package com.picpay.credit.domain.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.credit.domain.dtos.UserDto;
import com.picpay.credit.domain.entities.User;
import org.springframework.data.domain.Page;

public interface IUserService {
  void createUser(UserDto dto) throws JsonProcessingException;
  String deleteUser(Long id);
  Page<User> getUsers();
  User updatePassword(Long id);
}
