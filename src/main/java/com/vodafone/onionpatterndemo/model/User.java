package com.vodafone.onionpatterndemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String email;
  private String name;
  private String phone;
  private String password;
  private String authority;
  private LocalDateTime creationDate;


  @PrePersist
  private void setDefaultCreationDate() {
    if (creationDate == null) {
      creationDate = LocalDateTime.now();
    }
  }
}
