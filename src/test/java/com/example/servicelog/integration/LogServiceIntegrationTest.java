package com.example.servicelog.integration;

import com.example.servicelog.dto.ShuffleRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.example.servicelog.util.Constants.LOG_ENDPOINT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class LogServiceIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testLogService() {
        int number = 5;
        ShuffleRequest request = new ShuffleRequest();
        request.setNumber(number);

        webTestClient.post()
                .uri(LOG_ENDPOINT)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }

    @Test
    public void testLogServiceInvalidNumber() {
        int invalidNumber = 0;
        ShuffleRequest request = new ShuffleRequest();
        request.setNumber(invalidNumber);

        webTestClient.post()
                .uri(LOG_ENDPOINT)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest();
    }
}
