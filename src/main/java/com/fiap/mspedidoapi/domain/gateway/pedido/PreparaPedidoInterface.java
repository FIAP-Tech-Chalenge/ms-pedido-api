package com.fiap.mspedidoapi.domain.gateway.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;

import java.util.UUID;

public interface PreparaPedidoInterface {
    Pedido encontraPedidoPorUuid(UUID pedidoUuid) throws PedidoNaoEncontradoException;
    Entrega movePedidoParaEmPreparacao(UUID pedidoUuid, Integer tempoDePreparoEmMinutos) throws PedidoNaoEncontradoException;
}
