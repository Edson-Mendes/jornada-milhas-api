package br.com.emendes.jornadamilhasapi.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

}
