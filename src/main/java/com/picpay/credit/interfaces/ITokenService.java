package com.picpay.credit.interfaces;

import com.picpay.credit.security.UserDetailsImpl;

public interface ITokenService {
  String generateToken(UserDetailsImpl user);
  String getSubjectFromToken(String token);
}
