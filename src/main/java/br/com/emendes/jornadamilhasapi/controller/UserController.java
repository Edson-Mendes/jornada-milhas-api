package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.UserService;
import br.com.emendes.jornadamilhasapi.service.dto.request.CreateUserRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Controller responsável pelo endpoint /api/users.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> save(
      @RequestBody @Valid CreateUserRequest createUserRequest,
      UriComponentsBuilder uriBuilder) {
    UserResponse userResponse = userService.save(createUserRequest);

    URI uri = uriBuilder.path("/api/users/{id}").build(userResponse.id());

    return ResponseEntity.created(uri).body(userResponse);
  }

}
