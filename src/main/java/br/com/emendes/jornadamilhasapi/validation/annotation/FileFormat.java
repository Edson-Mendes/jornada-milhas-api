package br.com.emendes.jornadamilhasapi.validation.annotation;

import br.com.emendes.jornadamilhasapi.validation.validator.FileFormatValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O elemento anotado deve ser igual a um dos formatos especificados.
 * <p>
 * Tipos suportados:
 * <ul>
 *     <li>{@code MultipartFile}</li>
 * </ul>
 * <p>
 * {@code null} elementos são considerados válidos.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {FileFormatValidator.class})
public @interface FileFormat {

  String message() default "Invalid file size";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  String[] format() default {};
}
