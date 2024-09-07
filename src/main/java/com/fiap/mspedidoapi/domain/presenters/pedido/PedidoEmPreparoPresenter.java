package com.fiap.mspedidoapi.domain.presenters.pedido;

import com.fiap.mspedidoapi.domain.generic.presenter.PresenterInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoEmPreparacaoOutput;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;

import java.util.HashMap;
import java.util.Map;

public class PedidoEmPreparoPresenter implements PresenterInterface {
    private final PedidoEmPreparacaoOutput pedidoEmPreparoOutput;

    public PedidoEmPreparoPresenter(PedidoEmPreparacaoOutput pedidoEmPreparoOutput) {
        this.pedidoEmPreparoOutput = pedidoEmPreparoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid_pedido", this.pedidoEmPreparoOutput.getPedido().getPedidoId());

        return array;
    }

    public PedidoEmPreparacaoOutput getOutput() {
        return this.pedidoEmPreparoOutput;
    }
}