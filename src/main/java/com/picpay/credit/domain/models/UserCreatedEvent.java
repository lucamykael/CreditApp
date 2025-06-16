package com.picpay.credit.domain.models;

import lombok.Data;

@Data
public class UserCreatedEvent {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
}
