package com.vodafone.onionpatterndemo.service;

import com.vodafone.onionpatterndemo.model.User;
import com.vodafone.onionpatterndemo.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CustomService<User> {

  private final UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> findAll() {
    return userRepository.findAll();
  }

  @Override
  public void deleteById(Long id) {
    userRepository.delete(findById(id));
  }

  @Override
  public User update(User user) {
    return this.userRepository.save(user);
  }

  @Override
  public User findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User findByEmail(String email) {
    return this.userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with name : " + email));
  }
}
