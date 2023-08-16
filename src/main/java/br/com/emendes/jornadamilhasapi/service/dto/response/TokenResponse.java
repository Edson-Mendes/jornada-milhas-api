package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar o token do usuário.
 */
@Builder
public record TokenResponse(
    String type,
    String token
) {
}
