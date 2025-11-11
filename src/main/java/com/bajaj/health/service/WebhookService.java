package com.bajaj.health.service;

import com.bajaj.health.model.WebhookResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate = new RestTemplate();

    // Step 1: Generate webhook
    public WebhookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, Object> body = new HashMap<>();
        body.put("name", "John Doe");          // 🔹 Replace with your name
        body.put("regNo", "REG12347");         // 🔹 Replace with your reg no
        body.put("email", "john@example.com"); // 🔹 Replace with your email

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        ResponseEntity<WebhookResponse> response =
                restTemplate.postForEntity(url, request, WebhookResponse.class);

        return response.getBody();
    }

    // Step 3: Send the final SQL query using accessToken
    public void sendFinalQuery(String token, String finalQuery) {
        String apiUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); // sets "Authorization: Bearer <token>"

        Map<String, String> payload = new HashMap<>();
        payload.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, request, String.class);
        System.out.println("Server Response: " + response.getBody());
    }
}
