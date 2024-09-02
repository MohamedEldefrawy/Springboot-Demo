package com.vodafone.onionpatterndemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "AUTHORITIES")
@Getter
@Setter
public class Authority implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String authority;

  @ManyToMany(mappedBy = "authorities",
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Set<User> user;
}
