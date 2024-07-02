package com.fiap.mspedidoapi.domain.exception.pedido;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProdutoDoPedidoSemQuantidadeExceptionTest {

    @Test
    public void deveInicializarProdutoDoPedidoSemQuantidadeExceptionComMensagem() {
        String mensagem = "O produto do pedido está sem quantidade!";
        ProdutoDoPedidoSemQuantidadeException exception = new ProdutoDoPedidoSemQuantidadeException(mensagem);

        assertThat(exception.getMessage()).isEqualTo(mensagem);
    }

    @Test
    public void deveLancarProdutoDoPedidoSemQuantidadeException() {
        String mensagem = "O produto do pedido está sem quantidade!";

        Exception exception = assertThrows(ProdutoDoPedidoSemQuantidadeException.class, () -> {
            throw new ProdutoDoPedidoSemQuantidadeException(mensagem);
        });

        assertThat(exception.getMessage()).isEqualTo(mensagem);
    }
}
