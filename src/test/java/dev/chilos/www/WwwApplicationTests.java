package dev.chilos.www;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.lang.InterruptedException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WwwApplicationTests {

    @LocalServerPort
    private int port; // The randomly assigned port

    @Test
    void indexPage() throws InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + port))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "text/html")
                .GET()
                .build();

        CompletableFuture<String> fut = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(String::toString);

        String res = fut.get();
        assert(res.contains("I am David."));
    }

}
