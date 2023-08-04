package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;
import br.com.emendes.jornadamilhasapi.validation.annotation.IdValidation;
import br.com.emendes.jornadamilhasapi.validation.annotation.ImageValidation;
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
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
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
@Tag(name = "Destination", description = "Destination management APIs")
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
  @Operation(
      summary = "Save destination",
      description = "Save destination by sending a multipart/form-data request with a JSON containing name, meta, " +
          "description (opcional) and price, and two image files in the format png or jpg."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Successful save destination",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationDetailsResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<DestinationDetailsResponse> save(
      @RequestPart(name = "destination_info") @Valid DestinationRequest destinationRequest,
      @RequestPart(name = "destination_image1") @ImageValidation MultipartFile image1,
      @RequestPart(name = "destination_image2") @ImageValidation MultipartFile image2,
      UriComponentsBuilder uriBuilder) {
    DestinationDetailsResponse destinationSummaryResponse = destinationService.save(destinationRequest, List.of(image1, image2));
    URI uri = uriBuilder.path("/api/destinations/{id}").build(destinationSummaryResponse.id());

    return ResponseEntity.created(uri).body(destinationSummaryResponse);
  }

  /**
   * Método responsável por GET /api/destinations.
   *
   * @param pageable contém as informações de como a busca será paginada.
   */
  @Operation(
      summary = "Fetch page of destination, optional search by destination name",
      description = "Fetch page of destination, optional search by destination name, the client can adjust " +
          "page number, page size and sort parameter through parameters page, size and sort. " +
          "(e.g. /api/destinations?page=2&size=5&sort=createdAt,ASC)."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful fetch page of destinations"),
      @ApiResponse(responseCode = "404", description = "When fetch by name and not found any destination",
          content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDetail.class)))
  })
  @GetMapping
  public ResponseEntity<Page<DestinationSummaryResponse>> fetch(
      @ParameterObject @PageableDefault Pageable pageable,
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
  @Operation(
      summary = "Search destination by id",
      description = "Search destination by id, in case of success, a JSON containing id, name, price, meta, description" +
          "images and createdAt fields will send on response body."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful found destination",
          content = @Content(mediaType = "application/json", schema = @Schema(implementation = DestinationDetailsResponse.class))),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Destination not found")
  })
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
   */
  @Operation(
      summary = "Update destination by id",
      description = "Update destination by id, a JSON containing new destination info (name, meta, description, price) " +
          "must be sent on request body."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful update destination"),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Destination not found")
  })
  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
      @PathVariable(name = "id") @IdValidation String destinationId,
      @RequestBody @Valid DestinationRequest destinationRequest) {
    destinationService.update(destinationId, destinationRequest);
    return ResponseEntity.noContent().build();
  }

  /**
   * Método responsável por PATCH /api/destinations/{destinationId}/images/{imagesId}.
   *
   * @param destinationId identificador do Destino.
   * @param imageId       identificador da imagem.
   * @param image         nova imagem do destino.
   */
  @Operation(
      summary = "Update destination image by id",
      description = "Update destination image by id, a file on format (png or jpeg) must be sent on request body."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful update destination image"),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Destination or image not found")
  })
  @PatchMapping(value = "/{destinationId}/images/{imageId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<Void> updateImage(
      @PathVariable(name = "destinationId") @IdValidation String destinationId,
      @PathVariable(name = "imageId") @IdValidation String imageId,
      @RequestParam(value = "destination_image") @ImageValidation MultipartFile image) {
    destinationService.updateImage(destinationId, imageId, image);
    return ResponseEntity.noContent().build();
  }

  /**
   * Método responsável por DELETE /api/destinations/{id}
   *
   * @param destinationId identificador do Destination a ser deletado.
   */
  @Operation(
      summary = "Delete destination by id",
      description = "Delete all information about destination, including the destination images."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Successful delete destination"),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request"),
      @ApiResponse(responseCode = "404", description = "Destination not found")
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable(name = "id") @IdValidation String destinationId) {
    destinationService.delete(destinationId);
    return ResponseEntity.noContent().build();
  }

}
