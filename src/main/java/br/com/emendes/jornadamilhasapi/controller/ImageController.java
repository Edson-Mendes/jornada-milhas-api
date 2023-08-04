package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.validation.annotation.IdValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pelo endpoint /api/images
 */
@Tag(name = "Image", description = "Image management APIs")
@CrossOrigin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/images")
public class ImageController {

  private final ImageService imageService;

  /**
   * Trata requisição GET /api/images.
   *
   * @param imageId identificador da imagem solicitada.
   */
  @Operation(
      summary = "Search image by id",
      description = "Search image by id."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful found image", content = @Content),
      @ApiResponse(responseCode = "400", description = "Something is wrong with the request",
          content = @Content(mediaType = "application/json+problem", schema = @Schema(implementation = ProblemDetail.class))),
      @ApiResponse(responseCode = "404", description = "Image not found",
          content = @Content(mediaType = "application/json+problem", schema = @Schema(implementation = ProblemDetail.class))
      )
  })
  @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public ResponseEntity<Resource> findById(@PathVariable(name = "id") @IdValidation String imageId) {
    return ResponseEntity.ok(imageService.findById(imageId));
  }


}
