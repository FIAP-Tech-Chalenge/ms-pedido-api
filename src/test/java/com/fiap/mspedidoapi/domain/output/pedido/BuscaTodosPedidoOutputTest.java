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
    public void deveInicializarComConstrutorSemArgumentos() {
        BuscaTodosPedidoOutput output = new BuscaTodosPedidoOutput();
        assertThat(output.getListPedidos()).isNull();
        assertThat(output.getOutputStatus()).isNull();
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

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(buscaTodosPedidoOutput).isEqualTo(buscaTodosPedidoOutput);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        BuscaTodosPedidoOutput output1 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        BuscaTodosPedidoOutput output2 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        BuscaTodosPedidoOutput output1 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        BuscaTodosPedidoOutput output2 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        BuscaTodosPedidoOutput output3 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(buscaTodosPedidoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(buscaTodosPedidoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaListPedidosDiferentes() {
        PedidoEntity pedidoDiferente = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null, 300.0f);
        List<PedidoEntity> pedidosDiferentes = List.of(pedidoDiferente);
        BuscaTodosPedidoOutput outputDiferente = new BuscaTodosPedidoOutput(pedidosDiferentes, outputStatus);
        assertThat(buscaTodosPedidoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaTodosPedidoOutput outputDiferente = new BuscaTodosPedidoOutput(pedidos, statusDiferente);
        assertThat(buscaTodosPedidoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputComListPedidosNulo() {
        BuscaTodosPedidoOutput outputComListPedidosNulo = new BuscaTodosPedidoOutput(null, outputStatus);
        assertThat(buscaTodosPedidoOutput).isNotEqualTo(outputComListPedidosNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputComOutputStatusNulo() {
        BuscaTodosPedidoOutput outputComOutputStatusNulo = new BuscaTodosPedidoOutput(pedidos, null);
        assertThat(buscaTodosPedidoOutput).isNotEqualTo(outputComOutputStatusNulo);
    }

    @Test
    public void equalsDeveRetornarTrueParaTodosOsCamposNulos() {
        BuscaTodosPedidoOutput output1 = new BuscaTodosPedidoOutput(null, null);
        BuscaTodosPedidoOutput output2 = new BuscaTodosPedidoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int hashCode1 = buscaTodosPedidoOutput.hashCode();
        int hashCode2 = buscaTodosPedidoOutput.hashCode();
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        BuscaTodosPedidoOutput output1 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        BuscaTodosPedidoOutput output2 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        PedidoEntity pedidoDiferente = new PedidoEntity(UUID.randomUUID(), UUID.randomUUID(), null, null, 300.0f);
        List<PedidoEntity> pedidosDiferentes = List.of(pedidoDiferente);
        BuscaTodosPedidoOutput outputDiferente = new BuscaTodosPedidoOutput(pedidosDiferentes, outputStatus);
        assertThat(buscaTodosPedidoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "BuscaTodosPedidoOutput(listPedidos=" + pedidos + ", outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'})";
        assertThat(buscaTodosPedidoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        BuscaTodosPedidoOutput output1 = new BuscaTodosPedidoOutput(pedidos, outputStatus);
        assertThat(buscaTodosPedidoOutput.canEqual(output1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(buscaTodosPedidoOutput.canEqual(new Object())).isFalse();
    }
}
