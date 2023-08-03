package br.com.emendes.jornadamilhasapi.util.faker;

import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe utilitária com métodos que devolvem objetos usados em testes.
 */
public class DestinationFaker {

  private DestinationFaker(){}

  /**
   * DTO DestinationSummaryResponse para ser usado em testes.
   */
  public static DestinationSummaryResponse destinationSummaryResponse() {
    return DestinationSummaryResponse.builder()
        .id("abcdef1234567890abcdef12")
        .name("Veneza - Itália")
        .price(new BigDecimal("500.00"))
        .image(URI.create("http://urlimage.com/api/images/aaaabbbbccccddddeeeeffff"))
        .createdAt(LocalDateTime.parse("2023-07-31T10:00:00"))
        .build();
  }

  /**
   * DTO DestinationDetailsResponse para ser usado em testes.
   */
  public static DestinationDetailsResponse destinationDetailsResponse() {
    List<URI> uriList = List.of(
        URI.create("http://urlimage.com/api/images/aaaabbbbccccddddeeeeffff"),
        URI.create("http://urlimage.com/api/images/ffffeeeeddddccccbbbbaaaa"));

    return DestinationDetailsResponse.builder()
        .id("abcdef1234567890abcdef12")
        .name("Veneza - Itália")
        .meta("Uma bela cidade da Itália.")
        .description("Lorem ipsum dolor sit amet")
        .price(new BigDecimal("500.00"))
        .images(uriList)
        .createdAt(LocalDateTime.parse("2023-07-31T10:00:00"))
        .build();
  }
}
