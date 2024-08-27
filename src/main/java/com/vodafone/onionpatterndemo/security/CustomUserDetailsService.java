package com.vodafone.onionpatterndemo.security;

import com.vodafone.onionpatterndemo.model.User;
import com.vodafone.onionpatterndemo.repository.UserRepository;
import com.vodafone.onionpatterndemo.security.mapper.UserDetailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;
  private final UserDetailMapper userDetailMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User selectedUser = this.userRepository.findUserByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + username));
    return this.userDetailMapper.mapUserToUserDetails(selectedUser);
  }
}
