package br.com.emendes.jornadamilhasapi.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Combina as anotações {@link FileSize} com tamanho de arquivo menor ou igual a 10_485_760 bytes.
 * e {@link FileFormat} com os formatos jpeg e png.
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@FileSize(message = "file size must be max {bytes} bytes")
@FileFormat(format = {"jpeg", "png"}, message = "file format must be {format}")
public @interface ImageValidation {

  String message() default "Invalid image";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
