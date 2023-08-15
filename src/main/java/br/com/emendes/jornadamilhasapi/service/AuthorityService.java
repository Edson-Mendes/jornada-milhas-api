package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.model.Authority;

/**
 * Interface service com as abstrações para manipulação do recurso Authority.
 */
public interface AuthorityService {

  /**
   * Busca uma Authority pelo nome.
   *
   * @param authorityName nome da authority.
   * @return {@link Authority} encontrada com o dado nome.
   */
  Authority findByName(String authorityName);

}
