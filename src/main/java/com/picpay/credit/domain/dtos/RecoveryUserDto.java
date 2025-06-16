package com.picpay.credit.domain.dtos;

import com.picpay.credit.domain.entities.Role;

import java.util.List;

public record RecoveryUserDto(Long id,
                              String email,
                              List<Role> roles) {
}
