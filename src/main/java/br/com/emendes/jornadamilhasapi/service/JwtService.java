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

  /**
   * Verifica se o token é válido (não alterado e não expirado).
   *
   * @param token token a ser verificado.
   * @return true se o token for válido, false caso contrário.
   */
  boolean isTokenValid(String token);

  /**
   * Extrai o Subject do token.
   *
   * @param token que contém o subject.
   * @return Subject que estava no payload do token.
   */
  String extractSubject(String token);
}
