package br.com.emendes.jornadamilhasapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;

/**
 * Configuração de OpenAPI.
 */
@Configuration
public class OpenAPIConfig {

  public static final String API_TITLE = "Jornada Milhas API";

  /**
   * Adicionei isso por que o Swagger UI não permite definir o contentType para parts em requests multipart/form-data
   * ou seja, em requests com files e json no request body, o json acaba sendo enviado como application/octet-stream,
   * o que acaba dando erro 415 (Unsupported Media Type).
   * <p>
   * O código abaixo adiciona o mediaType application/octet-stream na lista suportada pelo Jackson.
   * Assim não ocorre mais problemas com o Swagger UI.
   */
  public OpenAPIConfig(MappingJackson2HttpMessageConverter converter) {
    var supportedMediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
    supportedMediaTypes.add(new MediaType("application", "octet-stream"));
    converter.setSupportedMediaTypes(supportedMediaTypes);
  }

  @Bean
  public OpenAPI openAPI() {
    Contact contact = new Contact();
    contact.name("Edson Mendes").email("edson.luiz.mendes@hotmail.com").url("https://github.com/Edson-Mendes");

    String description = "Tourism platform REST API, where the client can search and view destinations, average trip price, statements from other travelers and much more.";

    return new OpenAPI()
        .info(new Info().title(API_TITLE)
            .description(description)
            .version("v0.3").contact(contact));
  }

}
