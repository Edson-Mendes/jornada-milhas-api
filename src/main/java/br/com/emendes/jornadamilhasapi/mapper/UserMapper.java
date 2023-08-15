package br.com.emendes.jornadamilhasapi.mapper;

import br.com.emendes.jornadamilhasapi.model.User;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;

/**
 * Interface component com as abstrações de mapeamento entre User e DTOs e vice-versa.
 */
public interface UserMapper {

  /**
   * Mapeia o DTO CreateUserRequest para o document User.<br>
   * createUserRequest não deve ser null
   *
   * @param createUserRequest que será mapeado para User.
   * @return {@link User} contendo as informações que estavam em createUserRequest.
   */
  User toUser(CreateUserRequest createUserRequest);

  /**
   * Mapeia o document User para o DTO UserResponse.<br>
   * user não deve ser null.
   *
   * @param user que será mapeado para UserResponse.
   * @return {@link UserResponse} contendo as informações que estavam em user.
   */
  UserResponse toUserResponse(User user);
}
