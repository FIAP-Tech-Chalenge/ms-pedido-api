package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoSteps {

    private Produto produto;
    private UUID uuid;

    @Before
    public void setUp() {
        uuid = UUID.randomUUID();
        produto = new Produto(uuid, "Produto Teste", 10.0f, CategoriaEnum.LANCHE, 2);
    }

    @Given("que um produto é inicializado")
    public void que_um_produto_é_inicializado() {
        setUp();
    }

    @Then("os atributos do produto devem ser corretamente definidos")
    public void os_atributos_do_produto_devem_ser_corretamente_definidos() {
        assertThat(produto.getUuid()).isEqualTo(uuid);
        assertThat(produto.getNome()).isEqualTo("Produto Teste");
        assertThat(produto.getValor()).isEqualTo(10.0f);
        assertThat(produto.getCategoria()).isEqualTo(CategoriaEnum.LANCHE);
        assertThat(produto.getQuantidade()).isEqualTo(2);
    }
}
