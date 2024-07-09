package com.fiap.mspedidoapi.domain.output.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class PedidoProntoOutputTest {

    private Entrega entrega;
    private OutputStatus outputStatus;
    private PedidoProntoOutput pedidoProntoOutput;

    @BeforeEach
    public void setUp() {
        entrega = new Entrega(UUID.randomUUID(), 123, StatusPedido.PRONTO);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        pedidoProntoOutput = new PedidoProntoOutput(entrega, outputStatus);
    }

    @Test
    public void deveInicializarComConstrutorSemArgumentos() {
        PedidoProntoOutput output = new PedidoProntoOutput();
        assertThat(output.getEntrega()).isNull();
        assertThat(output.getOutputStatus()).isNull();
    }

    @Test
    public void deveInicializarPedidoProntoOutputComValoresCorretos() {
        assertThat(pedidoProntoOutput.getEntrega()).isEqualTo(entrega);
        assertThat(pedidoProntoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarEntregaComoBody() {
        assertThat(pedidoProntoOutput.getBody()).isEqualTo(entrega);
    }

    @Test
    public void devePermitirAtualizarEntrega() {
        Entrega novaEntrega = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        pedidoProntoOutput.setEntrega(novaEntrega);

        assertThat(pedidoProntoOutput.getEntrega()).isEqualTo(novaEntrega);
        assertThat(pedidoProntoOutput.getBody()).isEqualTo(novaEntrega);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        pedidoProntoOutput.setOutputStatus(novoOutputStatus);

        assertThat(pedidoProntoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(pedidoProntoOutput).isEqualTo(pedidoProntoOutput);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        PedidoProntoOutput output1 = new PedidoProntoOutput(entrega, outputStatus);
        PedidoProntoOutput output2 = new PedidoProntoOutput(entrega, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        PedidoProntoOutput output1 = new PedidoProntoOutput(entrega, outputStatus);
        PedidoProntoOutput output2 = new PedidoProntoOutput(entrega, outputStatus);
        PedidoProntoOutput output3 = new PedidoProntoOutput(entrega, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(pedidoProntoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(pedidoProntoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaEntregasDiferentes() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        PedidoProntoOutput outputDiferente = new PedidoProntoOutput(entregaDiferente, outputStatus);
        assertThat(pedidoProntoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        PedidoProntoOutput outputDiferente = new PedidoProntoOutput(entrega, statusDiferente);
        assertThat(pedidoProntoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaEntregaNulo() {
        PedidoProntoOutput outputComEntregaNulo = new PedidoProntoOutput(null, outputStatus);
        assertThat(pedidoProntoOutput).isNotEqualTo(outputComEntregaNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNulo() {
        PedidoProntoOutput outputComStatusNulo = new PedidoProntoOutput(entrega, null);
        assertThat(pedidoProntoOutput).isNotEqualTo(outputComStatusNulo);
    }

    @Test
    public void equalsDeveRetornarTrueParaTodosOsCamposNulos() {
        PedidoProntoOutput output1 = new PedidoProntoOutput(null, null);
        PedidoProntoOutput output2 = new PedidoProntoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int hashCode1 = pedidoProntoOutput.hashCode();
        int hashCode2 = pedidoProntoOutput.hashCode();
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        PedidoProntoOutput output1 = new PedidoProntoOutput(entrega, outputStatus);
        PedidoProntoOutput output2 = new PedidoProntoOutput(entrega, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        PedidoProntoOutput outputDiferente = new PedidoProntoOutput(entregaDiferente, outputStatus);
        assertThat(pedidoProntoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "PedidoProntoOutput(entrega=" + entrega + ", outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'})";
        assertThat(pedidoProntoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        PedidoProntoOutput output1 = new PedidoProntoOutput(entrega, outputStatus);
        assertThat(pedidoProntoOutput.canEqual(output1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(pedidoProntoOutput.canEqual(new Object())).isFalse();
    }
}
