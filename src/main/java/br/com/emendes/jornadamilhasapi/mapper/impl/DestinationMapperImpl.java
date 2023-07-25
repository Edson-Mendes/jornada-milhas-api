package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.mapper.DestinationMapper;
import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import org.springframework.stereotype.Component;

/**
 * Implementação de {@link DestinationMapper}.
 */
@Component
public class DestinationMapperImpl implements DestinationMapper {

  @Override
  public Destination toDestination(DestinationRequest destinationRequest) {
    // TODO: Implementar.
    return null;
  }

  @Override
  public DestinationResponse toDestinationResponse(Destination destination) {
    // TODO: Implementar.
    return null;
  }

}
