package com.vodafone.onionpatterndemo.security.model;

import com.vodafone.onionpatterndemo.model.Authority;
import com.vodafone.onionpatterndemo.model.User;
import java.io.Serializable;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class SecurityUser implements UserDetails, Serializable {

  private final User user;

  @Override
  public Collection<Authority> getAuthorities() {
    return user.getAuthorities();
  }

  @Override
  public String getPassword() {
    return this.user.getPassword();
  }

  @Override
  public String getUsername() {
    return this.user.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }
}
