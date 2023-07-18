package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.CreateStatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

  /**
   * Busca paginada de Statements.
   *
   * @param pageable contendo o número da página e a quantidade de elementos a ser buscado.
   * @return {@code Page<StatementResponse>}
   */
  Page<StatementResponse> fetch(Pageable pageable);

  /**
   * Busca de Statement por id.
   *
   * @param statementId identificador do Statement.
   * @return StatementResponse.
   */
  StatementResponse findById(String statementId);

  /**
   * Atualiza um Statement por id.
   *
   * @param statementId            identificador do Statement.
   * @param createStatementRequest objeto que contém as novas informações do Statement.
   */
  void update(String statementId, CreateStatementRequest createStatementRequest);

}
