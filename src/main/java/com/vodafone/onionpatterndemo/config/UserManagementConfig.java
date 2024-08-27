package com.vodafone.onionpatterndemo.config;

import com.vodafone.onionpatterndemo.repository.UserRepository;
import com.vodafone.onionpatterndemo.security.CustomUserDetailsService;
import com.vodafone.onionpatterndemo.security.mapper.UserDetailMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserManagementConfig {
  @Bean
  public UserDetailsService getUserDetailsService(UserRepository userRepository, UserDetailMapper userDetailMapper) {
    return new CustomUserDetailsService(userRepository, userDetailMapper);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }
}
