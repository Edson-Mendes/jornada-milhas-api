package br.com.emendes.jornadamilhasapi.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

/**
 * Interface service com as abstrações para manipulação do recurso Image.
 */
public interface ImageService {

  /**
   * Salva uma imagem no sistema.
   *
   * @param images List de files contendo a imagens a serem salvas.
   * @return {@code List<URI>} urls das imagens salvas.
   */
  List<URI> saveAll(List<MultipartFile> images);

  /**
   * Busca imagem por id.
   *
   * @param imageId identificador da imagem.
   * @return Resource com a imagem buscada.
   */
  Resource findById(String imageId);

  /**
   * Atualiza imagem por id.
   *
   * @param imageId identificador da imagem a ser atualizada.
   * @param image   arquivo contendo a nova imagem.
   */
  void update(String imageId, MultipartFile image);

  /**
   * Deleta imagens por id.
   *
   * @param imagesId Lista de identificadores das imagens a serem deletadas.
   */
  void deleteAll(List<String> imagesId);

}
