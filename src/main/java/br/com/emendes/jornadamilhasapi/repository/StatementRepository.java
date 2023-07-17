package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface para acessar o documento Statement.
 */
public interface StatementRepository extends MongoRepository<Statement, String> {
}
