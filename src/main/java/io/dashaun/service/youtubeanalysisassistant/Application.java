package io.dashaun.service.youtubeanalysisassistant;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.time.Duration;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

@Configuration
class Config{
	@Bean
	RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		Duration socketTimeout = Duration.ofSeconds(120); // 30 seconds, adjust as needed

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

	String generateImage(String prompt) {
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + openaiApiKey);

		var request = new ImageGenerationRequest("dall-e-3",prompt, 1, "1024x1024");
		var entity = new HttpEntity<>(request, headers);
		var response = restTemplate.postForEntity(API_URL, entity, String.class);
		return response.getBody();
	}
}
//public class ImageGenerationRequest {
//	private String model;
//	private String prompt;
//	private int n;
//	private String size;
//
//	// Constructors, getters and setters
//}

//1024x1024, 1024x1792 or 1792x1024 
record ImageGenerationRequest(String model, String prompt, int n, String size) {
	
}
