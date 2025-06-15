package com.picpay.credit.models;

import com.picpay.credit.enums.RoleName;
import lombok.Data;

@Data
public class UserCreatedEvent {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
