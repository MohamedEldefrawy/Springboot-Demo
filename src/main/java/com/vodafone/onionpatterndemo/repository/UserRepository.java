package com.vodafone.onionpatterndemo.repository;

import com.vodafone.onionpatterndemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByEmail(String email);
}
