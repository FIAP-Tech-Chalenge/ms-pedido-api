package com.fiap.mspedidoapi.domain.presenters.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPedidosPresenterTest {

    private GetPedidosPresenter presenter;
    private BuscaTodosPedidoOutput output;
    private PedidoEntity pedido1;
    private PedidoEntity pedido2;
    private ProdutoEntity produto1;
    private ProdutoEntity produto2;

    @BeforeEach
    public void setUp() {
        produto1 = new ProdutoEntity(UUID.randomUUID(), "Produto 1", 2, CategoriaEnum.LANCHE);
        produto1.setValor(10.0f);
        produto2 = new ProdutoEntity(UUID.randomUUID(), "Produto 2", 1, CategoriaEnum.BEBIDA);
        produto2.setValor(15.0f);

        pedido1 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), StatusPedido.RECEBIDO, StatusPagamento.PAGO,20, 35.0f);
        pedido1.setNumeroPedido(12345);
        pedido1.getProdutos().add(produto1);
        pedido1.getProdutos().add(produto2);

        pedido2 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), StatusPedido.EM_PREPARACAO, StatusPagamento.AGUARDANDO_PAGAMENTO,20, 50.0f);
        pedido2.setNumeroPedido(67890);

        List<PedidoEntity> pedidos = List.of(pedido1, pedido2);
        OutputStatus outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        output = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        presenter = new GetPedidosPresenter(output);
    }

    @Test
    public void deveInicializarGetPedidosPresenterComValoresCorretos() {
        assertThat(presenter.getOutput()).isEqualTo(output);
    }

    @Test
    public void deveConverterPedidosParaArrayCorretamente() {
        Map<String, Object> result = presenter.toArray();

        assertThat(result).containsKey("pedidos");
        List<Map<String, Object>> pedidosMapList = (List<Map<String, Object>>) result.get("pedidos");

        assertThat(pedidosMapList).hasSize(2);

        Map<String, Object> pedidoMap1 = pedidosMapList.get(0);
        assertThat(pedidoMap1.get("pedido_uuid")).isEqualTo(pedido1.getPedidoId().toString());
        assertThat(pedidoMap1.get("cliente_uuid")).isEqualTo(pedido1.getClienteUuid().toString());
        assertThat(pedidoMap1.get("status_pagamento")).isEqualTo(pedido1.getStatusPagamento().toString());
        assertThat(pedidoMap1.get("numero_pedido")).isEqualTo(pedido1.getNumeroPedido());
        assertThat(pedidoMap1.get("status_pedido")).isEqualTo(pedido1.getStatusPedido().toString());
        assertThat(pedidoMap1.get("total")).isEqualTo(pedido1.getTotal());

        List<Map<String, Object>> produtosMapList1 = (List<Map<String, Object>>) pedidoMap1.get("produtos");
        assertThat(produtosMapList1).hasSize(2);

        Map<String, Object> produtoMap1 = produtosMapList1.get(0);
        assertThat(produtoMap1.get("uuid")).isEqualTo(produto1.getUuid().toString());
        assertThat(produtoMap1.get("nome")).isEqualTo(produto1.getNome());
        assertThat(produtoMap1.get("quantidade")).isEqualTo(produto1.getQuantidade());
        assertThat(produtoMap1.get("valor")).isEqualTo(produto1.getValor());
        assertThat(produtoMap1.get("categoria")).isEqualTo(produto1.getCategoria().toString());

        Map<String, Object> produtoMap2 = produtosMapList1.get(1);
        assertThat(produtoMap2.get("uuid")).isEqualTo(produto2.getUuid().toString());
        assertThat(produtoMap2.get("nome")).isEqualTo(produto2.getNome());
        assertThat(produtoMap2.get("quantidade")).isEqualTo(produto2.getQuantidade());
        assertThat(produtoMap2.get("valor")).isEqualTo(produto2.getValor());
        assertThat(produtoMap2.get("categoria")).isEqualTo(produto2.getCategoria().toString());

        Map<String, Object> pedidoMap2 = pedidosMapList.get(1);
        assertThat(pedidoMap2.get("pedido_uuid")).isEqualTo(pedido2.getPedidoId().toString());
        assertThat(pedidoMap2.get("cliente_uuid")).isEqualTo(pedido2.getClienteUuid().toString());
        assertThat(pedidoMap2.get("status_pagamento")).isEqualTo(pedido2.getStatusPagamento().toString());
        assertThat(pedidoMap2.get("numero_pedido")).isEqualTo(pedido2.getNumeroPedido());
        assertThat(pedidoMap2.get("status_pedido")).isEqualTo(pedido2.getStatusPedido().toString());
        assertThat(pedidoMap2.get("total")).isEqualTo(pedido2.getTotal());

        List<Map<String, Object>> produtosMapList2 = (List<Map<String, Object>>) pedidoMap2.get("produtos");
        assertThat(produtosMapList2).isEmpty();
    }
}
