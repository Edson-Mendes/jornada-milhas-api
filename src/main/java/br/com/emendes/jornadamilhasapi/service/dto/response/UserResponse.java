package br.com.emendes.jornadamilhasapi.service.dto.response;

import lombok.Builder;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * Record DTO para enviar informações sobre User.
 */
@Builder
public record UserResponse(
    String id,
    String name,
    String email,
    URI image,
    LocalDateTime createdAt
) {
}
