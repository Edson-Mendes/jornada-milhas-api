package br.com.emendes.jornadamilhasapi.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

/**
 * Record DTO para receber dados de criação de Statement.
 */
@Builder
public record CreateStatementRequest(
    @NotBlank(message = "username must not be blank")
    @Size(min = 2, max = 100, message = "username must contain between {min} and {max} characters long")
    String username,
    @NotBlank(message = "text must not be blank")
    @Size(min = 2, max = 255, message = "text must contain between {min} and {max} characters long")
    String text,
    @NotBlank(message = "urlImage must not be blank")
    @URL(message = "urlImage must be a well formed URL")
    String urlImage
) {
}
