package com.picpay.credit.entities;

import com.picpay.credit.domain.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTests {

  @Test
  @DisplayName("User ID create is success")
  public void userIdGeneratedIsSuccess(){
    long id = 1L;

    User user = new User();
    user.setId(1L);

    Assertions.assertEquals(id, user.getId());
  }

  @Test
  @DisplayName("User first name create is success")
  public void userFirstNameCreateIsSuccess(){
    String name = "Lucas";

    User user = new User();
    user.setFirstName("Lucas");

    Assertions.assertEquals(name, user.getFirstName());
  }

  @Test
  @DisplayName("User last name create is success")
  public void userLastNameCreateIsSuccess(){
    String name = "Moura";

    User user = new User();
    user.setLastName("Moura");

    Assertions.assertEquals(name, user.getLastName());
  }

  @Test
  @DisplayName("User Id create is failed")
  public void userIdCreateIsFailed(){
    User user = new User();
    user.setId(-1L);
    Assertions.assertFalse(user.getId() > 1);
  }

  @Test
  @DisplayName("User first name create is failed")
  public void userFirstNameCreateIsFailed(){
    User user = new User();
    user.setFirstName(null);
    Assertions.assertNotEquals("", user.getFirstName());
  }
}
