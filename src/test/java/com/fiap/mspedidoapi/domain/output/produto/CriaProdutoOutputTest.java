package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.produto.Produto;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CriaProdutoOutputTest {

    private Produto produto;
    private OutputStatus outputStatus;
    private CriaProdutoOutput criaProdutoOutput;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Produto Teste", 100.0f, "Descrição do Produto Teste", CategoriaEnum.LANCHE, 10);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        criaProdutoOutput = new CriaProdutoOutput(produto, outputStatus);
    }

    @Test
    public void deveInicializarCriaProdutoOutputComValoresCorretos() {
        assertThat(criaProdutoOutput.getProduto()).isEqualTo(produto);
        assertThat(criaProdutoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarProdutoComoBody() {
        assertThat(criaProdutoOutput.getBody()).isEqualTo(produto);
    }

    @Test
    public void devePermitirAtualizarProduto() {
        Produto novoProduto = new Produto("Produto Atualizado", 200.0f, "Nova descrição do produto", CategoriaEnum.BEBIDA, 20);
        criaProdutoOutput.setProduto(novoProduto);

        assertThat(criaProdutoOutput.getProduto()).isEqualTo(novoProduto);
        assertThat(criaProdutoOutput.getBody()).isEqualTo(novoProduto);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        criaProdutoOutput.setOutputStatus(novoOutputStatus);

        assertThat(criaProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
