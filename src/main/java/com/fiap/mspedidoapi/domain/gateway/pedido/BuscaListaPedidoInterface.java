package com.fiap.mspedidoapi.domain.gateway.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.Pedido;

import java.util.List;

public interface BuscaListaPedidoInterface {
    List<Pedido> findListaPedidos();
}
