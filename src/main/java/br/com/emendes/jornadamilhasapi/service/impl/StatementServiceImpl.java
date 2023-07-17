package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.mapper.StatementMapper;
import br.com.emendes.jornadamilhasapi.model.Statement;
import br.com.emendes.jornadamilhasapi.repository.StatementRepository;
import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateStatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação de {@link StatementService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class StatementServiceImpl implements StatementService {

  private final StatementMapper statementMapper;
  private final StatementRepository statementRepository;

  @Override
  public StatementResponse save(CreateStatementRequest createStatementRequest) {
    log.info("attempt to save Statement.");
    Statement statement = statementMapper.toStatement(createStatementRequest);

    statement.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    statementRepository.save(statement);

    log.info("statement saved successful with id: {}", statement.getId());
    return statementMapper.toStatementResponse(statement);
  }

}
