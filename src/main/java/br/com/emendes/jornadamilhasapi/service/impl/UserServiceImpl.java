package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.service.AuthorityService;
import br.com.emendes.jornadamilhasapi.service.UserService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link UserService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final AuthorityService authorityService;

  @Override
  public UserResponse save(CreateUserRequest createUserRequest) {
    // TODO: Verificar se o password corresponde com confirmPassword.
    // TODO: mapear createUserRequest para User.
    // TODO: atribuir createdAt.
    // TODO: Buscar Authority USER no DB.
    // TODO: atribuir authority encontrada ao novo usuário.
    // TODO: Persistir User no DB.
    // TODO: Mapear User para UserResponse.
    return null;
  }



}
