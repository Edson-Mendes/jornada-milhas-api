package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.mapper.DestinationMapper;
import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Implementação de {@link DestinationMapper}.
 */
@Component
public class DestinationMapperImpl implements DestinationMapper {

  @Override
  public Destination toDestination(DestinationRequest destinationRequest) {
    Assert.notNull(destinationRequest, "DestinationRequest must not be null");

    return Destination.builder()
        .name(destinationRequest.name())
        .meta(destinationRequest.meta())
        .description(destinationRequest.description())
        .price(destinationRequest.price())
        .build();
  }

  @Override
  public DestinationResponse toDestinationResponse(Destination destination) {
    Assert.notNull(destination, "Destination must not be null");

    return DestinationResponse.builder()
        .id(destination.getId())
        .name(destination.getName())
        .price(destination.getPrice())
        .meta(destination.getMeta())
        .description(destination.getDescription())
        .images(destination.getImages())
        .createdAt(destination.getCreatedAt())
        .build();
  }

  @Override
  public void merge(Destination destination, DestinationRequest destinationRequest) {
    // TODO: deve ser atualizado!
    Assert.notNull(destination, "destination must not be null");
    Assert.notNull(destinationRequest, "destinationRequest must not be null");

    destination.setName(destinationRequest.name());
    destination.setPrice(destinationRequest.price());
  }

}
