package com.picpay.credit.infrastructure.specifications;

import com.picpay.credit.domain.entities.User;
import jakarta.persistence.criteria.Root;
import org.hibernate.mapping.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserSpecification {

  public Specification<User> getSpecification(){
    return (root, query, cb) -> {

      List<Join> joins = getJoins(root);
      List<Predicate> predicates = getPredicates();
      return null;
    };
  }

  private List<Join> getJoins(Root root){
    return new ArrayList<>();
  }

  private List<Predicate> getPredicates(){
    return new ArrayList<>();
  }
}
