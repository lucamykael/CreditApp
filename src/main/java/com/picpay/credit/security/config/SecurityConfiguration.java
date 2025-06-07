package com.picpay.credit.security.config;

import com.picpay.credit.security.CustomAuthenticationEntryPoint;
import com.picpay.credit.security.UserAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final UserAuthenticationFilter userAuthenticationFilter;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  public static final String[] ENDPOINTS_WITH_AUTH_NOT_REQUIRED = {
          "/auth/login",
          "/users"
  };

  public static final String[] ENDPOINTS_WITH_AUTH_REQUIRED = {
          "/auth/hello"
  };

  public static final String[] ENDPOINTS_ADMIN = {
          "/users/user",
          "/auth/hello"
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
              .requestMatchers(ENDPOINTS_WITH_AUTH_NOT_REQUIRED).permitAll()
              .requestMatchers(ENDPOINTS_ADMIN).hasRole("ADMIN")
              .anyRequest().authenticated()
            )
            .addFilterBefore(userAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(ex -> ex
                    .authenticationEntryPoint(customAuthenticationEntryPoint))
            .build();

  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
  }
}
