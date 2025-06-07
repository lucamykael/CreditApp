package com.picpay.credit.dtos;

import com.picpay.credit.entities.Role;

import java.util.List;

public record RecoveryUserDto(Long id,
                              String email,
                              List<Role> roles) {
}
