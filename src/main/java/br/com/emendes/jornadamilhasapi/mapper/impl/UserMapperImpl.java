package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.mapper.UserMapper;
import br.com.emendes.jornadamilhasapi.model.User;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link UserMapper}.
 */
@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User toUser(CreateUserRequest createUserRequest) {
    Assert.notNull(createUserRequest, "createUserRequest must not be null");

    return User.builder()
        .name(createUserRequest.name())
        .email(createUserRequest.email())
        .build();
  }

  @Override
  public UserResponse toUserResponse(User user) {
    Assert.notNull(user, "user must not be null");

    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .image(user.getImage())
        .createdAt(user.getCreatedAt())
        .build();
  }

}
