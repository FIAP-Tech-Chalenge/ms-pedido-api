package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.PedidoProntoRepository;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PedidoProntoRepositorySteps {

    private PedidosMongoRepository pedidosMongoRepository;
    private PedidoProntoRepository pedidoProntoRepository;
    private UUID uuidPedido;
    private Entrega entrega;

    @Before
    public void setUp() {
        pedidosMongoRepository = Mockito.mock(PedidosMongoRepository.class);
        pedidoProntoRepository = new PedidoProntoRepository(pedidosMongoRepository);
    }

    @Given("que o pedido com uuid {string} está com status {string}")
    public void que_o_pedido_com_uuid_está_com_status(String uuid, String status) {
        uuidPedido = UUID.fromString(uuid);
        Pedido pedido = new Pedido();
        pedido.setUuidPedido(uuidPedido);
        pedido.setNumeroPedido(123);
        pedido.setStatusPedido(StatusPedido.valueOf(status));

        when(pedidosMongoRepository.findByUuidPedido(uuidPedido)).thenReturn(Optional.of(pedido));
    }

    @Given("que o pedido com uuid {string} não existe")
    public void que_o_pedido_com_uuid_não_existe(String uuid) {
        uuidPedido = UUID.fromString(uuid);
        when(pedidosMongoRepository.findByUuidPedido(uuidPedido)).thenReturn(Optional.empty());
    }

    @When("o status do pedido é atualizado para pronto")
    public void o_status_do_pedido_é_atualizado_para_pronto() {
        entrega = pedidoProntoRepository.atualizaStatusPedido(uuidPedido);
    }

    @Then("deve retornar a entrega com sucesso")
    public void deve_retornar_a_entrega_com_sucesso() {
        assertThat(entrega).isNotNull();
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(123);
        assertThat(entrega.getStatusPedido()).isEqualTo(StatusPedido.PRONTO);

        verify(pedidosMongoRepository, never()).save(any(Pedido.class));
    }

    @Then("deve atualizar o status do pedido para {string}")
    public void deve_atualizar_o_status_do_pedido_para(String status) {
        assertThat(entrega).isNotNull();
        assertThat(entrega.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(entrega.getNumeroPedido()).isEqualTo(123);
        assertThat(entrega.getStatusPedido()).isEqualTo(StatusPedido.valueOf(status));

        ArgumentCaptor<Pedido> captor = ArgumentCaptor.forClass(Pedido.class);
        verify(pedidosMongoRepository).save(captor.capture());
        Pedido savedPedido = captor.getValue();
        assertThat(savedPedido.getStatusPedido()).isEqualTo(StatusPedido.valueOf(status));
    }

    @Then("deve retornar null")
    public void deve_retornar_null() {
        assertThat(entrega).isNull();
        verify(pedidosMongoRepository, never()).save(any(Pedido.class));
    }
}
