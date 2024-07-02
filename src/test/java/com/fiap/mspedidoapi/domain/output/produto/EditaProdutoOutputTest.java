package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.produto.Produto;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EditaProdutoOutputTest {

    private Produto produto;
    private OutputStatus outputStatus;
    private EditaProdutoOutput editaProdutoOutput;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Teste", 100.0f, "Descrição do Produto Teste", CategoriaEnum.LANCHE, 10);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        editaProdutoOutput = new EditaProdutoOutput(produto, outputStatus);
    }

    @Test
    public void deveInicializarEditaProdutoOutputComValoresCorretos() {
        assertThat(editaProdutoOutput.getProduto()).isEqualTo(produto);
        assertThat(editaProdutoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarProdutoComoBody() {
        assertThat(editaProdutoOutput.getBody()).isEqualTo(produto);
    }

    @Test
    public void devePermitirAtualizarProduto() {
        Produto novoProduto = new Produto("Produto Atualizado", 200.0f, "Nova descrição do produto", CategoriaEnum.BEBIDA, 20);
        EditaProdutoOutput novoEditaProdutoOutput = new EditaProdutoOutput(novoProduto, outputStatus);

        assertThat(novoEditaProdutoOutput.getProduto()).isEqualTo(novoProduto);
        assertThat(novoEditaProdutoOutput.getBody()).isEqualTo(novoProduto);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        EditaProdutoOutput novoEditaProdutoOutput = new EditaProdutoOutput(produto, novoOutputStatus);

        assertThat(novoEditaProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
