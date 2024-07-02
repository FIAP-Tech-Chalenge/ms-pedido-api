package com.fiap.mspedidoapi.infra.collection.pedido;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoTest {

    private Pedido pedido;
    private UUID uuidPedido;
    private UUID clienteUuid;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    public void setUp() {
        uuidPedido = UUID.randomUUID();
        clienteUuid = UUID.randomUUID();
        produto1 = new Produto(UUID.randomUUID(), "Produto 1", 10.0f, CategoriaEnum.LANCHE, 2);
        produto2 = new Produto(UUID.randomUUID(), "Produto 2", 15.0f, CategoriaEnum.BEBIDA, 1);

        pedido = new Pedido();
        pedido.setId("12345");
        pedido.setUuidPedido(uuidPedido);
        pedido.setClienteUuid(clienteUuid);
        pedido.setNumeroPedido(12345);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        pedido.setStatusPagamento(StatusPagamento.PAGO);
        pedido.setTotal(35.0f);
        pedido.setProdutos(List.of(produto1, produto2));
    }

    @Test
    public void deveInicializarPedidoCorretamente() {
        assertThat(pedido.getId()).isEqualTo("12345");
        assertThat(pedido.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(pedido.getClienteUuid()).isEqualTo(clienteUuid);
        assertThat(pedido.getNumeroPedido()).isEqualTo(12345);
        assertThat(pedido.getStatusPedido()).isEqualTo(StatusPedido.RECEBIDO);
        assertThat(pedido.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
        assertThat(pedido.getTotal()).isEqualTo(35.0f);
        assertThat(pedido.getProdutos()).containsExactly(produto1, produto2);
    }

    @Test
    public void devePermitirAtualizarAtributos() {
        UUID novoUuidPedido = UUID.randomUUID();
        UUID novoClienteUuid = UUID.randomUUID();
        Produto novoProduto = new Produto(UUID.randomUUID(), "Produto 3", 20.0f, CategoriaEnum.ACOMPANHAMENTO, 3);

        pedido.setUuidPedido(novoUuidPedido);
        pedido.setClienteUuid(novoClienteUuid);
        pedido.setNumeroPedido(67890);
        pedido.setStatusPedido(StatusPedido.EM_PREPARACAO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pedido.setTotal(60.0f);
        pedido.setProdutos(List.of(novoProduto));

        assertThat(pedido.getUuidPedido()).isEqualTo(novoUuidPedido);
        assertThat(pedido.getClienteUuid()).isEqualTo(novoClienteUuid);
        assertThat(pedido.getNumeroPedido()).isEqualTo(67890);
        assertThat(pedido.getStatusPedido()).isEqualTo(StatusPedido.EM_PREPARACAO);
        assertThat(pedido.getStatusPagamento()).isEqualTo(StatusPagamento.AGUARDANDO_PAGAMENTO);
        assertThat(pedido.getTotal()).isEqualTo(60.0f);
        assertThat(pedido.getProdutos()).containsExactly(novoProduto);
    }
}
