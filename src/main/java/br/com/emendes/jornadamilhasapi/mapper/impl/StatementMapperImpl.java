package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.mapper.StatementMapper;
import br.com.emendes.jornadamilhasapi.model.Statement;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateStatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link StatementMapper}.
 */
@Component
public class StatementMapperImpl implements StatementMapper {

  @Override
  public Statement toStatement(CreateStatementRequest createStatementRequest) {
    Assert.notNull(createStatementRequest, "CreateStatementRequest must not be null");

    return Statement.builder()
        .username(createStatementRequest.username())
        .text(createStatementRequest.text())
        .urlImage(createStatementRequest.urlImage())
        .build();
  }

  @Override
  public StatementResponse toStatementResponse(Statement statement) {
    return StatementResponse.builder()
        .id(statement.getId())
        .username(statement.getUsername())
        .text(statement.getText())
        .urlImage(statement.getUrlImage())
        .createdAt(statement.getCreatedAt())
        .build();
  }

  @Override
  public void merge(Statement statement, CreateStatementRequest createStatementRequest) {
    statement.setUsername(createStatementRequest.username());
    statement.setText(createStatementRequest.text());
    statement.setUrlImage(createStatementRequest.urlImage());
  }

}
