package com.vodafone.onionpatterndemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CSRF_TOKENS")
@Getter
@Setter
public class CsrfToken {
  @Id
  private String token;
  private LocalDateTime creationDate;
}
