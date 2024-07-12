package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import com.fiap.mspedidoapi.infra.adpter.repository.pedido.BuscarListaPedidoRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class BuscarListaPedidoRepositorySteps {

    private BuscarListaPedidoRepository repository;
    private PedidosMongoRepository pedidosMongoRepository;
    private List<PedidoEntity> pedidos;

    @Before
    public void setUp() {
        pedidosMongoRepository = Mockito.mock(PedidosMongoRepository.class);
        repository = new BuscarListaPedidoRepository(pedidosMongoRepository);
    }

    @Given("que existem pedidos no repositório")
    public void que_existem_pedidos_no_repositorio() {
        Produto produto1 = new Produto(UUID.randomUUID(), "Produto 1", 10.0f, CategoriaEnum.LANCHE, 2);
        Produto produto2 = new Produto(UUID.randomUUID(), "Produto 2", 15.0f, CategoriaEnum.BEBIDA, 1);

        Pedido pedido1 = new Pedido();
        pedido1.setUuidPedido(UUID.randomUUID());
        pedido1.setClienteUuid(UUID.randomUUID());
        pedido1.setProdutos(List.of(produto1, produto2));
        pedido1.setStatusPedido(StatusPedido.RECEBIDO);
        pedido1.setStatusPagamento(StatusPagamento.PAGO);
        pedido1.setTotal(35.0f);
        pedido1.setNumeroPedido(12345);

        when(pedidosMongoRepository.findAll()).thenReturn(List.of(pedido1));
    }

    @Given("que não existem pedidos no repositório")
    public void que_nao_existem_pedidos_no_repositorio() {
        when(pedidosMongoRepository.findAll()).thenReturn(List.of());
    }

    @When("a lista de pedidos é buscada")
    public void a_lista_de_pedidos_e_buscada() {
        pedidos = repository.findListaPedidos();
    }

    @Then("deve retornar uma lista de pedidos com sucesso")
    public void deve_retornar_uma_lista_de_pedidos_com_sucesso() {
        assertThat(pedidos).hasSize(1);
        PedidoEntity pedidoEntity = pedidos.get(0);
        Pedido pedido1 = pedidosMongoRepository.findAll().get(0);

        assertThat(pedidoEntity.getPedidoId()).isEqualTo(pedido1.getUuidPedido());
        assertThat(pedidoEntity.getClienteUuid()).isEqualTo(pedido1.getClienteUuid());
        assertThat(pedidoEntity.getStatusPedido()).isEqualTo(pedido1.getStatusPedido());
        assertThat(pedidoEntity.getStatusPagamento()).isEqualTo(pedido1.getStatusPagamento());
        assertThat(pedidoEntity.getTotal()).isEqualTo(pedido1.getTotal());
        assertThat(pedidoEntity.getNumeroPedido()).isEqualTo(pedido1.getNumeroPedido());

        List<ProdutoEntity> produtos = pedidoEntity.getProdutos();
        assertThat(produtos).hasSize(2);

        ProdutoEntity produtoEntity1 = produtos.get(0);
        Produto produto1 = pedido1.getProdutos().get(0);
        assertThat(produtoEntity1.getUuid()).isEqualTo(produto1.getUuid());
        assertThat(produtoEntity1.getNome()).isEqualTo(produto1.getNome());
        assertThat(produtoEntity1.getQuantidade()).isEqualTo(produto1.getQuantidade());
        assertThat(produtoEntity1.getValor()).isEqualTo(produto1.getValor());
        assertThat(produtoEntity1.getCategoria()).isEqualTo(produto1.getCategoria());

        ProdutoEntity produtoEntity2 = produtos.get(1);
        Produto produto2 = pedido1.getProdutos().get(1);
        assertThat(produtoEntity2.getUuid()).isEqualTo(produto2.getUuid());
        assertThat(produtoEntity2.getNome()).isEqualTo(produto2.getNome());
        assertThat(produtoEntity2.getQuantidade()).isEqualTo(produto2.getQuantidade());
        assertThat(produtoEntity2.getValor()).isEqualTo(produto2.getValor());
        assertThat(produtoEntity2.getCategoria()).isEqualTo(produto2.getCategoria());
    }

    @Then("deve retornar uma lista vazia")
    public void deve_retornar_uma_lista_vazia() {
        assertThat(pedidos).isEmpty();
    }
}
