package com.picpay.credit.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.picpay.credit.interfaces.ITokenService;
import com.picpay.credit.security.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService implements ITokenService {

  private static final String SECRET_KEY = "my-secret-key-123!@#";
  private static final String ISSUER = "my-system-app";

  public String generateToken(UserDetailsImpl user){
    try{
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

      var roles = user.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .map(role -> role.replace("ROLE_", ""))
              .toList();

      return JWT.create()
              .withIssuer(ISSUER)
              .withIssuedAt(creationDate())
              .withExpiresAt(expirationDate())
              .withSubject(user.getUsername())
              .withClaim("role", roles)
              .sign(algorithm);
    } catch(JWTCreationException ex){
      throw new RuntimeException("Error when try generate token", ex);
    }
  }

  public String getSubjectFromToken(String token){
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
      return JWT.require(algorithm)
              .withIssuer(ISSUER)
              .build()
              .verify(token)
              .getSubject();
    } catch(JWTVerificationException ex){
      throw new JWTVerificationException("Token invalid or expired", ex);
    }
  }

  private Date creationDate(){
    return new Date();
  }

  private Date expirationDate(){
    long ONE_HOUR = 60 * 60 * 1000;
    return new Date(System.currentTimeMillis() + ONE_HOUR);
  }
}
