package com.fiap.mspedidoapi.domain.entity.produto;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProdutoTest {
    private Produto produto;
    private UUID uuid;
    private String nome;
    private Float valor;
    private String descricao;
    private CategoriaEnum categoria;
    private Integer quantidade;
    private List<Imagem> imagens;

    @BeforeEach
    public void setUp() {
        uuid = UUID.randomUUID();
        nome = "Produto Teste";
        valor = 99.99f;
        descricao = "Descrição do Produto Teste";
        categoria = CategoriaEnum.LANCHE;
        quantidade = 10;
        imagens = List.of(new Imagem("Imagem 1", "url1"), new Imagem("Imagem 2", "url2"));
        produto = new Produto(nome, valor, descricao, categoria, quantidade);
        produto.setImagens(imagens);
    }

    @Test
    public void deveInicializarProdutoComConstrutorCompleto() {
        assertThat(produto.getNome()).isEqualTo(nome);
        assertThat(produto.getValor()).isEqualTo(valor);
        assertThat(produto.getDescricao()).isEqualTo(descricao);
        assertThat(produto.getCategoria()).isEqualTo(categoria);
        assertThat(produto.getQuantidade()).isEqualTo(quantidade);
        assertNull(produto.getUuid());
        assertThat(produto.getImagens()).isEqualTo(imagens);
    }

//    @Test
//    public void deveInicializarProdutoComUUIDECategoria() {
//        Produto produtoComUuidECategoria = new Produto(uuid, 5, CategoriaEnum.BEBIDA);
//        assertThat(produtoComUuidECategoria.getUuid()).isEqualTo(uuid);
//        assertThat(produtoComUuidECategoria.getQuantidade()).isEqualTo(5);
//        assertThat(produtoComUuidECategoria.getCategoria()).isEqualTo(CategoriaEnum.BEBIDA);
//    }

    @Test
    public void devePermitirAtualizarValores() {
        UUID novoUuid = UUID.randomUUID();
        String novoNome = "Produto Atualizado";
        Float novoValor = 199.99f;
        String novaDescricao = "Nova descrição do produto";
        CategoriaEnum novaCategoria = CategoriaEnum.BEBIDA;
        Integer novaQuantidade = 20;
        List<Imagem> novasImagens = List.of(new Imagem("Imagem 3", "url3"), new Imagem("Imagem 4", "url4"));

        produto.setUuid(novoUuid);
        produto.setNome(novoNome);
        produto.setValor(novoValor);
        produto.setDescricao(novaDescricao);
        produto.setCategoria(novaCategoria);
        produto.setQuantidade(novaQuantidade);
        produto.setImagens(novasImagens);

        assertThat(produto.getUuid()).isEqualTo(novoUuid);
        assertThat(produto.getNome()).isEqualTo(novoNome);
        assertThat(produto.getValor()).isEqualTo(novoValor);
        assertThat(produto.getDescricao()).isEqualTo(novaDescricao);
        assertThat(produto.getCategoria()).isEqualTo(novaCategoria);
        assertThat(produto.getQuantidade()).isEqualTo(novaQuantidade);
        assertThat(produto.getImagens()).isEqualTo(novasImagens);
    }

    @Test
    public void devePermitirValoresNulos() {
        Produto produtoNulo = new Produto(null, null, null, null, null);
        assertNull(produtoNulo.getNome());
        assertNull(produtoNulo.getValor());
        assertNull(produtoNulo.getDescricao());
        assertNull(produtoNulo.getCategoria());
        assertNull(produtoNulo.getQuantidade());
        assertNull(produtoNulo.getUuid());
        assertNull(produtoNulo.getImagens());
    }

    @Test
    public void deveTestarToString() {
        produto.setUuid(uuid);
        String esperado = "Produto(uuid=" + uuid.toString() + ", nome=" + nome + ", valor=" + valor +
                ", descricao=" + descricao + ", categoria=" + categoria + ", quantidade=" + quantidade +
                ", imagens=" + imagens + ")";

        assertThat(produto.toString()).isEqualTo(esperado);
    }
}
