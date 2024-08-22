package com.vodafone.onionpatterndemo.security.service;

import com.vodafone.onionpatterndemo.repository.UserRepository;
import com.vodafone.onionpatterndemo.security.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InMemoryUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;
  private final UserDetailMapper userDetailMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userDetailMapper.mapUserToUserDetails(
        userRepository
            .findUserByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with name : " + username)));
  }
}
