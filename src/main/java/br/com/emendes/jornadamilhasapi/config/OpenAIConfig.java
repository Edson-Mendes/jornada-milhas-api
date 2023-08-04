package br.com.emendes.jornadamilhasapi.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;

/**
 * Configuração de {@link OpenAiService}.
 */
@Configuration
public class OpenAIConfig {

  @Value("${jornadamilhas.openai.token}")
  private String openAiToken;

  /**
   * Adicionei isso por que o Swagger não permite definir o contentType para parts em requests multipart/form-data
   * ou seja, em requests com files e json no request body, o json acaba sendo enviado como application/octet-stream,
   * o que acaba dando erro 415 (Unsupported Media Type).
   * <p>
   * O código abaixo adiciona o mediaType application/octet-stream na lista suportada pelo Jackson.
   * Assim não ocorre mais problemas com o Swagger.
   */
  public OpenAIConfig(MappingJackson2HttpMessageConverter converter) {
    var supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
    supportedMediaTypes.add(new MediaType("application", "octet-stream"));
    converter.setSupportedMediaTypes(supportedMediaTypes);
  }

  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(openAiToken);
  }

}
