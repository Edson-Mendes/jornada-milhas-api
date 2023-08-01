package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Record DTO para enviar informações mais detalhadas sobre Destination.
 */
@Builder
public record DestinationDetailsResponse(
    String id,
    String name,
    BigDecimal price,
    String meta,
    String description,
    List<URI> images,
    LocalDateTime createdAt

) {
}
