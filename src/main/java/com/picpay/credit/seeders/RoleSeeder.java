package com.picpay.credit.seeders;

import com.picpay.credit.entities.Role;
import com.picpay.credit.enums.RoleName;
import com.picpay.credit.repositories.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleSeeder implements CommandLineRunner {

  private final RoleRepository repository;

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    if(repository.count() == 0){
      Role admin = new Role(null, RoleName.ROLE_ADMIN);
      Role customer = new Role(null, RoleName.ROLE_CUSTOMER);
      Role support = new Role(null, RoleName.ROLE_SUPPORT);

      repository.save(admin);
      repository.save(customer);
      repository.save(support);

      System.out.println("Roles inserted in database");
    } else {
      System.out.println("Roles is exists, not inserted");
    }
  }
}
