package br.com.emendes.jornadamilhasapi.mapper;

import br.com.emendes.jornadamilhasapi.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * Interface component com as abstrações de mapeamento entre {@link Image} e DTOs e vice-versa.
 */
public interface ImageMapper {

  /**
   * Mapeia MultipartFile para o document Image.<br>
   * destinationImage não deve ser null.
   *
   * @param destinationImage arquivo contendo a imagem.
   * @return {@link Image} contendo as informações que estavam no arquivo.
   */
  Image toImage(MultipartFile destinationImage);

  /**
   * Mapeia um document para URI que download da imagem.
   *
   * @param image a ser mapeada para URI.
   * @return URI de download da imagem.
   */
  URI toURI(Image image);

}
