package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.AuthenticationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.TokenResponse;

/**
 * Interface service com as abstrações relacionadas a autenticação de usuário.
 */
public interface AuthenticationService {

  /**
   * Autentica um usuário.
   *
   * @param authRequest contém os dados de autenticação do usuário.
   * @return {@link TokenResponse} contendo o JWT que deve ser enviado a cada requisição.
   */
  TokenResponse authenticate(AuthenticationRequest authRequest);

}
