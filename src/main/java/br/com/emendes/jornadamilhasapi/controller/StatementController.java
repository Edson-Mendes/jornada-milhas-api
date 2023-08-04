package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.StatementService;
import br.com.emendes.jornadamilhasapi.service.dto.request.StatementRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;
import br.com.emendes.jornadamilhasapi.validation.annotation.IdValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Controller responsável pelo endpoint /api/statements
 */
@Tag(name = "Statement", description = "Statement management APIs")
@CrossOrigin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/statements", produces = {"application/json;charset=UTF-8"})
public class StatementController {

  private final StatementService statementService;

  /**
   * Método responsável por POST /api/statements.
   *
   * @param statementRequest que contém as informações do Statement a ser salvo
   */
  @Operation(
      summary = "Save statement",
      description = "Save statement by sending a JSON on request body containing username, text and " +
          "urlImage."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful save statement",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = StatementResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PostMapping(consumes = {"application/json"})
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
  @Operation(
      summary = "Fetch page of statement",
      description = "Fetch page of statement, the client can adjust " +
          "page number, page size and sort parameter through parameters page, size and sort. " +
          "(e.g. /api/statements?page=2&size=5&sort=createdAt,ASC)."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful fetch page of statement")
  })
  @GetMapping
  public ResponseEntity<Page<StatementResponse>> fetch(@ParameterObject @PageableDefault Pageable pageable) {
    return ResponseEntity.ok(statementService.fetch(pageable));
  }

  /**
   * Método responsável por GET /api/statements/{id}.
   *
   * @param statementId identificador do Statement a ser buscado.
   */
  @Operation(
      summary = "Search statement by id",
      description = "Search statement by id, in case of success, a JSON containing id, username, text, urlImage and " +
          "createdAt fields will send on response body."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful found statement",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = StatementResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Statement not found")
  })
  @GetMapping("/{id}")
  public ResponseEntity<StatementResponse> findById(
      @PathVariable(name = "id") @IdValidation String statementId) {
    return ResponseEntity.ok(statementService.findById(statementId));
  }

  /**
   * Método responsável por GET /api/statements/home
   */
  @Operation(
      summary = "Fetch last three statements",
      description = "Fetch last three statements."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful fetch statements")
  })
  @GetMapping("/home")
  public ResponseEntity<List<StatementResponse>> fetchLast() {
    return ResponseEntity.ok(statementService.fetchLast(3));
  }

  /**
   * Método responsável por PUT /api/statements/{id}.
   *
   * @param statementId      identificador do Statement a ser atualizado.
   * @param statementRequest contento as novas informações do Statement.
   */
  @Operation(
      summary = "Update statement by id",
      description = "Update statement by id, a JSON containing new statement info (username, text and urlImage) " +
          "must be sent on request body."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful update statement"),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Statement not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable(name = "id") @IdValidation String statementId,
      @RequestBody @Valid StatementRequest statementRequest) {
    statementService.update(statementId, statementRequest);

    return ResponseEntity.noContent().build();
  }

  /**
   * Método responsável por DELETE /api/statements/{id}.
   *
   * @param statementId identificador do Statement a ser deletado.
   */
  @Operation(
      summary = "Delete statement by id",
      description = "Delete statement by id."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful delete statement"),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Statement not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") @IdValidation String statementId) {
    statementService.delete(statementId);

    return ResponseEntity.noContent().build();
  }

}
