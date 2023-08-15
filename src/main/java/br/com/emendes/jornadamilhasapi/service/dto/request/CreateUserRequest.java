package br.com.emendes.jornadamilhasapi.service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Record DTO para receber dados de User.
 */
public record CreateUserRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 150, message = "name must contain between {min} and {max} characters long")
    String name,
    @NotBlank(message = "email must not be blank")
    @Size(max = 150, message = "email must contain max {max} characters long")
    @Email(message = "email must be a well formed E-mail")
    String email,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 30, message = "password must contain between {min} and {max} characters long")
    String password,
    @NotBlank(message = "confirmPassword must not be blank")
    String confirmPassword
) {
}
