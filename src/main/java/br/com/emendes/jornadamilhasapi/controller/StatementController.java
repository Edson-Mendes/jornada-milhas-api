package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateStatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/statement
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/statement")
public class StatementController {

  private final StatementService statementService;

  /**
   * Método responsável por POST /api/statement.
   *
   * @param statementRequest que contém as informações do Statement a ser salvo
   */
  @PostMapping
  public ResponseEntity<StatementResponse> save(
      @RequestBody @Valid CreateStatementRequest statementRequest) {
    StatementResponse statementResponse = statementService.save(statementRequest);

    URI uri = URI.create(String.format("/api/statement/%s", statementResponse.id()));

    return ResponseEntity.created(uri).body(statementResponse);
  }

}
