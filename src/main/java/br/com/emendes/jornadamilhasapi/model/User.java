package br.com.emendes.jornadamilhasapi.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa o documento doc_user no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(value = "doc_user")
public class User {

  @EqualsAndHashCode.Include
  @Id
  private String id;
  private String name;
  private String email;
  private String password;
  private URI image;
  @DocumentReference
  private Set<Authority> authorities;
  private LocalDateTime createdAt;

  public void addAuthority(Authority authority) {
    if (authorities == null) {
      authorities = new HashSet<>();
    }

    authorities.add(authority);
  }

}
