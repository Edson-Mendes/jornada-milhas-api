package br.com.emendes.jornadamilhasapi.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de {@link OpenAiService}.
 */
@Configuration
public class OpenAIConfig {

  @Value("${jornadamilhas.openai.token}")
  private String openAiToken;

  @Bean
  public OpenAiService openAiService() {
    return new OpenAiService(openAiToken);
  }

}
