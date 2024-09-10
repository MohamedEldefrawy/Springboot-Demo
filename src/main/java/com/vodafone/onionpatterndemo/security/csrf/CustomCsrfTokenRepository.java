package com.vodafone.onionpatterndemo.security.csrf;

import com.vodafone.onionpatterndemo.repository.CsrfTokenJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

  public static final String X_CSRF_TOKEN = "X-CSRF-TOKEN";
  public static final String CSRF_ATTRIBUTE = "_csrf";
  private final CsrfTokenJpaRepository csrfTokenJpaRepository;

  @Override
  public CsrfToken generateToken(HttpServletRequest request) {
    String token = UUID.randomUUID().toString();
    com.vodafone.onionpatterndemo.model.CsrfToken csrfToken = new com.vodafone.onionpatterndemo.model.CsrfToken();
    csrfToken.setToken(token);
    csrfToken.setCreationDate(LocalDateTime.now());
    this.csrfTokenJpaRepository.save(csrfToken);
    return new DefaultCsrfToken(X_CSRF_TOKEN, CSRF_ATTRIBUTE, token);
  }

  @Override
  public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {
    log.info("token to be saved: {}", token.getToken());
    Optional<com.vodafone.onionpatterndemo.model.CsrfToken> selectedToken = this.csrfTokenJpaRepository.findById(token.getToken());

    if (selectedToken.isPresent()) {
      selectedToken.get().setToken(token.getToken());
      this.csrfTokenJpaRepository.save(selectedToken.get());
    } else {
      com.vodafone.onionpatterndemo.model.CsrfToken newToken = new com.vodafone.onionpatterndemo.model.CsrfToken();
      newToken.setToken(token.getToken());
      newToken.setCreationDate(LocalDateTime.now());
      this.csrfTokenJpaRepository.save(newToken);

    }
  }

  @Override
  public CsrfToken loadToken(HttpServletRequest request) {

    String header = request.getHeader(X_CSRF_TOKEN);

    if (StringUtils.hasText(header)) {
      Optional<com.vodafone.onionpatterndemo.model.CsrfToken> customTokenOptional = this.csrfTokenJpaRepository.findById(header);
      if (customTokenOptional.isPresent()) {
        return new DefaultCsrfToken(X_CSRF_TOKEN, CSRF_ATTRIBUTE, customTokenOptional.get().getToken());
      }
    }
    return generateToken(request);
  }
}
