package com.vodafone.onionpatterndemo.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class RequestIdHeaderFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (StringUtils.hasText(request.getHeader("X-Request-ID"))) {
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

  }
}
