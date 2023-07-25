package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.mapper.DestinationMapper;
import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.repository.DestinationRepository;
import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação de {@link DestinationService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DestinationServiceImpl implements DestinationService {

  private final DestinationMapper destinationMapper;
  private final ImageService imageService;
  private final DestinationRepository destinationRepository;

  @Override
  public DestinationResponse save(DestinationRequest destinationRequest, MultipartFile destinationImage) {
    log.info("attempt to save destination");

    Destination destination = destinationMapper.toDestination(destinationRequest);

    destination.setUrlImage(imageService.save(destinationImage));
    destination.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    destinationRepository.save(destination);
    log.info("Destination with id: {} saved successful", destination.getId());

    return destinationMapper.toDestinationResponse(destination);
  }

}
