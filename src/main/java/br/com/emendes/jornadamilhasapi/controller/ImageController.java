package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.validation.annotation.IdValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo endpoint /api/images
 */
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
  @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
  public ResponseEntity<Resource> findById(@PathVariable(name = "id") @IdValidation String imageId) {
    return ResponseEntity.ok(imageService.findById(imageId));
  }


}
