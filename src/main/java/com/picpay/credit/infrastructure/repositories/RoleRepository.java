package com.picpay.credit.infrastructure.repositories;

import com.picpay.credit.domain.entities.Role;
import com.picpay.credit.domain.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(RoleName roleName);
}
