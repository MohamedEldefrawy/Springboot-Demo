package com.vodafone.onionpatterndemo.security.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RequestIdHeaderFilter implements Filter {
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    HttpServletResponse response = (HttpServletResponse) servletResponse;
    if (StringUtils.hasText(request.getHeader("X-Request-ID"))) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
