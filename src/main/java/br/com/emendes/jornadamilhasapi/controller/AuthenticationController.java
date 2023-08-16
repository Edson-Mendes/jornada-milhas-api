package br.com.emendes.jornadamilhasapi.controller;

import br.com.emendes.jornadamilhasapi.service.AuthenticationService;
import br.com.emendes.jornadamilhasapi.service.dto.request.AuthenticationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo endpoint /api/auth
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  /**
   * Método responsável por POST /api/auth.
   *
   * @param authRequest que contém as informações de autenticação do usuário.
   * @return {@link TokenResponse} contendo JWT.
   */
  @PostMapping
  public ResponseEntity<TokenResponse> authenticate(@RequestBody @Valid AuthenticationRequest authRequest) {
    return ResponseEntity.ok(authenticationService.authenticate(authRequest));
  }
}
