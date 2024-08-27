package com.vodafone.onionpatterndemo.controller;

import com.vodafone.onionpatterndemo.dto.CreateUserRequest;
import com.vodafone.onionpatterndemo.dto.UserDto;
import com.vodafone.onionpatterndemo.mapper.UserMapper;
import com.vodafone.onionpatterndemo.model.User;
import com.vodafone.onionpatterndemo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  @GetMapping("/users")
  public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false, name = "email") String email) {
    List<User> users = new ArrayList<>();
    if (Objects.isNull(email)) {
      users = userService.findAll();
      return new ResponseEntity<>(users.stream().map(this.userMapper::mapUserToUserDto).toList(), HttpStatus.OK);
    }
    users.add(userService.findByEmail(email));
    return new ResponseEntity<>(users.stream().map(this.userMapper::mapUserToUserDto).toList(), HttpStatus.OK);
  }


  @PostMapping("/users")
  public ResponseEntity<Void> saveUser(@RequestBody CreateUserRequest userRequest) {
    userService.save(userMapper.mapCreateUserRequestToUser(userRequest));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable Long id) {
    this.userService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    user.setId(id);
    return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
  }

}
