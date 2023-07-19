package br.com.emendes.jornadamilhasapi.util.faker;

import br.com.emendes.jornadamilhasapi.service.dto.response.StatementResponse;

import java.time.LocalDateTime;

/**
 * Classe utilitária com métodos que devolvem objetos usados em testes.
 */
public class StatementFaker {

  private StatementFaker(){}

  /**
   * DTO StatementResponse para ser usado em testes.
   */
  public static StatementResponse statementResponse() {
    return StatementResponse.builder()
        .id("1234567890abcdef12345678")
        .username("Lorem Ipsum")
        .text("lorem ipsum dolor sit amet")
        .urlImage("https://www.imagexpto.com/123")
        .createdAt(LocalDateTime.parse("2023-07-19T10:00:00"))
        .build();
  }

}
