package com.picpay.credit.interfaces;

import com.picpay.credit.dtos.LoginRequestDto;
import com.picpay.credit.dtos.RecoveryJwtTokenDto;

public interface IAuthService {
  RecoveryJwtTokenDto authenticateUser(LoginRequestDto login);
  RecoveryJwtTokenDto refreshTokenUser(RecoveryJwtTokenDto token);
}
