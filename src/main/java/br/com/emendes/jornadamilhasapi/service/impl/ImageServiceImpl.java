package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.ResourceNotFoundException;
import br.com.emendes.jornadamilhasapi.mapper.ImageMapper;
import br.com.emendes.jornadamilhasapi.model.Image;
import br.com.emendes.jornadamilhasapi.repository.ImageRepository;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * Implementação de {@link ImageService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

  private final ImageMapper imageMapper;
  private final ImageRepository imageRepository;

  /**
   * Tamanho máximo da imagem, em bytes.
   */
  private static final long FILE_SIZE = 10_485_760L;

  @Override
  public URI save(MultipartFile destinationImage) {
    log.info("attempt to save image");

    Image image = imageMapper.toImage(destinationImage);
    imageRepository.save(image);

    log.info("image saved successful");
    return imageMapper.toURI(image);
  }

  @Override
  public Resource findById(String imageId) {
    log.info("attempt to fetch image with id: {}", imageId);

    Image image = imageRepository.findById(imageId)
        .orElseThrow(() -> {
          log.info("image not found for id: {}", imageId);
          return new ResourceNotFoundException("Image not found");
        });

    log.info("image with id: {} found successful", imageId);
    return new ByteArrayResource(image.getContent().getData());
  }

  @Override
  public void delete(String imageId) {
    log.info("attempt to delete image with id: {}", imageId);
    imageRepository.deleteById(imageId);
  }

}
