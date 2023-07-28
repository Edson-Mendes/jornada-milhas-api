package br.com.emendes.jornadamilhasapi.service.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Record DTO para receber dados de criação de Destination.
 */
public record DestinationRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 150, message = "name must contain between {min} and {max} characters long")
    String name,
    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive")
    @Digits(integer = 6, fraction = 2,
        message = "price must contain max {integer} integer digits and max {fraction} fraction digits")
    BigDecimal price
) {
}
