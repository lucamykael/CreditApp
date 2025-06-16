package com.picpay.credit.infrastructure.mappers;

import com.picpay.credit.domain.dtos.UserDto;
import com.picpay.credit.domain.entities.Role;
import com.picpay.credit.domain.entities.User;
import com.picpay.credit.domain.enums.RoleName;
import com.picpay.credit.domain.models.UserCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "firstName", source = "dto.firstName")
  @Mapping(target = "lastName", source = "dto.lastName")
  @Mapping(target = "email", source = "dto.email")
  @Mapping(target = "password", expression = "java(passwordEncoder.encode(dto.password()))")
  @Mapping(target = "roles", expression = "java(java.util.List.of(role))")
  User toEntity(UserDto dto, Role role);

  @Mapping(target = "id", source = "user.id")
  @Mapping(target = "firstName", source = "user.firstName")
  @Mapping(target = "lastName", source = "user.lastName")
  @Mapping(target = "email", source = "user.email")
  UserCreatedEvent toProducer(User user);

  default List<Role> map(RoleName roleName){
    if(roleName == null) return null;
    Role role = new Role();
    role.setName(roleName);
    return List.of(role);
  }
}
