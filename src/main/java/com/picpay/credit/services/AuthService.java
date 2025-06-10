package com.picpay.credit.services;

import com.picpay.credit.dtos.LoginRequestDto;
import com.picpay.credit.dtos.RecoveryJwtTokenDto;
import com.picpay.credit.interfaces.IAuthService;
import com.picpay.credit.interfaces.ITokenService;
import com.picpay.credit.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

  private final AuthenticationManager authenticationManager;
  private final ITokenService tokenService;

  public RecoveryJwtTokenDto authenticateUser(LoginRequestDto login) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.email(), login.password());
      Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      return new RecoveryJwtTokenDto(tokenService.generateToken(userDetails));
  }

  public RecoveryJwtTokenDto refreshTokenUser(RecoveryJwtTokenDto token){
    return new RecoveryJwtTokenDto(tokenService.refreshToken(token));
  }
}
