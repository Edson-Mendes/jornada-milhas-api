package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/statements
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statements")
public class StatementController {

  private final StatementService statementService;

  /**
   * Método responsável por POST /api/statements.
   *
   * @param statementRequest que contém as informações do Statement a ser salvo
   */
  @PostMapping
  public ResponseEntity<StatementResponse> save(
      @RequestBody @Valid StatementRequest statementRequest) {
    StatementResponse statementResponse = statementService.save(statementRequest);

    URI uri = URI.create(String.format("/api/statements/%s", statementResponse.id()));

    return ResponseEntity.created(uri).body(statementResponse);
  }

  /**
   * Método responsável por GET /api/statements.
   *
   * @param pageable que contém as informações de como a busca será paginada.
   */
  @GetMapping
  public ResponseEntity<Page<StatementResponse>> fetch(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(statementService.fetch(pageable));
  }

  /**
   * Método responsável por GET /api/statements/{id}.
   *
   * @param statementId      identificador do Statement a ser atualizado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<StatementResponse> findById(@PathVariable(name = "id") String statementId) {
    return ResponseEntity.ok(statementService.findById(statementId));
  }

  /**
   * Método responsável por PUT /api/statements/{id}.
   *
   * @param statementId      identificador do Statement a ser atualizado.
   * @param statementRequest contento as novas informações do Statement.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable(name = "id") String statementId,
      @RequestBody @Valid StatementRequest statementRequest) {
    statementService.update(statementId, statementRequest);

    return ResponseEntity.noContent().build();
  }

}
