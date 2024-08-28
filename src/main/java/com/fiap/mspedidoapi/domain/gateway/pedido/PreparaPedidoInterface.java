package com.fiap.mspedidoapi.domain.gateway.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;

import java.util.UUID;

public interface PreparaPedidoInterface {
    PedidoEntity encontraPedidoPorUuid(UUID pedidoUuid) throws PedidoNaoEncontradoException;
    PedidoEntity movePedidoParaEmPreparacao(UUID pedidoUuid, Integer tempoDePreparoEmMinutos) throws PedidoNaoEncontradoException;
}
