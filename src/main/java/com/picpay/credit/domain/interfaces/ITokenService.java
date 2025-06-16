package com.picpay.credit.domain.interfaces;

import com.picpay.credit.domain.dtos.RecoveryJwtTokenDto;
import com.picpay.credit.security.UserDetailsImpl;

public interface ITokenService {
  String generateToken(UserDetailsImpl user);
  String getSubjectFromToken(String token);
  String refreshToken(RecoveryJwtTokenDto token);
}
