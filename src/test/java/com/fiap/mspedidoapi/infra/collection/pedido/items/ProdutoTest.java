package com.fiap.mspedidoapi.infra.collection.pedido.items;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ProdutoTest {

    private Produto produto;
    private UUID uuid;

    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID();
        produto = new Produto(uuid, "Produto Teste", 10.0f, CategoriaEnum.LANCHE, 2);
    }

    @Test
    public void deveInicializarProdutoCorretamente() {
        assertThat(produto.getUuid()).isEqualTo(uuid);
        assertThat(produto.getNome()).isEqualTo("Produto Teste");
        assertThat(produto.getValor()).isEqualTo(10.0f);
        assertThat(produto.getCategoria()).isEqualTo(CategoriaEnum.LANCHE);
        assertThat(produto.getQuantidade()).isEqualTo(2);
    }
}
