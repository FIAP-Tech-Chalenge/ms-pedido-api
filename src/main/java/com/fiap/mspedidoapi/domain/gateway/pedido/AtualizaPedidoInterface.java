package com.fiap.mspedidoapi.domain.gateway.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;

import java.util.UUID;

public interface AtualizaPedidoInterface {
    Entrega movePedidoParaEmPreparacao(UUID pedidoUuid, Integer tempoDePreparoEmMinutos);
}
