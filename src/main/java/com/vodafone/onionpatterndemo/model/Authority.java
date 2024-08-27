package com.vodafone.onionpatterndemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITIES")
@Getter
@Setter
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String authority;

  @OneToMany(mappedBy = "authority",cascade = CascadeType.ALL)
  private Set<User> user;
}
