package br.com.emendes.jornadamilhasapi.util.faker;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Classe utilitária com métodos que devolvem objetos usados em testes.
 */
public class ImageFaker {

  private ImageFaker() {
  }

  public static Resource resource() {
    try (InputStream image = new FileInputStream("src/test/resources/image/veneza01.jpg")) {
      return new ByteArrayResource(image.readAllBytes());
    } catch (IOException ioException) {
      throw new RuntimeException("Can not read file");
    }
  }
}
