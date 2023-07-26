package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Classe que representa o documento doc_image no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value = "doc_image")
public class Image {

  @EqualsAndHashCode.Include
  @Id
  private String id;
  private long size;
  private String type;
  private Binary content;
}
