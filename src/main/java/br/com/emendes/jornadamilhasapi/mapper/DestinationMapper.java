package br.com.emendes.jornadamilhasapi.mapper;

import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;

/**
 * Interface component com as abstrações de mapeamento entre {@link Destination} e DTOs e vice-versa.
 */
public interface DestinationMapper {

  /**
   * Mapeia o DTO DestinationRequest para o document Destination.<br>
   * destinationRequest não deve ser null.
   *
   * @param destinationRequest que será mapeado para Destination.
   * @return {@link Destination} contendo as informações que estavam em detinationRequest.
   */
  Destination toDestination(DestinationRequest destinationRequest);

  /**
   * Mapeia o document Destination para o DTO {@link DestinationResponse}.
   * @param destination que será mapeado para DestinationResponse.
   * @return {@link DestinationResponse} contedo informações sobre Destination.
   */
  DestinationResponse toDestinationResponse(Destination destination);
}
