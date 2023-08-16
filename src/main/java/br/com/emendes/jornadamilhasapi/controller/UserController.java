package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.UserService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import br.com.emendes.jornadamilhasapi.validation.annotation.ImageValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/users.
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  /**
   * Método responsável por POST /api/users.
   *
   * @param createUserRequest que contém as informações do User a ser salvo
   * @param image             arquivo de imagem do User.
   */
  @PostMapping
  public ResponseEntity<UserResponse> save(
      @RequestPart(name = "user_info") @Valid CreateUserRequest createUserRequest,
      @RequestPart(name = "user_image") @ImageValidation MultipartFile image,
      UriComponentsBuilder uriBuilder) {
    UserResponse userResponse = userService.save(createUserRequest, image);

    URI uri = uriBuilder.path("/api/users/{id}").build(userResponse.id());

    return ResponseEntity.created(uri).body(userResponse);
  }

}
