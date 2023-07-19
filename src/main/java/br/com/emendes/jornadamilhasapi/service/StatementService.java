package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Interface service com as abstrações para manipulação do recurso Statement.
 */
public interface StatementService {

  /**
   * Salva um Statement no sistema.
   *
   * @param statementRequest contendo as informações do novo Statement.
   * @return {@link StatementResponse} contendo as informações do Statement salvo.
   */
  StatementResponse save(StatementRequest statementRequest);

  /**
   * Busca paginada de Statements.
   *
   * @param pageable contendo o número da página e a quantidade de elementos a ser buscado.
   * @return {@code Page<StatementResponse>}
   */
  Page<StatementResponse> fetch(Pageable pageable);

  /**
   * Busca uma quantidade de Statements, a busca começa pelos Statements recentemente adicionados.
   *
   * @param quantity Quantidade de Statements a ser buscado.
   * @return {@code List<StatementResponse>}
   * @throws IllegalArgumentException caso quantity seja menor ou igual a zero.
   */
  List<StatementResponse> fetchLast(int quantity);

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
   * @param statementId      identificador do Statement.
   * @param statementRequest objeto que contém as novas informações do Statement.
   */
  void update(String statementId, StatementRequest statementRequest);

  /**
   * Deleta um Statement por id.
   *
   * @param statementId identificador do Statement a ser deletado.
   */
  void delete(String statementId);

}
