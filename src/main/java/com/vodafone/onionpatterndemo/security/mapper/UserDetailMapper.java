package com.vodafone.onionpatterndemo.security.mapper;

import com.vodafone.onionpatterndemo.model.User;
import com.vodafone.onionpatterndemo.security.model.SecurityUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDetailMapper {
  @Mapping(source = "email", target = "user.email")
  @Mapping(source = "password", target = "user.password")
  @Mapping(source = "authorities", target = "user.authorities")
  SecurityUser mapUserToUserDetails(User user);
}
