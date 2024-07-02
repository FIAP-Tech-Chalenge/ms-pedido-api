package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.produto.Produto;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class BuscaProdutoOutputTest {

    private Produto produto;
    private OutputStatus outputStatus;
    private BuscaProdutoOutput buscaProdutoOutput;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Teste", 100.0f, "Descrição do Produto Teste", CategoriaEnum.LANCHE, 10);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        buscaProdutoOutput = new BuscaProdutoOutput(produto, outputStatus);
    }

    @Test
    public void deveInicializarBuscaProdutoOutputComValoresCorretos() {
        assertThat(buscaProdutoOutput.getProduto()).isEqualTo(produto);
        assertThat(buscaProdutoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarProdutoComoBody() {
        assertThat(buscaProdutoOutput.getBody()).isEqualTo(produto);
    }

    @Test
    public void devePermitirAtualizarProduto() {
        Produto novoProduto = new Produto("Produto Atualizado", 200.0f, "Nova descrição do produto", CategoriaEnum.BEBIDA, 20);
        buscaProdutoOutput.setProduto(novoProduto);

        assertThat(buscaProdutoOutput.getProduto()).isEqualTo(novoProduto);
        assertThat(buscaProdutoOutput.getBody()).isEqualTo(novoProduto);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        buscaProdutoOutput.setOutputStatus(novoOutputStatus);

        assertThat(buscaProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
