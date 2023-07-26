package br.com.emendes.jornadamilhasapi.validation.validator;

import br.com.emendes.jornadamilhasapi.validation.annotation.FileSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * ConstraintValidator de {@link FileSize}.
 */
public class FileSizeValidator implements ConstraintValidator<FileSize, MultipartFile> {

  private long fileSize;

  @Override
  public void initialize(FileSize constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
    this.fileSize = constraintAnnotation.bytes();
  }

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (file == null) return true;
    return file.getSize() > 0 && file.getSize() <= fileSize;
  }

}
