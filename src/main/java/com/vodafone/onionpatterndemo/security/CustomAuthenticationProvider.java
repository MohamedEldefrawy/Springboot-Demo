package com.vodafone.onionpatterndemo.security;

import java.util.List;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    if (username.equals("user") && password.equals("P@ssw0rd")) {
      return new UsernamePasswordAuthenticationToken(username, password, List.of());
    } else {
      throw new AuthenticationCredentialsNotFoundException("Bad credentials");
    }
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }
}
