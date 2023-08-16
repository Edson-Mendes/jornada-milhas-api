package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.model.User;
import br.com.emendes.jornadamilhasapi.service.AuthenticationService;
import br.com.emendes.jornadamilhasapi.service.JwtService;
import br.com.emendes.jornadamilhasapi.service.dto.request.AuthenticationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link AuthenticationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public TokenResponse authenticate(AuthenticationRequest authRequest) {
    Authentication authenticate = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

    User user = (User) authenticate.getPrincipal();

    String token = jwtService.generateToken(user);
    log.info("token generate successfully for user : {}", user.getUsername());

    return TokenResponse.builder()
        .type("Bearer")
        .token(token)
        .build();
  }

}
