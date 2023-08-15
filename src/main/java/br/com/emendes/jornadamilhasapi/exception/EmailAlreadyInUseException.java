package br.com.emendes.jornadamilhasapi.exception;

/**
 * Exception a ser lançada quando email já está em uso no sistema.
 */
public class EmailAlreadyInUseException extends RuntimeException {

  public EmailAlreadyInUseException(String message) {
    super(message);
  }

}
