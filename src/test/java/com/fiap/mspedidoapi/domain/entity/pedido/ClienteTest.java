package com.fiap.mspedidoapi.domain.entity.pedido;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClienteTest {
    private Cliente cliente;
    private UUID uuid;
    private String nome;
    private String email;
    private String cpf;

    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID();
        nome = "Cliente 1";
        email = "cliente1@email.com";
        cpf = "12345678912";
        cliente = new Cliente(nome, cpf, email, uuid);
    }

    @Test
    public void devePermitirInicializarClienteSemArgumentos() {
        Cliente clienteSemArgs = new Cliente();
        clienteSemArgs.setUuid(uuid);
        clienteSemArgs.setNome(nome);
        clienteSemArgs.setEmail(email);
        clienteSemArgs.setCpf(cpf);

        assertThat(clienteSemArgs.getUuid()).isEqualTo(uuid);
        assertThat(clienteSemArgs.getNome()).isEqualTo(nome);
        assertThat(clienteSemArgs.getEmail()).isEqualTo(email);
        assertThat(clienteSemArgs.getCpf()).isEqualTo(cpf);
    }

    @Test
    public void deveInicializarClienteComArgumentos() {
        assertThat(cliente.getUuid()).isEqualTo(uuid);
        assertThat(cliente.getNome()).isEqualTo(nome);
        assertThat(cliente.getEmail()).isEqualTo(email);
        assertThat(cliente.getCpf()).isEqualTo(cpf);
    }

    @Test
    public void devePermitirAtualizarValores() {
        UUID novoUuid = UUID.randomUUID();
        String novoNome = "Cliente 2";
        String novoEmail = "cliente2@email.com";
        String novoCpf = "98765432109";

        cliente.setUuid(novoUuid);
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
        cliente.setCpf(novoCpf);

        assertThat(cliente.getUuid()).isEqualTo(novoUuid);
        assertThat(cliente.getNome()).isEqualTo(novoNome);
        assertThat(cliente.getEmail()).isEqualTo(novoEmail);
        assertThat(cliente.getCpf()).isEqualTo(novoCpf);
    }

    @Test
    public void devePermitirValoresNulos() {
        Cliente clienteNulo = new Cliente();
        clienteNulo.setUuid(null);
        clienteNulo.setNome(null);
        clienteNulo.setEmail(null);
        clienteNulo.setCpf(null);

        assertNull(clienteNulo.getUuid());
        assertNull(clienteNulo.getNome());
        assertNull(clienteNulo.getEmail());
        assertNull(clienteNulo.getCpf());
    }

//    @Test
//    public void deveTestarToString() {
//        String esperado = new Cliente (uuid=" + uuid.toString() + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ");
//        assertThat(cliente.toString()).isEqualTo(esperado);
//    }
}
