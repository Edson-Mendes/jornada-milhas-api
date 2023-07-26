package br.com.emendes.jornadamilhasapi.validation.annotation;

import br.com.emendes.jornadamilhasapi.validation.validator.FileSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O elemento anotado deve possuir um tamanho (em bytes) menor ou igual ao especificado.
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
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSize {

  String message() default "Invalid file size";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  long bytes() default 10_485_760L;

}
