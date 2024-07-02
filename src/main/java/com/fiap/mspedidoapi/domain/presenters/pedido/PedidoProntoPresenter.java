package com.fiap.mspedidoapi.domain.presenters.pedido;

import com.fiap.mspedidoapi.domain.generic.presenter.PresenterInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;

import java.util.HashMap;
import java.util.Map;

public class PedidoProntoPresenter implements PresenterInterface {
    private final PedidoProntoOutput pedidoProntoOutput;

    public PedidoProntoPresenter(PedidoProntoOutput pedidoProntoOutput) {
        this.pedidoProntoOutput = pedidoProntoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid_pedido", this.pedidoProntoOutput.getEntrega().getUuidPedido());
        array.put("status_pagamento", this.pedidoProntoOutput.getEntrega().getStatusPedido());
        array.put("numero_pedido", this.pedidoProntoOutput.getEntrega().getNumeroPedido());

        return array;
    }

    public PedidoProntoOutput getOutput() {
        return this.pedidoProntoOutput;
    }
}