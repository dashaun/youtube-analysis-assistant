package io.dashaun.service.youtubeanalysisassistant;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.ai.client.AiClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.Duration;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Configuration
class YaaConfiguration {

	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		var socketTimeout = Duration.ofSeconds(120); // 30 seconds, adjust as needed

		return restTemplateBuilder
				.setReadTimeout(socketTimeout)   // Set the socket read timeout
				.setConnectTimeout(socketTimeout) // Set the socket connect timeout
				.build();
	}
}

@Component
class YaaClient {

	private final RestTemplate restTemplate;
	private final AiClient aiClient;
	
	private final String openaiApiKey;

	YaaClient(RestTemplate restTemplate, AiClient aiClient, @Value("${spring.ai.openai.api-key}")String openaiApiKey) {
        this.restTemplate = restTemplate;
        this.aiClient = aiClient;
		this.openaiApiKey = openaiApiKey;
    }


    String downloadTranscriptForUrl(URL url) {
		return null;
	}
	
	String createPrompt(String transcript) {
		return null;
	}
	
	URL generateThumbnail(String thumbnailDesign){
		return null;
	}

	private static final String API_URL = "https://api.openai.com/v1/images/generations";

	ImageGenerationResponse generateImage(String prompt, ImageSize imageSize) {
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + openaiApiKey);

		var request = new ImageGenerationRequest("dall-e-3", prompt, 1, imageSize.value());
		var entity = new HttpEntity<>(request, headers);
		var response = restTemplate.postForEntity(API_URL, entity, ImageGenerationResponse.class);
		return response.getBody();
	}
}

record ImageResponse(@JsonProperty("revised_prompt") String revisedPrompt, URL url) {
}

record ImageGenerationResponse(long created, List<ImageResponse> data) {
}


enum ImageSize {

	SIZE_1024x1024("1024x1024"),
	SIZE_1024x1792("1024x1792"),
	SIZE_1792x1024("1792x1024");

	private final String value;

	ImageSize(String s) {
		this.value = s;
	}

	String value() {
		return this.value;
	}
}

//1024x1024, 1024x1792 or 1792x1024 
record ImageGenerationRequest(String model, String prompt, int n, String size) {
	
}
