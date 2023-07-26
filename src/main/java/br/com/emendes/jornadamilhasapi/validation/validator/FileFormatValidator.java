package br.com.emendes.jornadamilhasapi.validation.validator;

import br.com.emendes.jornadamilhasapi.validation.annotation.FileFormat;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * ConstraintValidator de {@link FileFormat}.
 */
public class FileFormatValidator implements ConstraintValidator<FileFormat, MultipartFile> {

  private String[] formats;

  @Override
  public void initialize(FileFormat constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.formats = constraintAnnotation.format();
  }

  @Override
  public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
    if (value == null || value.getContentType() == null) return true;

    return isValidFormat(value.getContentType());
  }

  private boolean isValidFormat(String contentType) {
    for (String format : formats) {
      if (contentType.contains(format.toLowerCase())) return true;
    }

    return false;
  }

}
