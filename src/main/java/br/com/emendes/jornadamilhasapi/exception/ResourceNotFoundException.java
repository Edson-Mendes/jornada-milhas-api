package br.com.emendes.jornadamilhasapi.exception;

/**
 * Exception a ser usada quando um recurso não é encontrado.
 */
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message) {
    super(message);
  }

}
