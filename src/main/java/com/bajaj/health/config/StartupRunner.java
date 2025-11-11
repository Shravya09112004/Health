package com.bajaj.health.config;

import com.bajaj.health.model.WebhookResponse;
import com.bajaj.health.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(String... args) {
        System.out.println("🚀 Starting Bajaj Finserv Health Project...");

        // Step 1: Get webhook and token
        WebhookResponse response = webhookService.generateWebhook();

        if (response == null) {
            System.out.println("❌ Failed to generate webhook. Check internet connection.");
            return;
        }

        System.out.println("✅ Webhook URL: " + response.getWebhook());
        System.out.println("🔑 Access Token: " + response.getAccessToken());

        // Step 2: Add your SQL query below
        String finalQuery = "SELECT department, COUNT(*) FROM employees GROUP BY department;"; // Replace with your solution

        // Step 3: Send query
        webhookService.sendFinalQuery(response.getAccessToken(), finalQuery);
        System.out.println("📤 SQL query sent successfully!");
    }
}
