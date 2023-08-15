package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface service com as abstrações para manipulação do recurso User.
 */
public interface UserService {

  /**
   * Salva um usuário no sistema.
   *
   * @param createUserRequest DTO contendo as informações do novo usuário.
   * @return {@link UserResponse} contendo informações do usuário salvo.
   */
  UserResponse save(CreateUserRequest createUserRequest, MultipartFile image);

}
