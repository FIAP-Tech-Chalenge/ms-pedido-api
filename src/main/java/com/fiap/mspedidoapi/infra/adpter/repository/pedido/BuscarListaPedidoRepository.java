package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.Pedido;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class BuscarListaPedidoRepository implements BuscaListaPedidoInterface {
    public final PedidosMongoRepository pedidosMongoRepository;

    @Override
    public List<Pedido> findListaPedidos() {
        //implementar a lógica de busca de pedidos
        var a = pedidosMongoRepository.findAll();
        List<Pedido> pedidosEntities = new java.util.ArrayList<>();

        return pedidosEntities;
    }
}
