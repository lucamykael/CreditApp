package com.picpay.credit.seeders;

import com.picpay.credit.entities.Role;
import com.picpay.credit.entities.User;
import com.picpay.credit.enums.RoleName;
import com.picpay.credit.repositories.RoleRepository;
import com.picpay.credit.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.picpay.credit.mappers.UserMapper.passwordEncoder;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

  private final UserRepository repository;
  private final RoleRepository roleRepository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    if(repository.count() == 0){

      Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Role ADMIN not found"));

      User admin = new User(
              null,
              "admin",
              "admin",
              passwordEncoder.encode("pwdAdmin"),
              "admin@dev.com",
              List.of(adminRole)
              );

      repository.save(admin);
    }
  }
}
