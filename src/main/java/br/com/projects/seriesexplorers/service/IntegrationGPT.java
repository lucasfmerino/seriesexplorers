package br.com.projects.seriesexplorers.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import br.com.projects.seriesexplorers.utils.Params;

public class IntegrationGPT {

    private static final String GPT_KEY = Params.gptKey;

    public static String translate(String plot) {
        OpenAiService service = new OpenAiService(GPT_KEY);

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .prompt("traduza para o português(brasileiro) o texto: " + plot)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(request);
        return response.getChoices().get(0).getText();
    }
}
