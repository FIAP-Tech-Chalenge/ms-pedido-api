package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BuscaTodosPedidoOutputTest {

    private List<PedidoEntity> pedidos;
    private OutputStatus outputStatus;
    private BuscaTodosPedidoOutput buscaTodosPedidoOutput;

    @BeforeEach
    public void setUp() {
        PedidoEntity pedido1 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null, 100.0f);
        PedidoEntity pedido2 = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null, 200.0f);
        pedidos = List.of(pedido1, pedido2);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        buscaTodosPedidoOutput = new BuscaTodosPedidoOutput(pedidos, outputStatus);
    }

    @Test
    public void deveInicializarBuscaTodosPedidoOutputComValoresCorretos() {
        assertThat(buscaTodosPedidoOutput.getListPedidos()).isEqualTo(pedidos);
        assertThat(buscaTodosPedidoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarListPedidosComoBody() {
        assertThat(buscaTodosPedidoOutput.getBody()).isEqualTo(pedidos);
    }

    @Test
    public void devePermitirAtualizarListPedidos() {
        PedidoEntity novoPedido = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null, 300.0f);
        List<PedidoEntity> novosPedidos = List.of(novoPedido);
        buscaTodosPedidoOutput.setListPedidos(novosPedidos);

        assertThat(buscaTodosPedidoOutput.getListPedidos()).isEqualTo(novosPedidos);
        assertThat(buscaTodosPedidoOutput.getBody()).isEqualTo(novosPedidos);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        buscaTodosPedidoOutput.setOutputStatus(novoOutputStatus);

        assertThat(buscaTodosPedidoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
