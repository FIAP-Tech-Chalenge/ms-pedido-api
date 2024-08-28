package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PedidoProntoRepositoryTest {

    private PedidosMongoRepository pedidosMongoRepository;
    private PedidoProntoRepository pedidoProntoRepository;

    @BeforeEach
    public void setUp() {
        pedidosMongoRepository = mock(PedidosMongoRepository.class);
        pedidoProntoRepository = new PedidoProntoRepository(pedidosMongoRepository);
    }

    @Test
    public void deveRetornarEntregaQuandoPedidoJaEstaPronto() {
        UUID uuidPedido = UUID.randomUUID();
        Pedido pedido = new Pedido();
        pedido.setUuidPedido(uuidPedido);
        pedido.setNumeroPedido(123);
        pedido.setStatusPedido(StatusPedido.PRONTO);

        when(pedidosMongoRepository.findByUuidPedido(uuidPedido)).thenReturn(Optional.of(pedido));

        Entrega entrega = pedidoProntoRepository.atualizaStatusPedido(uuidPedido);

        assertThat(entrega).isNotNull();
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(123);
        assertThat(entrega.getStatusPedido()).isEqualTo(StatusPedido.PRONTO);

        verify(pedidosMongoRepository, never()).save(any(Pedido.class));
    }

    @Test
    public void deveAtualizarStatusParaProntoQuandoNaoEstaPronto() {
        UUID uuidPedido = UUID.randomUUID();
        Pedido pedido = new Pedido();
        pedido.setUuidPedido(uuidPedido);
        pedido.setNumeroPedido(123);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);

        when(pedidosMongoRepository.findByUuidPedido(uuidPedido)).thenReturn(Optional.of(pedido));

        Entrega entrega = pedidoProntoRepository.atualizaStatusPedido(uuidPedido);

        assertThat(entrega).isNotNull();
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(123);
        assertThat(entrega.getStatusPedido()).isEqualTo(StatusPedido.PRONTO);

        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidosMongoRepository).save(captor.capture());
        Pedido savedPedido = captor.getValue();
        assertThat(savedPedido.getStatusPedido()).isEqualTo(StatusPedido.PRONTO);
    }

    @Test
    public void deveRetornarNullQuandoPedidoNaoEncontrado() {
        UUID uuidPedido = UUID.randomUUID();
        when(pedidosMongoRepository.findByUuidPedido(uuidPedido)).thenReturn(Optional.empty());

        Entrega entrega = pedidoProntoRepository.atualizaStatusPedido(uuidPedido);

        assertThat(entrega).isNull();
        verify(pedidosMongoRepository, never()).save(any(Pedido.class));
    }
}
