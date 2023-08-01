package br.com.emendes.jornadamilhasapi.service;

import br.com.emendes.jornadamilhasapi.service.dto.request.DestinationRequest;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationDetailsResponse;
import br.com.emendes.jornadamilhasapi.service.dto.response.DestinationSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Interface service com as abstrações para manipulação do recurso Destination.
 */
public interface DestinationService {

  /**
   * Salva um destino no sistema.
   *
   * @param destinationRequest DTO contendo as informações do destino a ser salvo.
   * @param destinationImages  Lista de files contendo imagens (jpg ou png) do destino.
   * @return {@link DestinationSummaryResponse} contendo informações do destino salvo.
   */
  DestinationDetailsResponse save(DestinationRequest destinationRequest, List<MultipartFile> destinationImages);

  /**
   * Busca páginada de Destinos..
   *
   * @param pageable contendo o número da página e a quantidade de elementos a ser buscado.
   * @return {@code Page<DestinationResponse>}
   */
  Page<DestinationSummaryResponse> fetch(Pageable pageable);

  /**
   * Busca páginada de Destinos por name.
   *
   * @param pageable contendo o número da página e a quantidade de elementos a ser buscado.
   * @param name     nome do destino a ser buscado.
   * @return {@code Page<DestinationResponse>}
   */
  Page<DestinationSummaryResponse> findByName(Pageable pageable, String name);

  /**
   * Busca um destino por id.
   *
   * @param destinationId identificador do destino.
   * @return {@link DestinationDetailsResponse} contendo as informações detalhas sobre o destino solicitado.
   */
  DestinationDetailsResponse findById(String destinationId);

  /**
   * Atualiza um destino por id.
   *
   * @param destinationId      identificador do Destino a ser atualizado.
   * @param destinationRequest contendo as novas informações do Destino.
   * @param destinationImage   arquivo contendo a nova  imagem do Destino.
   */
  void update(String destinationId, DestinationRequest destinationRequest);

  /**
   * Deleta um destination por id.
   *
   * @param destinationId identificador do Destination a ser deletado.
   */
  void delete(String destinationId);

}
