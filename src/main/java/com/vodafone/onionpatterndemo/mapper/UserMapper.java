package com.vodafone.onionpatterndemo.mapper;

import com.vodafone.onionpatterndemo.dto.AuthorityDto;
import com.vodafone.onionpatterndemo.dto.CreateUserRequest;
import com.vodafone.onionpatterndemo.dto.UserDto;
import com.vodafone.onionpatterndemo.model.Authority;
import com.vodafone.onionpatterndemo.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
  @Mapping(source = "id", target = "id")
  @Mapping(source = "username", target = "userName")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phone", target = "phone")
  User mapCreateUserRequestToUser(CreateUserRequest createUserRequest);

  @Mapping(source = "id", target = "id")
  @Mapping(source = "userName", target = "userName")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "phone", target = "phone")
  @Mapping(source = "creationDate", target = "creationDate")
  @Mapping(source = "authorities", target = "authorities", qualifiedByName = "mapAuthorities")
  UserDto mapUserToUserDto(User user);

  @Named(value = "mapAuthorities")
  default List<AuthorityDto> mapAuthorities(Set<Authority> authorities) {

    List<AuthorityDto> authoritiesDto = new ArrayList<>();

    for (Authority authority : authorities) {
      authoritiesDto.add(new AuthorityDto().name(authority.getAuthority()));
    }
    return authoritiesDto;
  }
}
