package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

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
  private String meta;
  private String description;
  private List<URI> images;
  private LocalDateTime createdAt;

  /**
   * Extrai o id da imagem da URI images.
   *
   * @return identificador da imagem o qual images está associado.
   */
  public String retrieveImageId() {
    if (images == null || images.isEmpty()) {
      throw new RuntimeException("has no images");
    }
    String[] paths = images.get(0).getPath().split("/");

    return paths[paths.length - 1];
  }

}
