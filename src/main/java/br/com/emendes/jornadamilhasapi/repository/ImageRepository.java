package br.com.emendes.jornadamilhasapi.repository;

import br.com.emendes.jornadamilhasapi.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository interface para acessar o documento Image.
 */
public interface ImageRepository extends MongoRepository<Image, String> {
}
