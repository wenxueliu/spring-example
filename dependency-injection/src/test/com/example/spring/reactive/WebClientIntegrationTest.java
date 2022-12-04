package com.example.spring.reactive;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.After;
import org.junit.Test;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.Duration;
import java.util.function.Consumer;

/**
 * @author liuwenxue
 * @date 2022-11-19
 */
class WebClientIntegrationTest {

    private MockWebServer server;

    private WebClient webClient;

    private void startServer() {
        this.server = new MockWebServer();
        this.server.enqueue(new MockResponse().setBody("ok"));
        this.webClient = WebClient
                .builder()
                .baseUrl(this.server.url("/").toString())
                .build();
    }

    private void startServer(ClientHttpConnector connector) {
        this.server = new MockWebServer();
        this.server.enqueue(new MockResponse().setBody("ok"));
        this.webClient = WebClient
                .builder()
                .clientConnector(connector)
                .baseUrl(this.server.url("/").toString())
                .build();
    }

    @After
    void shutdown() throws IOException {
        if (server != null) {
            this.server.shutdown();
        }
    }

    private void prepareResponse(Consumer<MockResponse> consumer) {
        MockResponse response = new MockResponse();
        consumer.accept(response);
        this.server.enqueue(response);
    }

    private void expectRequest(Consumer<RecordedRequest> consumer) {
        try {
            consumer.accept(this.server.takeRequest());
        }
        catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }


    @Test
    public void retrieve() {
        startServer();
        prepareResponse(response ->
                response.setHeader("Content-Type", "text/plain")
                        .setBody("Hello Spring!"));
        Mono<String> result = this.webClient.get()
                .uri("/greeting")
                .cookie("testkey", "testvalue")
                .header("X-Test-Header", "testvalue")
                .retrieve()
                .bodyToMono(String.class);

        StepVerifier.create(result)
                .expectNext("Hello Spring!")
                .expectComplete()
                .verify(Duration.ofSeconds(3));
    }

    public static void main(String[] args) {
        new WebClientIntegrationTest().retrieve();

    }
}
