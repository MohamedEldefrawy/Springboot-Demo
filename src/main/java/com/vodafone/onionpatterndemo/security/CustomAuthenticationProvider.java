package com.vodafone.onionpatterndemo.security;

import com.vodafone.onionpatterndemo.model.User;
import com.vodafone.onionpatterndemo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
  private final UserRepository userRepository;

  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    User user = this.userRepository.findUserByEmail(username).orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Bad Credentials"));
    if (username.equals(user.getEmail()) && password.equals(user.getPassword())) {
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
