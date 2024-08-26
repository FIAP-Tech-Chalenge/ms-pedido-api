package com.fiap.mspedidoapi.domain.useCase.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.gateway.pedido.PedidoProntoInterface;
import com.fiap.mspedidoapi.domain.gateway.producers.PedidoProntoProducerInterface;
import com.fiap.mspedidoapi.domain.generic.output.OutputError;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PedidoProntoUseCaseTest {

    private PedidoProntoInterface pedidoProntoInterface;
    private PedidoProntoProducerInterface pedidoProntoProducerInterface;
    private PedidoProntoUseCase pedidoProntoUseCase;

    @BeforeEach
    public void setUp() {
        pedidoProntoInterface = mock(PedidoProntoInterface.class);
        pedidoProntoProducerInterface = mock(PedidoProntoProducerInterface.class);
        pedidoProntoUseCase = new PedidoProntoUseCase(pedidoProntoInterface, pedidoProntoProducerInterface);
    }

    @Test
    public void deveAtualizarPedidoComSucesso() {
        UUID uuidPedido = UUID.randomUUID();
        Entrega entrega = new Entrega(uuidPedido, 123, StatusPedido.PRONTO);
        when(pedidoProntoInterface.atualizaStatusPedido(uuidPedido)).thenReturn(entrega);

        pedidoProntoUseCase.execute(uuidPedido);

        assertThat(pedidoProntoUseCase.getOutputInterface()).isInstanceOf(PedidoProntoOutput.class);
        assertThat(pedidoProntoUseCase.getOutputInterface().getOutputStatus().getCode()).isEqualTo(200);
        assertThat(pedidoProntoUseCase.getOutputInterface().getBody()).isEqualTo(entrega);

        ArgumentCaptor<PedidoProntoOutput> captor = ArgumentCaptor.forClass(PedidoProntoOutput.class);
        verify(pedidoProntoProducerInterface).send(captor.capture());
        PedidoProntoOutput output = captor.getValue();
        assertThat(output.getOutputStatus().getCode()).isEqualTo(200);
    }

    @Test
    public void deveRetornarNotFoundQuandoPedidoNaoEncontrado() {
        UUID uuidPedido = UUID.randomUUID();
        when(pedidoProntoInterface.atualizaStatusPedido(uuidPedido)).thenReturn(null);

        pedidoProntoUseCase.execute(uuidPedido);

        assertThat(pedidoProntoUseCase.getOutputInterface()).isInstanceOf(PedidoProntoOutput.class);
        assertThat(pedidoProntoUseCase.getOutputInterface().getOutputStatus().getCode()).isEqualTo(404);
        assertThat(pedidoProntoUseCase.getOutputInterface().getBody()).isNull();

        verify(pedidoProntoProducerInterface, never()).send(any(PedidoProntoOutput.class));
    }

    @Test
    public void deveRetornarErroQuandoExcecaoForLancada() {
        UUID uuidPedido = UUID.randomUUID();
        when(pedidoProntoInterface.atualizaStatusPedido(uuidPedido)).thenThrow(new RuntimeException("Erro no servidor"));

        pedidoProntoUseCase.execute(uuidPedido);

        assertThat(pedidoProntoUseCase.getOutputInterface()).isInstanceOf(OutputError.class);
        assertThat(pedidoProntoUseCase.getOutputInterface().getOutputStatus().getCode()).isEqualTo(500);
        assertThat(((OutputError) pedidoProntoUseCase.getOutputInterface()).getMensagem()).isEqualTo("Erro no servidor");

        verify(pedidoProntoProducerInterface, never()).send(any(PedidoProntoOutput.class));
    }

    @Test
    public void deveRetornarEntregaInterfaceCorreta() {
        assertThat(pedidoProntoUseCase.getEntregaInterface()).isEqualTo(pedidoProntoInterface);
    }

    @Test
    public void deveRetornarPedidoProntoProducerInterfaceCorreta() {
        assertThat(pedidoProntoUseCase.getPedidoProntoProducerInterface()).isEqualTo(pedidoProntoProducerInterface);
    }
}
