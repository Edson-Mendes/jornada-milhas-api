package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.mapper.DestinationMapper;
import br.com.emendes.jornadamilhasapi.model.Destination;
import br.com.emendes.jornadamilhasapi.repository.DestinationRepository;
import br.com.emendes.jornadamilhasapi.service.DestinationService;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
  public DestinationDetailsResponse save(DestinationRequest destinationRequest, List<MultipartFile> destinationImages) {
    log.info("attempt to save destination");

    Destination destination = destinationMapper.toDestination(destinationRequest);

    destination.setImages(imageService.saveAll(destinationImages));
    destination.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    if (destination.getDescription() == null) {
      // TODO: Integrar com ChatGPT para ter uma descrição do lugar!
      destination.setDescription("Lorem ipsum dolor sit amet");
    }

    destinationRepository.save(destination);
    log.info("Destination with id: {} saved successful", destination.getId());

    return destinationMapper.toDestinationDetailsResponse(destination);
  }

  @Override
  public Page<DestinationSummaryResponse> fetch(Pageable pageable) {
    log.info("fetching page: {} and size: {} of Destinations.", pageable.getPageNumber(), pageable.getPageSize());

    return destinationRepository.findAll(pageable).map(destinationMapper::toDestinationSummaryResponse);
  }

  @Override
  public Page<DestinationSummaryResponse> findByName(Pageable pageable, String name) {
    log.info(
        "fetching page: {} and size: {} of Destinations with name: {}.",
        pageable.getPageNumber(), pageable.getPageSize(), name);

    String regexName = String.format(".*%s.*", name);

    Page<Destination> destinationPage = destinationRepository.findByName(pageable, regexName);

    if (destinationPage.getTotalElements() == 0) {
      throw new ResourceNotFoundException(String.format("Destinations with name containing {%s} not found", name));
    }

    return destinationPage.map(destinationMapper::toDestinationSummaryResponse);
  }

  @Override
  public DestinationDetailsResponse findById(String destinationId) {
    log.info("attempt to fetch destination with id: {}", destinationId);

    Destination destination = findDestinationById(destinationId);

    log.info("destination found successful with id: {}", destinationId);
    return destinationMapper.toDestinationDetailsResponse(destination);
  }

  @Override
  public void update(String destinationId, DestinationRequest destinationRequest) {
    log.info("attempt to update destination with id: {}", destinationId);

    Destination destination = findDestinationById(destinationId);

    destinationMapper.merge(destination, destinationRequest);

    if (destination.getDescription() == null) {
      // TODO: Integrar com ChatGPT para ter uma descrição do lugar!
      destination.setDescription("Lorem ipsum dolor sit amet");
    }

    destinationRepository.save(destination);
    log.info("destination updated successful with id: {}", destinationId);
  }

  @Override
  public void updateImage(String destinationId, String imageId, MultipartFile image) {
    log.info("attempt to update image with id: {}", imageId);
    Destination destination = findDestinationById(destinationId);

    List<String> imageIdList = destination.retrieveImagesId();

    if (!imageIdList.contains(imageId)) {
      throw new ResourceNotFoundException("Image not found");
    }

    imageService.update(imageId, image);
    log.info("image with id: {} updated successful", imageId);
  }

  @Override
  public void delete(String destinationId) {
    log.info("attempt to delete destination with id: {}", destinationId);

    Destination destination = findDestinationById(destinationId);

    String imageId = destination.retrieveImageId();
    imageService.delete(imageId);
    destinationRepository.delete(destination);

    log.info("destination with id: {} deleted successful", destinationId);
  }

  /**
   * Busca Destination por id.
   *
   * @param destinationId identificador do Destination.
   * @return Destination para o dado id.
   * @throws ResourceNotFoundException caso não seja encontrado Destination para o dado destinationId.
   */
  private Destination findDestinationById(String destinationId) {
    return destinationRepository.findById(destinationId).orElseThrow(() -> {
      log.info("destination not found for id: {}", destinationId);
      return new ResourceNotFoundException("Destination not found");
    });
  }

}
