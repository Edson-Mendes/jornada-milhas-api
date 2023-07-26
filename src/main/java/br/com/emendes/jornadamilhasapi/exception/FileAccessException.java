package br.com.emendes.jornadamilhasapi.exception;

/**
 * Exception a ser usada quando não conseguir obter os bytes de um arquivo.
 */
public class FileAccessException extends RuntimeException {

  public FileAccessException(String message) {
    super(message);
  }

}
