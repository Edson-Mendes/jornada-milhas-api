package br.com.emendes.jornadamilhasapi.service;

/**
 * Interface service com as abstrações para interação com o ChatGPT.
 */
public interface ChatGPTService {

  /**
   * Consulta o ChatGPT para fazer um resumo/descrição do destino informado.
   *
   * @param destinationName nome do destino.
   * @return uma resumo do destino informado.
   */
  String fetchDestinationDescription(String destinationName);

}
