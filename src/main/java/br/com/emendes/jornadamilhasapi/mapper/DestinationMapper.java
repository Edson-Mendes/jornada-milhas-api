package br.com.emendes.jornadamilhasapi.mapper;

import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;

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
   * Mapeia o document Destination para o DTO {@link DestinationSummaryResponse}.
   *
   * @param destination que será mapeado para DestinationResponse.
   * @return {@link DestinationSummaryResponse} contedo informações sobre Destination.
   */
  DestinationSummaryResponse toDestinationSummaryResponse(Destination destination);

  /**
   * Mapeia o document Destination para o DTO {@link DestinationDetailsResponse}.
   *
   * @param destination que será mapeado para DestinationDetailsResponse.
   * @return {@link DestinationDetailsResponse} contedo informações sobre Destination.
   */
  DestinationDetailsResponse toDestinationDetailsResponse(Destination destination);

  /**
   * Mescla as informações dentro de destination com as informações contidas em destinationRequest.
   *
   * @param destination        que receberá as novas informações.
   * @param destinationRequest que contém as novas informações.
   */
  void merge(Destination destination, DestinationRequest destinationRequest);

}
