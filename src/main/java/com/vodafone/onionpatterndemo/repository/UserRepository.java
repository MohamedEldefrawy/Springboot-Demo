package com.vodafone.onionpatterndemo.repository;

import com.vodafone.onionpatterndemo.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByEmail(String email);
}
