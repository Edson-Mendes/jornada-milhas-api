package br.com.emendes.jornadamilhasapi.exception;

/**
 * Exception a ser usada quando uma imagem for inválida.
 */
public class ImageNotValidException extends RuntimeException {
  public ImageNotValidException(String message) {
    super(message);
  }
}
