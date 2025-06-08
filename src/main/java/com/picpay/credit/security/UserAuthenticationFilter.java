package com.picpay.credit.security;

import com.picpay.credit.entities.User;
import com.picpay.credit.repositories.UserRepository;
import com.picpay.credit.security.config.SecurityConfiguration;
import com.picpay.credit.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

  private final TokenService jwtTokenService;

  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    System.out.println("Request receive: " + request.getMethod() + " " + request.getRequestURI());

    if(checkIfEndpointIsNotPublic(request)){
      String token = recoveryToken(request);
      if(token != null){
        String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByEmail(subject).orElseThrow(() -> new UsernameNotFoundException("User not found" + subject));
        UserDetailsImpl userDetails = new UserDetailsImpl(user);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(),
                null,
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
      } else {
        throw new RuntimeException("Token is not present");
      }
    }

    filterChain.doFilter(request, response);
  }

  private String recoveryToken(HttpServletRequest request){
    String authorizationHeader = request.getHeader("Authorization");
    if(authorizationHeader != null){
      return authorizationHeader.replace("Bearer ", "");
    }

    return null;
  }

  private boolean checkIfEndpointIsNotPublic(HttpServletRequest request){
    String requestURI = request.getRequestURI();
    return !Arrays.asList(SecurityConfiguration.ENDPOINTS_WITH_AUTH_NOT_REQUIRED).contains(requestURI);
  }
}
