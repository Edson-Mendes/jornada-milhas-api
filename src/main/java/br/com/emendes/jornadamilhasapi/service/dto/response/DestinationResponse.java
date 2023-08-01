package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Record DTO para enviar informações do Destination.
 */
@Builder
public record DestinationResponse(
    String id,
    String name,
    BigDecimal price,
    String meta,
    String description,
    List<URI> images,
    LocalDateTime createdAt

) {
}
