package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface para acessar o documento User.
 */
public interface UserRepository extends MongoRepository<User, String> {
}
