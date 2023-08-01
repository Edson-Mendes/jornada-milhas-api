package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

/**
 * Record DTO para enviar informações resumidas do Destination.
 */
@Builder
public record DestinationSummaryResponse(
    String id,
    String name,
    BigDecimal price,
    URI image,
    LocalDateTime createdAt

) {
}
