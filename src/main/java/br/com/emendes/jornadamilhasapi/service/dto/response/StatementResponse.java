package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Record DTO para enviar informações do Statement para o cliente.
 */
@Builder
public record StatementResponse(
    String id,
    String username,
    String text,
    String urlImage,
    LocalDateTime createdAt
) {
}
