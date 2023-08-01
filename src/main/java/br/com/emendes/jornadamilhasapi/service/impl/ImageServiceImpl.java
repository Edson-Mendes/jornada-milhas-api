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
import java.util.List;

/**
 * Implementação de {@link ImageService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

  private final ImageMapper imageMapper;
  private final ImageRepository imageRepository;

  @Override
  public List<URI> saveAll(List<MultipartFile> files) {
    log.info("attempt to save images");

    List<Image> images = files.stream().map(imageMapper::toImage).toList();
    imageRepository.saveAll(images);

    log.info("images saved successful");
    return images.stream().map(imageMapper::toURI).toList();
  }

  @Override
  public Resource findById(String imageId) {
    log.info("attempt to fetch image with id: {}", imageId);

    Image image = findImageById(imageId);

    log.info("image with id: {} found successful", imageId);
    return new ByteArrayResource(image.getContent().getData());
  }

  @Override
  public void update(String imageId, MultipartFile file) {
    Image image = findImageById(imageId);

    imageMapper.merge(image, file);
    imageRepository.save(image);
  }

  @Override
  public void deleteAll(List<String> imagesId) {
    log.info("attempt to delete images with id: {}", imagesId);
    imageRepository.deleteAllById(imagesId);
  }

  /**
   * Busca imagem por id.
   *
   * @param imageId identificador da imagem.
   * @return {@code Image}
   * @throws ResourceNotFoundException caso a imagem não seja encontrada.
   */
  private Image findImageById(String imageId) {
    return imageRepository.findById(imageId)
        .orElseThrow(() -> {
          log.info("image not found for id: {}", imageId);
          return new ResourceNotFoundException("Image not found");
        });
  }

}
