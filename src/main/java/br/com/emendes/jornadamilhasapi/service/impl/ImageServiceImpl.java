package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * Implementação de {@link ImageService}.
 */
@Service
public class ImageServiceImpl implements ImageService {

  @Override
  public URI save(MultipartFile destinationImage) {
    // TODO: Implementar.
    return null;
  }

}
