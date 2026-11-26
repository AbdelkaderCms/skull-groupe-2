package fr.miage.skull.services.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public final class ApiClient {
    private final String base;
    private final HttpClient http = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(4)).build();
    final ObjectMapper json = new ObjectMapper();

    public ApiClient(String base) {
        this.base = base.replaceAll("/+$", "");
    }

    static String pseudo(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    String request(String path, String body, int expected) throws Exception {
        var b = HttpRequest.newBuilder(URI.create(base + path)).timeout(Duration.ofSeconds(8)).header("Accept", "application/json");
        if (body == null) b.GET();
        else b.header("Content-Type", "application/json").POST(HttpRequest.BodyPublishers.ofString(body));
        var response = http.send(b.build(), HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != expected)
            throw new IllegalStateException("Réponse HTTP " + response.statusCode() + " (attendu : " + expected + ")");
        return response.body();
    }
}
