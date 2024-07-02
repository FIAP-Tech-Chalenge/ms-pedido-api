package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.produto.Produto;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BuscaTodosProdutoOutputTest {

    private List<Produto> produtos;
    private OutputStatus outputStatus;
    private BuscaTodosProdutoOutput buscaTodosProdutoOutput;

    @BeforeEach
    public void setUp() {
        Produto produto1 = new Produto("Produto 1", 50.0f, "Descrição do Produto 1", CategoriaEnum.LANCHE, 5);
        Produto produto2 = new Produto("Produto 2", 75.0f, "Descrição do Produto 2", CategoriaEnum.BEBIDA, 10);
        produtos = List.of(produto1, produto2);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        buscaTodosProdutoOutput = new BuscaTodosProdutoOutput(produtos, outputStatus);
    }

    @Test
    public void deveInicializarBuscaTodosProdutoOutputComValoresCorretos() {
        assertThat(buscaTodosProdutoOutput.getListProdutos()).isEqualTo(produtos);
        assertThat(buscaTodosProdutoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarListProdutosComoBody() {
        assertThat(buscaTodosProdutoOutput.getBody()).isEqualTo(produtos);
    }

    @Test
    public void devePermitirAtualizarListProdutos() {
        Produto novoProduto = new Produto("Produto 3", 100.0f, "Descrição do Produto 3", CategoriaEnum.SOBREMESA, 15);
        List<Produto> novosProdutos = List.of(novoProduto);
        buscaTodosProdutoOutput.setListProdutos(novosProdutos);

        assertThat(buscaTodosProdutoOutput.getListProdutos()).isEqualTo(novosProdutos);
        assertThat(buscaTodosProdutoOutput.getBody()).isEqualTo(novosProdutos);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        buscaTodosProdutoOutput.setOutputStatus(novoOutputStatus);

        assertThat(buscaTodosProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
