package br.com.emendes.jornadamilhasapi.mapper.impl;

import br.com.emendes.jornadamilhasapi.exception.FileAccessException;
import br.com.emendes.jornadamilhasapi.mapper.ImageMapper;
import br.com.emendes.jornadamilhasapi.model.Image;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

/**
 * Implementação de {@link ImageMapper}.
 */
@Component
public class ImageMapperImpl implements ImageMapper {

  @Override
  public Image toImage(MultipartFile destinationImage) {
    Assert.notNull(destinationImage, "DestinationImage must not be null");

    Binary content = toBinary(destinationImage);

    return Image.builder()
        .type(destinationImage.getContentType())
        .size(destinationImage.getSize())
        .content(content)
        .build();
  }

  @Override
  public URI toURI(Image image) {
    return URI.create(String.format("http://localhost:8080/api/images/%s", image.getId()));
  }

  @Override
  public void merge(Image image, MultipartFile destinationImage) {
    Binary content = toBinary(destinationImage);

    image.setContent(content);
    image.setSize(destinationImage.getSize());
  }

  /**
   * Converte MultipartFile para {@link Binary}.
   */
  private static Binary toBinary(MultipartFile destinationImage) {
    try {
      return new Binary(BsonBinarySubType.BINARY, destinationImage.getBytes());
    } catch (IOException ioException) {
      throw new FileAccessException("File access error");
    }
  }

}
