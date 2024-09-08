package com.vodafone.onionpatterndemo.config;

import com.vodafone.onionpatterndemo.security.CustomAuthenticationProvider;
import com.vodafone.onionpatterndemo.security.CustomEntryPoint;
import com.vodafone.onionpatterndemo.security.filter.AuthenticationLoggingFilter;
import com.vodafone.onionpatterndemo.security.filter.CsrfTokenLoggerFilter;
import com.vodafone.onionpatterndemo.security.filter.RequestIdHeaderFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
@RequiredArgsConstructor
public class WebAuthorizationConfig {

  private final CustomAuthenticationProvider customAuthenticationProvider;
  private final AuthenticationLoggingFilter authenticationLoggingFilter;
  private final RequestIdHeaderFilter requestIdHeaderFilter;
  private final CsrfTokenLoggerFilter csrfTokenLoggerFilter;

  @Bean
  public SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {

    http.headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(Customizer.withDefaults()).disable());
    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
    {
      authorizationManagerRequestMatcherRegistry.requestMatchers("/h2-console/**").permitAll();
      authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.POST, "/users").hasAuthority("can-create-users");
      authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.GET, "/users/**").hasAuthority("can-read-users");
    });

    http.httpBasic(httpSecurityHttpBasicConfigurer ->
    {
      httpSecurityHttpBasicConfigurer.realmName("Other");
      httpSecurityHttpBasicConfigurer.authenticationEntryPoint(new CustomEntryPoint());
    });

    http.authenticationProvider(customAuthenticationProvider);
    http.addFilterBefore(requestIdHeaderFilter, UsernamePasswordAuthenticationFilter.class);
    http.addFilterAfter(csrfTokenLoggerFilter, CsrfFilter.class);

    return http.build();
  }
}
