package com.fiap.mspedidoapi.domain.output.produto;

import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.domain.generic.output.OutputStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class DeletaProdutoOutputTest {

    private ProdutoEntity produto;
    private OutputStatus outputStatus;
    private DeletaProdutoOutput deletaProdutoOutput;

    @BeforeEach
    public void setUp() {
        produto = new ProdutoEntity(UUID.randomUUID(), "Produto Teste", 10, CategoriaEnum.LANCHE);
        produto.setValor(100.0f);
        outputStatus = new OutputStatus(200, "SUCCESS", "Operação realizada com sucesso");
        deletaProdutoOutput = new DeletaProdutoOutput(produto, outputStatus);
    }

    @Test
    public void deveInicializarDeletaProdutoOutputComValoresCorretos() {
        assertThat(deletaProdutoOutput.getProduto()).isEqualTo(produto);
        assertThat(deletaProdutoOutput.getOutputStatus()).isEqualTo(outputStatus);
    }

    @Test
    public void deveRetornarProdutoComoBody() {
        assertThat(deletaProdutoOutput.getBody()).isEqualTo(produto);
    }

    @Test
    public void devePermitirAtualizarProduto() {
        ProdutoEntity novoProduto = new ProdutoEntity(UUID.randomUUID(), "Produto Atualizado", 20, CategoriaEnum.BEBIDA);
        novoProduto.setValor(200.0f);
        DeletaProdutoOutput novoDeletaProdutoOutput = new DeletaProdutoOutput(novoProduto, outputStatus);

        assertThat(novoDeletaProdutoOutput.getProduto()).isEqualTo(novoProduto);
        assertThat(novoDeletaProdutoOutput.getBody()).isEqualTo(novoProduto);
    }

    @Test
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        DeletaProdutoOutput novoDeletaProdutoOutput = new DeletaProdutoOutput(produto, novoOutputStatus);

        assertThat(novoDeletaProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }
}
