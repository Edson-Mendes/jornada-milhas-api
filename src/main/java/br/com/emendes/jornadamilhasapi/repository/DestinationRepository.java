package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface para acessar o documento Destination.
 */
public interface DestinationRepository extends MongoRepository<Destination, String> {
}
