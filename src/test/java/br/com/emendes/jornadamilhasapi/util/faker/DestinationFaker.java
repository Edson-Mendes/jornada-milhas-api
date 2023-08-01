package br.com.emendes.jornadamilhasapi.util.faker;

import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

/**
 * Classe utilitária com métodos que devolvem objetos usados em testes.
 */
public class DestinationFaker {

  private DestinationFaker(){}

  /**
   * DTO DestinationResponse para ser usado em testes.
   */
  public static DestinationSummaryResponse destinationResponse() {
    return DestinationSummaryResponse.builder()
        .id("abcdef1234567890abcdef12")
        .name("Veneza - Itália")
        .price(new BigDecimal("500.00"))
        .urlImage(URI.create("http://urlimage.com/api/images/aaaabbbbccccddddeeeeffff"))
        .createdAt(LocalDateTime.parse("2023-07-31T10:00:00"))
        .build();
  }

}
