package com.fiap.mspedidoapi.domain.presenters.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.generic.presenter.PresenterInterface;
import com.fiap.mspedidoapi.domain.output.pedido.BuscaTodosPedidoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPedidosPresenter implements PresenterInterface {
    private final BuscaTodosPedidoOutput buscaTodosPedidoOutput;

    public GetPedidosPresenter(BuscaTodosPedidoOutput buscaTodosPedidoOutput) {
        this.buscaTodosPedidoOutput = buscaTodosPedidoOutput;
    }

    public Map<String, Object> toArray() {
        List<PedidoEntity> pedidos = this.buscaTodosPedidoOutput.getListPedidos();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> pedidosMapList = new ArrayList<>();

        for (PedidoEntity pedido : pedidos) {
            Map<String, Object> pedidoMap = new HashMap<>();
            pedidoMap.put("pedido_uuid", pedido.getPedidoId().toString());
            pedidoMap.put("cliente_uuid", pedido.getClienteUuid().toString());
            pedidoMap.put("status_pagamento", pedido.getStatusPagamento().toString());
            pedidoMap.put("numero_pedido", pedido.getNumeroPedido());
            pedidoMap.put("status_pedido", pedido.getStatusPedido().toString());
            pedidoMap.put("pedido_categoria", pedido.getStatusPagamento().toString());
            pedidoMap.put("pedido_quantidade", pedido.getStatusPagamento().toString());

            List<Map<String, Object>> produtosMapList = new ArrayList<>();
            for (ProdutoEntity produto : pedido.getProdutos()) {
                Map<String, Object> produtoMap = new HashMap<>();
                produtoMap.put("uuid", produto.getUuid().toString());
                produtoMap.put("nome", produto.getNome());
                produtoMap.put("quantidade", produto.getQuantidade());
                produtoMap.put("valor", produto.getValor());
                produtoMap.put("categoria", produto.getCategoria().toString());
                produtosMapList.add(produtoMap);
            }
            pedidoMap.put("produtos", produtosMapList);

            pedidoMap.put("total", pedido.getTotal());

            pedidosMapList.add(pedidoMap);
        }

        map.put("pedidos", pedidosMapList);

        return map;
    }

    public BuscaTodosPedidoOutput getOutput() {
        return this.buscaTodosPedidoOutput;
    }
}