package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.mapper.StatementMapper;
import br.com.emendes.jornadamilhasapi.model.Statement;
import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link StatementMapper}.
 */
@Component
public class StatementMapperImpl implements StatementMapper {

  @Override
  public Statement toStatement(StatementRequest statementRequest) {
    Assert.notNull(statementRequest, "StatementRequest must not be null");

    return Statement.builder()
        .username(statementRequest.username())
        .text(statementRequest.text())
        .urlImage(statementRequest.urlImage())
        .build();
  }

  @Override
  public StatementResponse toStatementResponse(Statement statement) {
    Assert.notNull(statement, "statement must not be null");

    return StatementResponse.builder()
        .id(statement.getId())
        .username(statement.getUsername())
        .text(statement.getText())
        .urlImage(statement.getUrlImage())
        .createdAt(statement.getCreatedAt())
        .build();
  }

  @Override
  public void merge(Statement statement, StatementRequest statementRequest) {
    Assert.notNull(statementRequest, "StatementRequest must not be null");
    Assert.notNull(statement, "statement must not be null");

    statement.setUsername(statementRequest.username());
    statement.setText(statementRequest.text());
    statement.setUrlImage(statementRequest.urlImage());
  }

}
