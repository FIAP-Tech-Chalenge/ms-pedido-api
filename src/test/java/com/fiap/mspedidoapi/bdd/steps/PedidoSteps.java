package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class PedidoSteps {

    private Pedido pedido;
    private UUID uuidPedido;
    private UUID clienteUuid;
    private Produto produto1;
    private Produto produto2;
    private Produto novoProduto;
    private UUID novoUuidPedido;
    private UUID novoClienteUuid;

    @Before
    public void setUp() {
        uuidPedido = UUID.randomUUID();
        clienteUuid = UUID.randomUUID();
        produto1 = new Produto(UUID.randomUUID(), "Produto 1", 10.0f, CategoriaEnum.LANCHE, 2);
        produto2 = new Produto(UUID.randomUUID(), "Produto 2", 15.0f, CategoriaEnum.BEBIDA, 1);

        pedido = new Pedido();
        pedido.setId("12345");
        pedido.setUuidPedido(uuidPedido);
        pedido.setClienteUuid(clienteUuid);
        pedido.setNumeroPedido(12345);
        pedido.setStatusPedido(StatusPedido.RECEBIDO);
        pedido.setStatusPagamento(StatusPagamento.PAGO);
        pedido.setTotal(35.0f);
        pedido.setProdutos(List.of(produto1, produto2));
    }

    @Given("que um pedido é inicializado")
    public void que_um_pedido_é_inicializado() {
        setUp();
    }

    @Then("os atributos do pedido devem ser corretamente definidos")
    public void os_atributos_do_pedido_devem_ser_corretamente_definidos() {
        assertThat(pedido.getId()).isEqualTo("12345");
        assertThat(pedido.getUuidPedido()).isEqualTo(uuidPedido);
        assertThat(pedido.getClienteUuid()).isEqualTo(clienteUuid);
        assertThat(pedido.getNumeroPedido()).isEqualTo(12345);
        assertThat(pedido.getStatusPedido()).isEqualTo(StatusPedido.RECEBIDO);
        assertThat(pedido.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
        assertThat(pedido.getTotal()).isEqualTo(35.0f);
        assertThat(pedido.getProdutos()).containsExactly(produto1, produto2);
    }

    @When("os atributos do pedido são atualizados")
    public void os_atributos_do_pedido_são_atualizados() {
        novoUuidPedido = UUID.randomUUID();
        novoClienteUuid = UUID.randomUUID();
        novoProduto = new Produto(UUID.randomUUID(), "Produto 3", 20.0f, CategoriaEnum.ACOMPANHAMENTO, 3);

        pedido.setUuidPedido(novoUuidPedido);
        pedido.setClienteUuid(novoClienteUuid);
        pedido.setNumeroPedido(67890);
        pedido.setStatusPedido(StatusPedido.EM_PREPARACAO);
        pedido.setStatusPagamento(StatusPagamento.AGUARDANDO_PAGAMENTO);
        pedido.setTotal(60.0f);
        pedido.setProdutos(List.of(novoProduto));
    }

    @Then("os novos atributos do pedido devem ser corretamente definidos")
    public void os_novos_atributos_do_pedido_devem_ser_corretamente_definidos() {
        assertThat(pedido.getUuidPedido()).isEqualTo(novoUuidPedido);
        assertThat(pedido.getClienteUuid()).isEqualTo(novoClienteUuid);
        assertThat(pedido.getNumeroPedido()).isEqualTo(67890);
        assertThat(pedido.getStatusPedido()).isEqualTo(StatusPedido.EM_PREPARACAO);
        assertThat(pedido.getStatusPagamento()).isEqualTo(StatusPagamento.AGUARDANDO_PAGAMENTO);
        assertThat(pedido.getTotal()).isEqualTo(60.0f);
        assertThat(pedido.getProdutos()).containsExactly(novoProduto);
    }

}
