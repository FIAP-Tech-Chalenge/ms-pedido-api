package com.fiap.mspedidoapi.domain.generic.output;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputErrorTest {

    @Test
    public void deveInicializarOutputErrorComValoresCorretos() {
        String mensagem = "Erro ao processar a operação.";
        OutputStatus outputStatus = new OutputStatus(500, "ERROR", "Internal Server Error");
        OutputError outputError = new OutputError(mensagem, outputStatus);

        assertThat(outputError.getMensagem()).isEqualTo(mensagem);
        assertThat(outputError.getOutputStatus()).isEqualTo(outputStatus);
        assertThat(outputError.getBody()).isEqualTo(outputStatus);
    }
}
