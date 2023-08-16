package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

/**
 * Classe que representa o documento doc_authority no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value = "doc_authority")
public class Authority implements GrantedAuthority {

  @EqualsAndHashCode.Include
  @Id
  private String id;
  private String name;

  @Override
  public String getAuthority() {
    return this.name;
  }
}
