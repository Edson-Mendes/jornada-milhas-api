package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.exception.ImageNotValidException;
import br.com.emendes.jornadamilhasapi.mapper.ImageMapper;
import br.com.emendes.jornadamilhasapi.model.Image;
import br.com.emendes.jornadamilhasapi.repository.ImageRepository;
import br.com.emendes.jornadamilhasapi.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Objects;

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

    if (!isFileValid(destinationImage)) {
      throw new ImageNotValidException("Imagem inválida");
    }

    Image image = imageMapper.toImage(destinationImage);
    imageRepository.save(image);

    log.info("image saved successful");
    return imageMapper.toURI(image);
  }

  private boolean isFileValid(MultipartFile file) {
    return !file.isEmpty() && isValidSize(file) && (isJPEG(file)
        || isPNG(file));
  }

  private boolean isJPEG(MultipartFile file) {
    return Objects.equals(file.getContentType(), MediaType.IMAGE_JPEG_VALUE);
  }

  private boolean isPNG(MultipartFile file) {
    return Objects.equals(file.getContentType(), MediaType.IMAGE_PNG_VALUE);
  }

  private boolean isValidSize(MultipartFile file) {
    return file.getSize() > 0 && file.getSize() <= FILE_SIZE;
  }

}
