package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/destinations
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

  private final DestinationService destinationService;

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
  public ResponseEntity<DestinationResponse> save(
      @RequestPart(name = "destination_info") @Valid DestinationRequest destinationRequest,
      @RequestPart(name = "destination_image") MultipartFile image,
      UriComponentsBuilder uriBuilder) {
    DestinationResponse destinationResponse = destinationService.save(destinationRequest, image);
    URI uri = uriBuilder.path("/api/destinations/{id}").build(destinationResponse.id());

    return ResponseEntity.created(uri).body(destinationResponse);
  }

}
