package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;
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
import java.util.List;

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
   * @param image1             arquivo de imagem do destino.
   * @param image2             arquivo de imagem do destino.
   */
  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<DestinationSummaryResponse> save(
      @RequestPart(name = "destination_info") @Valid DestinationRequest destinationRequest,
      @RequestPart(name = "destination_image1") @ImageValidation MultipartFile image1,
      @RequestPart(name = "destination_image2") @ImageValidation MultipartFile image2,
      UriComponentsBuilder uriBuilder) {
    DestinationSummaryResponse destinationSummaryResponse = destinationService.save(destinationRequest, List.of(image1, image2));
    URI uri = uriBuilder.path("/api/destinations/{id}").build(destinationSummaryResponse.id());

    return ResponseEntity.created(uri).body(destinationSummaryResponse);
  }

  /**
   * Método responsável por GET /api/destinations.
   *
   * @param pageable contém as informações de como a busca será paginada.
   */
  @GetMapping
  public ResponseEntity<Page<DestinationSummaryResponse>> fetch(
      @PageableDefault Pageable pageable,
      @RequestParam(value = "name", required = false) String name) {
    if (name == null) {
      return ResponseEntity.ok(destinationService.fetch(pageable));
    }
    return ResponseEntity.ok(destinationService.findByName(pageable, name));
  }

  /**
   * Método responsável por GET /api/destinations/{id}.
   *
   * @param destinationId identificador do Destination a ser buscado.
   */
  @GetMapping("/{id}")
  public ResponseEntity<DestinationDetailsResponse> findById(
      @PathVariable(name = "id") @IdValidation String destinationId) {
    return ResponseEntity.ok(destinationService.findById(destinationId));
  }

  /**
   * Método responsável por PUT /api/destinations/{id}.
   *
   * @param destinationId      identificador do Destination a ser buscado.
   * @param destinationRequest que contém as novas informações do Destination.
   * @param image              arquivo de imagem do destino.
   */
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable(name = "id") @IdValidation String destinationId,
      @RequestPart(name = "destination_info") @Valid DestinationRequest destinationRequest,
      @RequestPart(name = "destination_image", required = false) @ImageValidation MultipartFile image) {
    destinationService.update(destinationId, destinationRequest, image);
    return ResponseEntity.noContent().build();
  }

  /**
   * Método responsável por DELETE /api/destinations/{id}
   *
   * @param destinationId identificador do Destination a ser deletado.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") @IdValidation String destinationId) {
    destinationService.delete(destinationId);
    return ResponseEntity.noContent().build();
  }

}
