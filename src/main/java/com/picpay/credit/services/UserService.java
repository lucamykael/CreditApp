package com.picpay.credit.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.picpay.credit.dtos.LoginRequestDto;
import com.picpay.credit.dtos.RecoveryJwtTokenDto;
import com.picpay.credit.dtos.UserDto;
import com.picpay.credit.entities.Role;
import com.picpay.credit.entities.User;
import com.picpay.credit.exceptions.NotFoundException;
import com.picpay.credit.exceptions.UniqueConstraintViolationException;
import com.picpay.credit.interfaces.IUserService;
import com.picpay.credit.mappers.UserMapper;
import com.picpay.credit.models.UserCreatedEvent;
import com.picpay.credit.producers.UserProducer;
import com.picpay.credit.repositories.RoleRepository;
import com.picpay.credit.repositories.UserRepository;
import com.picpay.credit.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final UserMapper mapper;
  private final UserRepository repository;
  private final RoleRepository roleRepository;
  private final UserProducer userProducer;

  public void createUser(UserDto dto) throws JsonProcessingException {
    Role role = roleRepository.findByName(dto.role()).orElseThrow(() -> new NotFoundException("Role not exists"));
    User user = mapper.toEntity(dto, role);
    try {
      repository.save(user);
    } catch(DataIntegrityViolationException ex){
      throw new UniqueConstraintViolationException("Email is duplicated. Please, try another email");
    }

    UserCreatedEvent event = mapper.toProducer(user);
    userProducer.sendUserCreatedEvent(event);
  }

  public String deleteUser(Long id) {
    User user = getUserById(id).orElseThrow(() -> new NotFoundException("User not found"));
    repository.delete(user);
    return "User deleted successfully";
  }

  public Page<User> getUsers() {
    List<User> users = repository.findAll();
    return new PageImpl<>(users, PageRequest.of(0, 20, Sort.unsorted()), users.size());
  }
  public User updatePassword(Long id) {
    User user = getUserById(id).orElseThrow(() -> new NotFoundException("User not found"));

    return user;
  }

  private Optional<User> getUserById(Long id){
    return repository.findUserById(id);
  }
}