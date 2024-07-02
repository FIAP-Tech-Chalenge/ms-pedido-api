package com.fiap.mspedidoapi.domain.exception.pedido;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PedidoVazioExceptionTest {

    @Test
    public void deveInicializarPedidoVazioExceptionComMensagem() {
        String mensagem = "O pedido está vazio!";
        PedidoVazioException exception = new PedidoVazioException(mensagem);

        assertThat(exception.getMessage()).isEqualTo(mensagem);
    }

    @Test
    public void deveLancarPedidoVazioException() {
        String mensagem = "O pedido está vazio!";

        Exception exception = assertThrows(PedidoVazioException.class, () -> {
            throw new PedidoVazioException(mensagem);
        });

        assertThat(exception.getMessage()).isEqualTo(mensagem);
    }
}
