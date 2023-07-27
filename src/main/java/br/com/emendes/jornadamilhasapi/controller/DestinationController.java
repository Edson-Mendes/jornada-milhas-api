package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import br.com.emendes.jornadamilhasapi.validation.annotation.IdValidation;
import br.com.emendes.jornadamilhasapi.validation.annotation.ImageValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/destinations
 */
@CrossOrigin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/destinations", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DestinationController {

  private final DestinationService destinationService;

  /**
   * Método responsável por POST /api/destinations.
   *
   * @param destinationRequest que contém as informações do Destination a ser salvo
   * @param image              arquivo de imagem do destino.
   */
  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<DestinationResponse> save(
      @RequestPart(name = "destination_info") @Valid DestinationRequest destinationRequest,
      @RequestPart(name = "destination_image") @ImageValidation MultipartFile image,
      UriComponentsBuilder uriBuilder) {
    DestinationResponse destinationResponse = destinationService.save(destinationRequest, image);
    URI uri = uriBuilder.path("/api/destinations/{id}").build(destinationResponse.id());

    return ResponseEntity.created(uri).body(destinationResponse);
  }

  /**
   * Método responsável por GET /api/destinations/{id}.
   *
   * @param destinationId identificador do Destination a ser buscado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<DestinationResponse> findById(@PathVariable(name = "id") @IdValidation String destinationId) {
    return ResponseEntity.ok(destinationService.findById(destinationId));
  }

  /**
   * Método responsável por GET /api/destinations.
   *
   * @param pageable contém as informações de como a busca será paginada.
   */
  @GetMapping
  public ResponseEntity<Page<DestinationResponse>> fetch(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(destinationService.fetch(pageable));
  }

}