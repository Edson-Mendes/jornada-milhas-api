package br.com.emendes.jornadamilhasapi.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Record DTO para receber dados de autenticação de usuário.
 */
public record AuthenticationRequest(
    @NotBlank(message = "email must not be blank")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "password must not be blank")
    String password
) {
}
