package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Classe que representa o documento doc_statement no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value = "doc_statement")
public class Statement {

  @EqualsAndHashCode.Include
  @Id
  private String id;
  private String username;
  private String text;
  private String urlImage;
  private LocalDateTime createdAt;

}
