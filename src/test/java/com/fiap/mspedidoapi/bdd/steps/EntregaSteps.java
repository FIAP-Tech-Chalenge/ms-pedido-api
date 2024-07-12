package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class EntregaSteps {

    private UUID uuidPedido;
    private int numeroPedido;
    private StatusPedido statusPedido;
    private Entrega entrega;
    private UUID novoUuidPedido;
    private int novoNumeroPedido;
    private StatusPedido novoStatusPedido;

    @Before
    public void setUp() {
        uuidPedido = UUID.randomUUID();
        numeroPedido = 123;
        statusPedido = StatusPedido.PRONTO;
        entrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
    }

    @Given("que uma Entrega é inicializada")
    public void que_uma_entrega_é_inicializada() {
        setUp();
    }

    @Then("os atributos da Entrega devem ser corretamente definidos")
    public void os_atributos_da_entrega_devem_ser_corretamente_definidos() {
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(numeroPedido);
        assertThat(entrega.getStatusPedido()).isEqualTo(statusPedido);
    }

    @When("o uuidPedido é atualizado")
    public void o_uuidpedido_é_atualizado() {
        novoUuidPedido = UUID.randomUUID();
        entrega.setUuidPedido(novoUuidPedido);
    }

    @Then("o novo uuidPedido deve ser corretamente definido")
    public void o_novo_uuidpedido_deve_ser_corretamente_definido() {
        assertThat(entrega.getUuidPedido()).isEqualTo(novoUuidPedido);
    }

    @When("o numeroPedido é atualizado")
    public void o_numeropedido_é_atualizado() {
        novoNumeroPedido = 456;
        entrega.setNumeroPedido(novoNumeroPedido);
    }

    @Then("o novo numeroPedido deve ser corretamente definido")
    public void o_novo_numeropedido_deve_ser_corretamente_definido() {
        assertThat(entrega.getNumeroPedido()).isEqualTo(novoNumeroPedido);
    }

    @When("o statusPedido é atualizado")
    public void o_statuspedido_é_atualizado() {
        novoStatusPedido = StatusPedido.FINALIZADO;
        entrega.setStatusPedido(novoStatusPedido);
    }

    @Then("o novo statusPedido deve ser corretamente definido")
    public void o_novo_statuspedido_deve_ser_corretamente_definido() {
        assertThat(entrega.getStatusPedido()).isEqualTo(novoStatusPedido);
    }

    @Then("deve retornar false para equals com objeto diferente")
    public void deve_retornar_false_para_equals_com_objeto_diferente() {
        assertThat(entrega).isNotEqualTo(new Object());
    }

    @Then("deve retornar true para equals com o mesmo objeto")
    public void deve_retornar_true_para_equals_com_o_mesmo_objeto() {
        assertThat(entrega).isEqualTo(entrega);
    }

    @Then("deve retornar true para equals com mesmo conteúdo")
    public void deve_retornar_true_para_equals_com_mesmo_conteúdo() {
        Entrega mesmaEntrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
        assertThat(entrega).isEqualTo(mesmaEntrega);
    }

    @Then("deve retornar false para equals com conteúdo diferente")
    public void deve_retornar_false_para_equals_com_conteúdo_diferente() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        assertThat(entrega).isNotEqualTo(entregaDiferente);
    }

    @Then("hashCode deve ser igual para objetos iguais")
    public void hashcode_deve_ser_igual_para_objetos_iguais() {
        Entrega mesmaEntrega = new Entrega(uuidPedido, numeroPedido, statusPedido);
        assertThat(entrega.hashCode()).isEqualTo(mesmaEntrega.hashCode());
    }

    @Then("hashCode deve ser diferente para objetos diferentes")
    public void hashcode_deve_ser_diferente_para_objetos_diferentes() {
        Entrega entregaDiferente = new Entrega(UUID.randomUUID(), 456, StatusPedido.FINALIZADO);
        assertThat(entrega.hashCode()).isNotEqualTo(entregaDiferente.hashCode());
    }

    @Then("toString deve retornar a string correta")
    public void tostring_deve_retornar_a_string_correta() {
        String expectedToString = "Entrega{uuidPedido=" + uuidPedido + ", numeroPedido=" + numeroPedido + ", statusPedido=" + statusPedido + "}";
        assertThat(entrega.toString()).isEqualTo(expectedToString);
    }
}
