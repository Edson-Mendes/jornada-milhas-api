package br.com.emendes.jornadamilhasapi.mapper;

import br.com.emendes.jornadamilhasapi.model.Statement;
import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;

/**
 * Interface component com as abstrações de mapeamento entre {@link Statement} e DTOs e vice-versa.
 */
public interface StatementMapper {

  /**
   * Mapeia o DTO CreateStatementRequest para o document Statement.<br>
   * createStatementRequest não deve ser null.
   *
   * @param statementRequest que será mapeado para Statement.
   * @return {@link Statement} contendo as informações que estavam em createStatementRequest.
   */
  Statement toStatement(StatementRequest statementRequest);

  /**
   * Mapeia um document Statement para o DTO StatementResponse.
   *
   * @param statement que será mapeado para StatementResponse.
   * @return {@link StatementResponse} contendo informações sobre Statement.
   */
  StatementResponse toStatementResponse(Statement statement);

  /**
   * Mescla as informações dentro de statement com as informações contidas em createStatementRequest.
   *
   * @param statement              que receberá as novas informações.
   * @param statementRequest que contém as novas informações.
   */
  void merge(Statement statement, StatementRequest statementRequest);

}
