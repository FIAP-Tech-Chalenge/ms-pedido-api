package com.fiap.mspedidoapi.domain.gateway.producers;

import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;

public interface PedidoProntoProducerInterface {
    void send(PedidoProntoOutput pedidoProntoOutput);
}
