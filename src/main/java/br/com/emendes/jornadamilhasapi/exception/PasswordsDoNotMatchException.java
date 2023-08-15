package br.com.emendes.jornadamilhasapi.exception;

/**
 * Exception a ser usada quando senhas não corresponderem.
 */
public class PasswordsDoNotMatchException extends RuntimeException {

  public PasswordsDoNotMatchException(String message) {
    super(message);
  }

}
