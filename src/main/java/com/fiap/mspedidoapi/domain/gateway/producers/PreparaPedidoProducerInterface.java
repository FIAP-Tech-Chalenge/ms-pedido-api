package com.fiap.mspedidoapi.domain.gateway.producers;

import com.fiap.mspedidoapi.domain.output.pedido.PedidoEmPreparacaoOutput;

public interface PreparaPedidoProducerInterface {
    void send(PedidoEmPreparacaoOutput pedidoEmPreparacaoOutput);
}
