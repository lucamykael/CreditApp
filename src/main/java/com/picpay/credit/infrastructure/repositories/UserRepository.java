package com.picpay.credit.infrastructure.repositories;

import com.picpay.credit.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  User getUserById(Long id);
  Optional<User> findUserById(Long id);
}
