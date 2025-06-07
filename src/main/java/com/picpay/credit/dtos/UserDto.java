package com.picpay.credit.dtos;

import com.picpay.credit.enums.RoleName;

public record UserDto(String firstName,
                      String lastName,
                      String email,
                      String password,
                      RoleName role) {
}
