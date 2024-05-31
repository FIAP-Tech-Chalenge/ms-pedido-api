package com.fiap.mspedidoapi.domain.gateway.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;

import java.util.List;

public interface BuscaListaPedidoInterface {
    List<PedidoEntity> findListaPedidos();
}
