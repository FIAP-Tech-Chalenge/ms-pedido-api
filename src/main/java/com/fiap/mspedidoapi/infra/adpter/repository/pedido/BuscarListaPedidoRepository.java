package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.Pedido;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarListaPedidoRepository implements BuscaListaPedidoInterface {
    @Override
    public List<Pedido> findListaPedidos() {
        //implementar a lógica de busca de pedidos
        List<Pedido> pedidosEntities = null;
        return pedidosEntities;
    }
}
