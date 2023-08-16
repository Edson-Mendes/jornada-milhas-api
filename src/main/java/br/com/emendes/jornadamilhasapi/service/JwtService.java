package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.model.User;

/**
 * Interface service com as abstrações para manipulação de JWT.
 */
public interface JwtService {

  /**
   * Gera um JWT (Json Web Token) a partir de um {@link User}.
   *
   * @param user a quem irá pertencer o JWT.
   * @return JWT
   */
  String generateToken(User user);

}
