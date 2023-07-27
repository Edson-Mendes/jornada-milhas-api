package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Repository interface para acessar o documento Destination.
 */
public interface DestinationRepository extends MongoRepository<Destination, String> {

  /**
   * Busca paginada de Destination por name que corresponda a expressão regular regexName.
   *
   * @param pageable  contendo o número da página e a quantidade de elementos a ser buscado.
   * @param regexName regex o qual name deve corresponder.
   * @return {@code Page<Destination>}
   */
  @Query(value = "{'name': {$regex : ?0, $options: 'i'}}")
  Page<Destination> findByName(Pageable pageable, String regexName);

}
