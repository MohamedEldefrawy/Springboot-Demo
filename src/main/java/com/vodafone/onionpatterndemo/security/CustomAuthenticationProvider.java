package com.vodafone.onionpatterndemo.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private final CustomUserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
    if (username.equals(userDetails.getUsername()) && passwordEncoder.matches(userDetails.getPassword(), password)) {
      return new UsernamePasswordAuthenticationToken(username, password, List.of());
    } else {
      throw new AuthenticationCredentialsNotFoundException("Incorrect password");
    }
  }

  @Override
  public boolean supports(Class<?> authenticationType) {
    return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
  }
}
