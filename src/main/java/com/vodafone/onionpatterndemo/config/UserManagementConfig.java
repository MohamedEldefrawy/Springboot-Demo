package com.vodafone.onionpatterndemo.config;

import com.vodafone.onionpatterndemo.repository.UserRepository;
import com.vodafone.onionpatterndemo.security.mapper.UserDetailMapper;
import com.vodafone.onionpatterndemo.security.service.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
  @Bean
  public UserDetailsService getUserDetailsService(UserRepository repository, UserDetailMapper mapper) {
    return new InMemoryUserDetailsService(repository, mapper);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
