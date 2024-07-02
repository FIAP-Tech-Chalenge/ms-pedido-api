package com.fiap.mspedidoapi.domain.generic.output;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OutputStatusTest {

    @Test
    public void deveInicializarOutputStatusComValoresCorretos() {
        int code = 200;
        String codeName = "SUCCESS";
        String message = "Operação realizada com sucesso.";
        OutputStatus outputStatus = new OutputStatus(code, codeName, message);

        assertThat(outputStatus.getCode()).isEqualTo(code);
        assertThat(outputStatus.getCodeName()).isEqualTo(codeName);
        assertThat(outputStatus.getMessage()).isEqualTo(message);
    }
}
