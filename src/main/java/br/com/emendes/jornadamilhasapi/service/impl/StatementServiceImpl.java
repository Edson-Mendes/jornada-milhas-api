package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.mapper.StatementMapper;
import br.com.emendes.jornadamilhasapi.model.Statement;
import br.com.emendes.jornadamilhasapi.repository.StatementRepository;
import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
  public StatementResponse save(StatementRequest statementRequest) {
    log.info("attempt to save Statement.");
    Statement statement = statementMapper.toStatement(statementRequest);

    statement.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    statementRepository.save(statement);

    log.info("statement saved successful with id: {}", statement.getId());
    return statementMapper.toStatementResponse(statement);
  }

  @Override
  public Page<StatementResponse> fetch(Pageable pageable) {
    log.info("fetching page: {} and size: {} of Statements.", pageable.getPageNumber(), pageable.getPageSize());

    Page<Statement> statementPage = statementRepository.findAll(pageable);

    return statementPage.map(statementMapper::toStatementResponse);
  }

  @Override
  public List<StatementResponse> fetchLast(int quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("quantity must not be equal or less than 0");
    }
    log.info("fetching last {} statements", quantity);

    Pageable pageable = PageRequest.of(0, quantity, Sort.Direction.DESC, "createdAt");
    Page<StatementResponse> statementResponsePage = fetch(pageable);

    return statementResponsePage.getContent();
  }

  @Override
  public StatementResponse findById(String statementId) {
    log.info("attempt to fetch statement with id: {}", statementId);

    Statement statement = findStatementById(statementId);

    log.info("statement found successful with id: {}", statement.getId());
    return statementMapper.toStatementResponse(statement);
  }

  @Override
  public void update(String statementId, StatementRequest statementRequest) {
    log.info("attempt to update statement with id: {}", statementId);

    Statement statement = findStatementById(statementId);

    statementMapper.merge(statement, statementRequest);
    statementRepository.save(statement);

    log.info("statement updated successful with id: {}", statement.getId());
  }

  @Override
  public void delete(String statementId) {
    log.info("attempt to delete Statement with id: {}", statementId);

    Statement statement = findStatementById(statementId);
    statementRepository.delete(statement);

    log.info("statement deleted successful with id: {}", statement.getId());
  }

  /**
   * Busca Statement por id.
   *
   * @param statementId identificador do Statement.
   * @return Statement para o dado id.
   * @throws ResourceNotFoundException caso não seja encontrado Statement para o dado statementId.
   */
  private Statement findStatementById(String statementId) {
    return statementRepository.findById(statementId).orElseThrow(() -> {
      log.info("statement not found for id: {}", statementId);
      return new ResourceNotFoundException("Statement not found");
    });
  }

}
