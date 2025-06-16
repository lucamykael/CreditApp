package com.picpay.credit.security;

import com.picpay.credit.domain.entities.User;
import com.picpay.credit.infrastructure.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository repository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
    User user = repository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    return new UserDetailsImpl(user);
  }
}
