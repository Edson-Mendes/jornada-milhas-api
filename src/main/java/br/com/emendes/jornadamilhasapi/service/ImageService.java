package br.com.emendes.jornadamilhasapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

/**
 * Interface service com as abstrações para manipulação do recurso Image.
 */
public interface ImageService {

  /**
   * Salva uma imagem no sistema.
   *
   * @param destinationImage file contendo a imagem a ser salva.
   * @return url usada para recuperar a imagem salva.
   */
  URI save(MultipartFile destinationImage);

}
