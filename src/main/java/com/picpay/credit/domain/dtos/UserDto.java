package com.picpay.credit.domain.dtos;

import com.picpay.credit.domain.enums.RoleName;

public record UserDto(String firstName,
                      String lastName,
                      String email,
                      String password,
                      RoleName role) {
}
