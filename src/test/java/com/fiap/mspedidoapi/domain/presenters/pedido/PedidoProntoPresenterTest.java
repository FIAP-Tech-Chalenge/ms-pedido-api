package com.fiap.mspedidoapi.domain.presenters.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoProntoPresenterTest {

    private PedidoProntoOutput pedidoProntoOutput;
    private PedidoProntoPresenter pedidoProntoPresenter;
    private Entrega entrega;
    private OutputStatus outputStatus;

    @BeforeEach
    public void setUp() {
        entrega = new Entrega(UUID.randomUUID(), 123, StatusPedido.PRONTO);
        outputStatus = new OutputStatus(200, "OK", "Pedido pronto");
        pedidoProntoOutput = new PedidoProntoOutput(entrega, outputStatus);
        pedidoProntoPresenter = new PedidoProntoPresenter(pedidoProntoOutput);
    }

    @Test
    public void deveInicializarPedidoProntoPresenterCorretamente() {
        assertThat(pedidoProntoPresenter.getOutput()).isEqualTo(pedidoProntoOutput);
    }

    @Test
    public void toArrayDeveRetornarMapaCorreto() {
        Map<String, Object> array = pedidoProntoPresenter.toArray();

        assertThat(array).containsEntry("uuid_pedido", entrega.getUuidPedido());
        assertThat(array).containsEntry("status_pagamento", entrega.getStatusPedido());
        assertThat(array).containsEntry("numero_pedido", entrega.getNumeroPedido());
    }
}
