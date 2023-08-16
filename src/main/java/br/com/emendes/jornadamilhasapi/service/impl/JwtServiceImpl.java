package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.model.User;
import br.com.emendes.jornadamilhasapi.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.Key;
import java.util.Date;

import static br.com.emendes.jornadamilhasapi.config.OpenAPIConfig.API_TITLE;

/**
 * Implementação de {@link JwtService}.
 */
@Slf4j
@Service
public class JwtServiceImpl implements JwtService {

  @Value("${jornadamilhas.jwt.expiration}")
  private String expiration;
  @Value("${jornadamilhas.jwt.secret}")
  private String secret;

  @Override
  public String generateToken(User user) {
    Assert.notNull(user, "user must not be null");
    Assert.notNull(user.getEmail(), "user email must not be null");

    long currentTimeMillis = System.currentTimeMillis();
    return Jwts.builder()
        .setIssuer(API_TITLE)
        .setSubject(user.getEmail())
        .setIssuedAt(new Date(currentTimeMillis))
        .setExpiration(new Date(currentTimeMillis + Long.parseLong(expiration)))
        .signWith(getKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      getClaims(token);
      return true;
    } catch (JwtException exception) {
      log.info("invalid token: {}, exception message: {}", token, exception.getMessage());
      return false;
    }
  }

  @Override
  public String extractSubject(String token) {
    Claims claims = getClaims(token);

    return claims.getSubject();
  }

  /**
   * retorna {@link Claims} que estão do token.
   */
  private Claims getClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  /**
   * retorna {@link Key} de acordo com a secret.
   */
  private Key getKey() {
    byte[] secretBytes = secret.getBytes();
    return Keys.hmacShaKeyFor(secretBytes);
  }

}
