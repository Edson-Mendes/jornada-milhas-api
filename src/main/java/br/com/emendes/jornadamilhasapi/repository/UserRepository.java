package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface para acessar o documento User.
 */
public interface UserRepository extends MongoRepository<User, String> {

  /**
   * Verifica se existe algum user com o dado email.
   *
   * @param email a ser verificado.
   * @return true caso exista algum user com o dado email, false caso contrário.
   */
  boolean existsByEmail(String email);

}
