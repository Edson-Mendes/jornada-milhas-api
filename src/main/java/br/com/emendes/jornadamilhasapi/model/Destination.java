package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;

/**
 * Classe que representa o documento doc_destination no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value = "doc_destination")
public class Destination {

  @EqualsAndHashCode.Include
  @Id
  private String id;
  private String name;
  private BigDecimal price;
  private URI urlImage;
  private LocalDateTime createdAt;

  /**
   * Extrai o id da imagem da URI urlImage.
   *
   * @return identificador da imagem o qual urlImage está associado.
   */
  public String retrieveImageId() {
    String[] paths = urlImage.getPath().split("/");

    return paths[paths.length - 1];
  }

}
