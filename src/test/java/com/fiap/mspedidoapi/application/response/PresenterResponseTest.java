package com.fiap.mspedidoapi.application.response;

import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.generic.presenter.PresenterInterface;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class PresenterResponseTest {

    private PresenterResponse presenterResponse;
    private PresenterInterface presenterInterface;

    @BeforeEach
    public void setUp() {
        presenterResponse = new PresenterResponse();
        presenterInterface = Mockito.mock(PresenterInterface.class);
    }

    @Test
    public void deveRetornarStatusOk() {
        OutputStatus outputStatus = new OutputStatus(200, "OK", "Success");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusCreated() {
        OutputStatus outputStatus = new OutputStatus(201, "CREATED", "Created");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusNotFound() {
        OutputStatus outputStatus = new OutputStatus(404, "NOT_FOUND", "Not Found");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusNoContent() {
        OutputStatus outputStatus = new OutputStatus(204, "NO_CONTENT", "No Content");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusUnprocessableEntity() {
        OutputStatus outputStatus = new OutputStatus(422, "UNPROCESSABLE_ENTITY", "Unprocessable Entity");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusBadRequest() {
        OutputStatus outputStatus = new OutputStatus(400, "BAD_REQUEST", "Bad Request");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }

    @Test
    public void deveRetornarStatusInternalServerError() {
        OutputStatus outputStatus = new OutputStatus(500, "INTERNAL_SERVER_ERROR", "Internal Server Error");
        BuscaTodosPedidoOutput output = Mockito.mock(BuscaTodosPedidoOutput.class);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("key", "value");

        when(presenterInterface.getOutput()).thenReturn(output);
        when(output.getOutputStatus()).thenReturn(outputStatus);
        when(presenterInterface.toArray()).thenReturn(responseBody);

        ResponseEntity<Object> response = presenterResponse.response(presenterInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo(responseBody);
    }
}
