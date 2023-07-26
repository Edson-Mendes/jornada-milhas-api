package br.com.emendes.jornadamilhasapi.handler;

import br.com.emendes.jornadamilhasapi.exception.FileAccessException;
import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

/**
 * Handler responsável por tratar exception que ocorrerem a partir da camada controller.
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Trata {@link MethodArgumentNotValidException}.
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "Invalid field(s)");

    String fields = ex.getFieldErrors().stream()
        .map(FieldError::getField)
        .collect(Collectors.joining(","));
    String errorMessages = ex.getFieldErrors().stream()
        .map(FieldError::getDefaultMessage)
        .collect(Collectors.joining(","));

    URI uri = URI.create("http://non-implemented.com/invalid-fields");
    problemDetail.setType(uri);
    problemDetail.setProperty("fields", fields);
    problemDetail.setProperty("errorMessages", errorMessages);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  /**
   * Trata {@link ResourceNotFoundException}.
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFoundException ex) {
    HttpStatusCode status = HttpStatusCode.valueOf(404);
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());

    URI uri = URI.create("http://non-implemented.com/resource-not-found");
    problemDetail.setType(uri);

    return ResponseEntity.status(status).body(problemDetail);
  }

  /**
   * Trata {@link ConstraintViolationException}.
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ProblemDetail> handleConstraintViolationException(ConstraintViolationException ex) {
    HttpStatusCode status = HttpStatusCode.valueOf(400);
    String details = ex.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage)
        .collect(Collectors.joining(","));

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, details);

    URI uri = URI.create("http://non-implemented.com/invalid-param");
    problemDetail.setType(uri);

    return ResponseEntity.status(status).body(problemDetail);
  }

  /**
   * Trata {@link FileAccessException}.
   */
  @ExceptionHandler(FileAccessException.class)
  public ResponseEntity<ProblemDetail> handleFileAccess(FileAccessException ex) {
    HttpStatusCode status = HttpStatusCode.valueOf(500);

    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, ex.getMessage());

    URI uri = URI.create("http://non-implemented.com/file-access");
    problemDetail.setType(uri);

    return ResponseEntity.status(status).body(problemDetail);
  }

}
