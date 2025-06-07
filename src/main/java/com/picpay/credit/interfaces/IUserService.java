package com.picpay.credit.interfaces;

import com.picpay.credit.dtos.UserDto;
import com.picpay.credit.entities.User;
import org.springframework.data.domain.Page;

public interface IUserService {
  void createUser(UserDto dto);
  String deleteUser(Long id);
  Page<User> getUsers();
  User updatePassword(Long id);
}
