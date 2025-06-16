package com.picpay.credit.domain.interfaces;

import com.picpay.credit.domain.dtos.LoginRequestDto;
import com.picpay.credit.domain.dtos.RecoveryJwtTokenDto;

public interface IAuthService {
  RecoveryJwtTokenDto authenticateUser(LoginRequestDto login);
  RecoveryJwtTokenDto refreshTokenUser(RecoveryJwtTokenDto token);
}
