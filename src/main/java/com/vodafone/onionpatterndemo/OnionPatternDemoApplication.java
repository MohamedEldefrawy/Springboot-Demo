package com.vodafone.onionpatterndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.vodafone.onionPatternDemo.model")
public class OnionPatternDemoApplication {

  public static void main(String[] args) {

    ConfigurableApplicationContext context = SpringApplication.run(OnionPatternDemoApplication.class, args);
//    UserService userService = context.getBean(UserService.class);
//    userService.save(User.builder().id("1").name("Mohamed").build());
//
//    List<User> users = userService.findAll();
//
//    System.out.println(users);

  }

}
