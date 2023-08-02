package br.com.emendes.jornadamilhasapi.service.impl;

import br.com.emendes.jornadamilhasapi.service.ChatGPTService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link ChatGPTService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ChatGPTServiceImpl implements ChatGPTService {

  public static final String MODEL_ID = "text-davinci-003";
  private static final String PROMPT_TEMPLATE =
      "Faça um resumo sobre %s enfatizando o porque este lugar é incrível. Utilize uma linguagem informal e até 200 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.";

  private final OpenAiService openAiService;

  @Override
  public String fetchDestinationDescription(String destinationName) {
    if (destinationName == null || destinationName.isBlank()) {
      throw new IllegalArgumentException("destinationName must not be null or blank");
    }
    log.info("attempt to fetch description about {}", destinationName);

    CompletionRequest completionRequest = CompletionRequest.builder()
        .model(MODEL_ID)
        .prompt(String.format(PROMPT_TEMPLATE, destinationName))
        .maxTokens(1000)
        .build();

    CompletionResult completionResult = openAiService.createCompletion(completionRequest);

    return completionResult.getChoices().get(0).getText();
  }

}
