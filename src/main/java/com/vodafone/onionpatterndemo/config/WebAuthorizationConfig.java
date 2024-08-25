package com.vodafone.onionpatterndemo.config;

import com.vodafone.onionpatterndemo.security.filter.AuthenticationLoggingFilter;
import com.vodafone.onionpatterndemo.security.filter.RequestIdHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class WebAuthorizationConfig {


  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

    http.httpBasic(Customizer.withDefaults());
    http.addFilterBefore(new RequestIdHeaderFilter(), UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(new AuthenticationLoggingFilter(), UsernamePasswordAuthenticationFilter.class);

    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
    {
      authorizationManagerRequestMatcherRegistry.requestMatchers("/h2-console/**").permitAll();
      authorizationManagerRequestMatcherRegistry.anyRequest().authenticated();
    });

    http.csrf(AbstractHttpConfigurer::disable);
    http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(Customizer.withDefaults()).disable());

    return http.build();
  }
}
