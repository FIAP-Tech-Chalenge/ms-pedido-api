package com.fiap.mspedidoapi.infra.dependecy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HttpAdapterTest {

    private HttpAdapter httpAdapter;
    private HttpClient httpClient;

    @BeforeEach
    public void setUp() {
        httpClient = mock(HttpClient.class);
        httpAdapter = new HttpAdapter(httpClient); // Injeta o HttpClient mockado
    }

    @Test
    public void deveRealizarGetComSucesso() throws Exception {
        String url = "http://example.com";
        String expectedResponse = "response body";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(expectedResponse);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        String response = httpAdapter.get(url, null);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void deveLancarExcecaoQuandoGetFalhar() throws Exception {
        String url = "http://example.com";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(500);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            httpAdapter.get(url, null);
        });

        assertThat(exception.getMessage()).isEqualTo("HTTP request failed with status code: 500");
    }

    @Test
    public void deveRealizarPostComSucesso() throws Exception {
        String url = "http://example.com";
        String requestBody = "request body";
        String expectedResponse = "response body";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(expectedResponse);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        String response = httpAdapter.post(url, requestBody, null);

        assertThat(response).isEqualTo(expectedResponse);
    }

    @Test
    public void deveLancarExcecaoQuandoPostFalhar() throws Exception {
        String url = "http://example.com";
        String requestBody = "request body";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> httpResponse = mock(HttpResponse.class);
        when(httpResponse.statusCode()).thenReturn(500);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            httpAdapter.post(url, requestBody, null);
        });

        assertThat(exception.getMessage()).isEqualTo("HTTP request failed with status code: 500");
    }
}
