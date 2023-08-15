package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.Authority;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository interface para acessar o documento Authority.
 */
public interface AuthorityRepository extends MongoRepository<Authority, String> {

  /**
   * Busca uma Authority por nome.
   */
  Optional<Authority> findByName(String authorityName);

}
