package com.vodafone.onionpatterndemo.mapper;

import com.vodafone.onionpatterndemo.dto.CreateUserRequest;
import com.vodafone.onionpatterndemo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  @Mapping(source = "id", target = "id")
  @Mapping(source = "username", target = "userName")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phone", target = "phone")
  User mapCreateUserRequestToUser(CreateUserRequest createUserRequest);
}
