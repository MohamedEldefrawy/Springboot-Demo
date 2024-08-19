package com.vodafone.onionpatterndemo.config;

import com.vodafone.onionpatterndemo.security.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
  private final CustomAuthenticationProvider customAuthenticationProvider;

  @Bean
  public UserDetailsService getUserDetailsService() {
    UserDetails userDetails = User.builder().username("user")
        .password("P@ssw0rd").roles("USER").build();
    return new InMemoryUserDetailsManager(userDetails);
  }

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
    http.httpBasic(Customizer.withDefaults());
    http.authenticationProvider(customAuthenticationProvider);
    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
        authorizationManagerRequestMatcherRegistry.anyRequest().authenticated());
    return http.build();
  }
}
