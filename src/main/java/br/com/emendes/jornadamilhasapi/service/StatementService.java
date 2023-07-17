package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.CreateStatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;

/**
 * Interface service com as abstrações para manipulação do recurso Statement.
 */
public interface StatementService {

  /**
   * Salva um Statement no sistema.
   *
   * @param createStatementRequest contendo as informações do novo Statement.
   * @return {@link StatementResponse} contendo as informações do Statement salvo.
   */
  StatementResponse save(CreateStatementRequest createStatementRequest);

}
