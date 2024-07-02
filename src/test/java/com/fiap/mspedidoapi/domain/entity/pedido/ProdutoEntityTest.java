package com.fiap.mspedidoapi.domain.entity.pedido;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoEntityTest {
    private ProdutoEntity produtoEntity;
    private UUID uuid;

    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID();
        produtoEntity = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
    }

    @Test
    public void testConstructor() {
        assertNotNull(produtoEntity);
        assertEquals(uuid, produtoEntity.getUuid());
        assertEquals("Produto Teste", produtoEntity.getNome());
        assertEquals(5, produtoEntity.getQuantidade());
        assertEquals(CategoriaEnum.LANCHE, produtoEntity.getCategoria());
        assertNull(produtoEntity.getValor());
    }

    @Test
    public void testGettersAndSetters() {
        produtoEntity.setValor(99.99f);
        assertEquals(99.99f, produtoEntity.getValor());

        produtoEntity.setCategoria(CategoriaEnum.BEBIDA);
        assertEquals(CategoriaEnum.BEBIDA, produtoEntity.getCategoria());
    }

    @Test
    public void testNullValues() {
        ProdutoEntity nullProdutoEntity = new ProdutoEntity(null, null, null, null);
        assertNull(nullProdutoEntity.getUuid());
        assertNull(nullProdutoEntity.getNome());
        assertNull(nullProdutoEntity.getQuantidade());
        assertNull(nullProdutoEntity.getCategoria());
        assertNull(nullProdutoEntity.getValor());
    }

    @Test
    public void testNegativeQuantity() {
        ProdutoEntity produtoComQuantidadeNegativa = new ProdutoEntity(UUID.randomUUID(), "Produto Quantidade Negativa", -10, CategoriaEnum.ACOMPANHAMENTO);
        assertEquals(-10, produtoComQuantidadeNegativa.getQuantidade());
    }
}
