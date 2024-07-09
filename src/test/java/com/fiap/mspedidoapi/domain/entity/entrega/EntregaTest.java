package com.fiap.mspedidoapi.domain.entity.entrega;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EntregaTest {

    private UUID uuidPedido;
    private int numeroPedido;
    private StatusPedido statusPedido;
    private Entrega entrega;

    @BeforeEach
    public void setUp() {
        uuidPedido = UUID.randomUUID();
        numeroPedido = 123;
        statusPedido = StatusPedido.PRONTO;
        entrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
    }

    @Test
    public void deveInicializarEntregaComValoresCorretos() {
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(numeroPedido);
        assertThat(entrega.getStatusPedido()).isEqualTo(statusPedido);
    }

    @Test
    public void devePermitirAtualizarUuidPedido() {
        UUID novoUuidPedido = UUID.randomUUID();
        entrega.setUuidPedido(novoUuidPedido);
        assertThat(entrega.getUuidPedido()).isEqualTo(novoUuidPedido);
    }

    @Test
    public void devePermitirAtualizarNumeroPedido() {
        int novoNumeroPedido = 456;
        entrega.setNumeroPedido(novoNumeroPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(novoNumeroPedido);
    }

    @Test
    public void devePermitirAtualizarStatusPedido() {
        StatusPedido novoStatusPedido = StatusPedido.FINALIZADO;
        entrega.setStatusPedido(novoStatusPedido);
        assertThat(entrega.getStatusPedido()).isEqualTo(novoStatusPedido);
    }

    @Test
    public void deveRetornarFalseParaEqualsComObjetoDiferente() {
        assertThat(entrega).isNotEqualTo(new Object());
    }

    @Test
    public void deveRetornarTrueParaEqualsComMesmoObjeto() {
        assertThat(entrega).isEqualTo(entrega);
    }

    @Test
    public void deveRetornarTrueParaEqualsComMesmoConteudo() {
        Entrega mesmaEntrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
        assertThat(entrega).isEqualTo(mesmaEntrega);
    }

    @Test
    public void deveRetornarFalseParaEqualsComConteudoDiferente() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        assertThat(entrega).isNotEqualTo(entregaDiferente);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        Entrega mesmaEntrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
        assertThat(entrega.hashCode()).isEqualTo(mesmaEntrega.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        assertThat(entrega.hashCode()).isNotEqualTo(entregaDiferente.hashCode());
    }

    @Test
    public void toStringDeveRetornarStringCorreta() {
        String expectedToString = "Entrega{uuidPedido=" + uuidPedido + ", numeroPedido=" + numeroPedido + ", statusPedido=" + statusPedido + "}";
        assertThat(entrega.toString()).isEqualTo(expectedToString);
    }
}
