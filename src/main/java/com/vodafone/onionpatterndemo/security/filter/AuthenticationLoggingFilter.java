package com.vodafone.onionpatterndemo.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
public class AuthenticationLoggingFilter extends OncePerRequestFilter {
  @Override
  public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
    String requestID = request.getHeader("X-Request-ID");
    log.info("Successfully logged in with X-Request-ID: {}", requestID);
    filterChain.doFilter(request, response);
  }
}
