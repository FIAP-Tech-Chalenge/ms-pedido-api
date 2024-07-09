package com.fiap.mspedidoapi.domain.entity.pedido;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(produtoEntity).isNotNull();
        assertThat(produtoEntity.getUuid()).isEqualTo(uuid);
        assertThat(produtoEntity.getNome()).isEqualTo("Produto Teste");
        assertThat(produtoEntity.getQuantidade()).isEqualTo(5);
        assertThat(produtoEntity.getCategoria()).isEqualTo(CategoriaEnum.LANCHE);
        assertThat(produtoEntity.getValor()).isNull();
    }

    @Test
    public void testGettersAndSetters() {
        produtoEntity.setValor(99.99f);
        assertThat(produtoEntity.getValor()).isEqualTo(99.99f);

        produtoEntity.setCategoria(CategoriaEnum.BEBIDA);
        assertThat(produtoEntity.getCategoria()).isEqualTo(CategoriaEnum.BEBIDA);
    }

    @Test
    public void testNullValues() {
        ProdutoEntity nullProdutoEntity = new ProdutoEntity(null, null, null, null);
        assertThat(nullProdutoEntity.getUuid()).isNull();
        assertThat(nullProdutoEntity.getNome()).isNull();
        assertThat(nullProdutoEntity.getQuantidade()).isNull();
        assertThat(nullProdutoEntity.getCategoria()).isNull();
        assertThat(nullProdutoEntity.getValor()).isNull();
    }

    @Test
    public void testNegativeQuantity() {
        ProdutoEntity produtoComQuantidadeNegativa = new ProdutoEntity(UUID.randomUUID(), "Produto Quantidade Negativa", -10, CategoriaEnum.ACOMPANHAMENTO);
        assertThat(produtoComQuantidadeNegativa.getQuantidade()).isEqualTo(-10);
    }

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(produtoEntity).isEqualTo(produtoEntity);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        ProdutoEntity produto1 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        ProdutoEntity produto2 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        assertThat(produto1).isEqualTo(produto2);
        assertThat(produto2).isEqualTo(produto1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        ProdutoEntity produto1 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        ProdutoEntity produto2 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        ProdutoEntity produto3 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        assertThat(produto1).isEqualTo(produto2);
        assertThat(produto2).isEqualTo(produto3);
        assertThat(produto1).isEqualTo(produto3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(produtoEntity.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(produtoEntity.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaValoresDiferentes() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 10, CategoriaEnum.BEBIDA);
        assertThat(produtoEntity).isNotEqualTo(produtoDiferente);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        ProdutoEntity produto1 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        ProdutoEntity produto2 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        assertThat(produto1.hashCode()).isEqualTo(produto2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 10, CategoriaEnum.BEBIDA);
        assertThat(produtoEntity.hashCode()).isNotEqualTo(produtoDiferente.hashCode());
    }

    @Test
    public void toStringDeveRetornarStringCorreta() {
        String expectedToString = "ProdutoEntity(uuid=" + uuid + ", quantidade=5, nome=Produto Teste, valor=null, categoria=LANCHE)";
        assertThat(produtoEntity.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        ProdutoEntity produto1 = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.LANCHE);
        assertThat(produtoEntity.canEqual(produto1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(produtoEntity.canEqual(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaUuidDiferente() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Teste", 5, CategoriaEnum.LANCHE);
        assertThat(produtoEntity).isNotEqualTo(produtoDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaNomeDiferente() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(uuid, "Produto Diferente", 5, CategoriaEnum.LANCHE);
        assertThat(produtoEntity).isNotEqualTo(produtoDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaQuantidadeDiferente() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(uuid, "Produto Teste", 10, CategoriaEnum.LANCHE);
        assertThat(produtoEntity).isNotEqualTo(produtoDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaCategoriaDiferente() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(uuid, "Produto Teste", 5, CategoriaEnum.BEBIDA);
        assertThat(produtoEntity).isNotEqualTo(produtoDiferente);
    }

    @Test
    public void hashCodeDeveSerConsistenteComMesmosValores() {
        int initialHashCode = produtoEntity.hashCode();
        assertThat(produtoEntity.hashCode()).isEqualTo(initialHashCode);
    }
}
