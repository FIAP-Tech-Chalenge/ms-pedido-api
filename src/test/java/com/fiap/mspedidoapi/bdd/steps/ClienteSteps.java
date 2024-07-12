package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.entity.pedido.Cliente;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteSteps {

    private Cliente cliente;
    private UUID uuid;
    private String nome;
    private String email;
    private String cpf;
    private UUID novoUuid;
    private String novoNome;
    private String novoEmail;
    private String novoCpf;

    @Before
    public void setUp() {
        uuid = UUID.randomUUID();
        nome = "Cliente 1";
        email = "cliente1@email.com";
        cpf = "12345678912";
        cliente = new Cliente(nome, cpf, email, uuid);
    }

    @Given("que um Cliente é inicializado sem argumentos")
    public void que_um_cliente_é_inicializado_sem_argumentos() {
        cliente = new Cliente();
    }

    @When("os valores são definidos")
    public void os_valores_sao_definidos() {
        cliente.setUuid(uuid);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setCpf(cpf);
    }

    @Then("os atributos do Cliente devem ser corretamente definidos")
    public void os_atributos_do_cliente_devem_ser_corretamente_definidos() {
        assertThat(cliente.getUuid()).isEqualTo(uuid);
        assertThat(cliente.getNome()).isEqualTo(nome);
        assertThat(cliente.getEmail()).isEqualTo(email);
        assertThat(cliente.getCpf()).isEqualTo(cpf);
    }

    @Given("que um Cliente é inicializado com argumentos")
    public void que_um_cliente_é_inicializado_com_argumentos() {
        setUp();
    }

    @When("os valores são atualizados")
    public void os_valores_sao_atualizados() {
        novoUuid = UUID.randomUUID();
        novoNome = "Cliente 2";
        novoEmail = "cliente2@email.com";
        novoCpf = "98765432109";

        cliente.setUuid(novoUuid);
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setCpf(novoCpf);
    }

    @Then("os novos atributos do Cliente devem ser corretamente definidos")
    public void os_novos_atributos_do_cliente_devem_ser_corretamente_definidos() {
        assertThat(cliente.getUuid()).isEqualTo(novoUuid);
        assertThat(cliente.getNome()).isEqualTo(novoNome);
        assertThat(cliente.getEmail()).isEqualTo(novoEmail);
        assertThat(cliente.getCpf()).isEqualTo(novoCpf);
    }

    @When("os valores são definidos como nulos")
    public void os_valores_sao_definidos_como_nulos() {
        cliente.setUuid(null);
        cliente.setNome(null);
        cliente.setEmail(null);
        cliente.setCpf(null);
    }

    @Then("os atributos do Cliente devem ser nulos")
    public void os_atributos_do_cliente_devem_ser_nulos() {
        assertNull(cliente.getUuid());
        assertNull(cliente.getNome());
        assertNull(cliente.getEmail());
        assertNull(cliente.getCpf());
    }

    @Then("toString do Cliente deve retornar a string correta")
    public void tostring_do_cliente_deve_retornar_a_string_correta() {
        String esperado = "Cliente{uuid=" + uuid.toString() + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + "}";
        assertThat(cliente.toString()).isEqualTo(esperado);
    }
}
