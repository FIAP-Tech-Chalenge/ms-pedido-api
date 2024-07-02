package com.fiap.mspedidoapi.application.response;

import com.fiap.mspedidoapi.domain.generic.output.OutputInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GenericResponseTest {

    private GenericResponse genericResponse;
    private OutputInterface outputInterface;

    @BeforeEach
    public void setUp() {
        genericResponse = new GenericResponse();
        outputInterface = Mockito.mock(OutputInterface.class);
    }

    @Test
    public void deveRetornarStatusOk() {
        OutputStatus outputStatus = new OutputStatus(200, "OK", "Success");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("OK Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("OK Body");
    }

    @Test
    public void deveRetornarStatusCreated() {
        OutputStatus outputStatus = new OutputStatus(201, "CREATED", "Created");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("Created Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo("Created Body");
    }

    @Test
    public void deveRetornarStatusNotFound() {
        OutputStatus outputStatus = new OutputStatus(404, "NOT_FOUND", "Not Found");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("Not Found Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Not Found Body");
    }

    @Test
    public void deveRetornarStatusNoContent() {
        OutputStatus outputStatus = new OutputStatus(204, "NO_CONTENT", "No Content");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("No Content Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isEqualTo("No Content Body");
    }

    @Test
    public void deveRetornarStatusUnprocessableEntity() {
        OutputStatus outputStatus = new OutputStatus(422, "UNPROCESSABLE_ENTITY", "Unprocessable Entity");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("Unprocessable Entity Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
        assertThat(response.getBody()).isEqualTo("Unprocessable Entity Body");
    }

    @Test
    public void deveRetornarStatusBadRequest() {
        OutputStatus outputStatus = new OutputStatus(400, "BAD_REQUEST", "Bad Request");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("Bad Request Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Bad Request Body");
    }

    @Test
    public void deveRetornarStatusInternalServerError() {
        OutputStatus outputStatus = new OutputStatus(500, "INTERNAL_SERVER_ERROR", "Internal Server Error");
        when(outputInterface.getOutputStatus()).thenReturn(outputStatus);
        when(outputInterface.getBody()).thenReturn("Internal Server Error Body");

        ResponseEntity<Object> response = genericResponse.response(outputInterface);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isEqualTo("Internal Server Error Body");
    }
}
