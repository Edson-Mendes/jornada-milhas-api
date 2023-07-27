package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Interface service com as abstrações para manipulação do recurso Destination.
 */
public interface DestinationService {

  /**
   * Salva um destino no sistema.
   *
   * @param destinationRequest DTO contendo as informações do destino a ser salvo.
   * @param destinationImage   file contendo uma imagem (jpg ou png) do destino.
   * @return {@link DestinationResponse} contendo informações do destino salvo.
   */
  DestinationResponse save(DestinationRequest destinationRequest, MultipartFile destinationImage);

  /**
   * Busca um destino por id.
   *
   * @param destinationId identificador do destino.
   * @return {@link DestinationResponse} contendo as informações do destino solicitado.
   */
  DestinationResponse findById(String destinationId);

}
