package com.fiap.mspedidoapi.infra.dependecy;

import com.fiap.mspedidoapi.domain.gateway.dependency.HttpAdapterInterface;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class HttpAdapter implements HttpAdapterInterface {
    private final HttpClient httpClient;

    // Construtor padrão
    public HttpAdapter() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Construtor para injeção de dependência
    public HttpAdapter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String get(String url, Map<String, String> headers) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET();

        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Check response status code
        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        } else {
            throw new RuntimeException("HTTP request failed with status code: " + statusCode);
        }
    }

    public String post(String url, String requestBody, Map<String, String> headers) throws Exception {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody));

        if (headers != null) {
            headers.forEach(requestBuilder::header);
        }

        HttpRequest request = requestBuilder.build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        int statusCode = response.statusCode();
        if (statusCode >= 200 && statusCode < 300) {
            return response.body();
        } else {
            throw new RuntimeException("HTTP request failed with status code: " + statusCode);
        }
    }
}

