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
    public void devePermitirAtualizarOutputStatus() {
        OutputStatus novoOutputStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        deletaProdutoOutput.setOutputStatus(novoOutputStatus);

        assertThat(deletaProdutoOutput.getOutputStatus()).isEqualTo(novoOutputStatus);
    }

    @Test
    public void devePermitirAtualizarProduto() {
        ProdutoEntity novoProduto = new ProdutoEntity(UUID.randomUUID(), "Produto Atualizado", 20, CategoriaEnum.BEBIDA);
        novoProduto.setValor(200.0f);
        deletaProdutoOutput.setProduto(novoProduto);

        assertThat(deletaProdutoOutput.getProduto()).isEqualTo(novoProduto);
        assertThat(deletaProdutoOutput.getBody()).isEqualTo(novoProduto);
    }

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(deletaProdutoOutput).isEqualTo(deletaProdutoOutput);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(produto, outputStatus);
        DeletaProdutoOutput output2 = new DeletaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(produto, outputStatus);
        DeletaProdutoOutput output2 = new DeletaProdutoOutput(produto, outputStatus);
        DeletaProdutoOutput output3 = new DeletaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(deletaProdutoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(deletaProdutoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutosDiferentes() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 20, CategoriaEnum.BEBIDA);
        produtoDiferente.setValor(200.0f);
        DeletaProdutoOutput outputDiferente = new DeletaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(deletaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        DeletaProdutoOutput outputDiferente = new DeletaProdutoOutput(produto, statusDiferente);
        assertThat(deletaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNulo() {
        DeletaProdutoOutput outputComProdutoNulo = new DeletaProdutoOutput(null, outputStatus);
        assertThat(deletaProdutoOutput).isNotEqualTo(outputComProdutoNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNulo() {
        DeletaProdutoOutput outputComStatusNulo = new DeletaProdutoOutput(produto, null);
        assertThat(deletaProdutoOutput).isNotEqualTo(outputComStatusNulo);
    }

    @Test
    public void equalsDeveRetornarTrueParaTodosOsCamposNulos() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(null, null);
        DeletaProdutoOutput output2 = new DeletaProdutoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int hashCode1 = deletaProdutoOutput.hashCode();
        int hashCode2 = deletaProdutoOutput.hashCode();
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(produto, outputStatus);
        DeletaProdutoOutput output2 = new DeletaProdutoOutput(produto, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 20, CategoriaEnum.BEBIDA);
        produtoDiferente.setValor(200.0f);
        DeletaProdutoOutput outputDiferente = new DeletaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(deletaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "DeletaProdutoOutput(outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'}, produto=ProdutoEntity(uuid=" + produto.getUuid() + ", quantidade=" + produto.getQuantidade() + ", nome=" + produto.getNome() + ", valor=" + produto.getValor() + ", categoria=" + produto.getCategoria() + "))";
        assertThat(deletaProdutoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(produto, outputStatus);
        assertThat(deletaProdutoOutput.canEqual(output1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(deletaProdutoOutput.canEqual(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNullEOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        DeletaProdutoOutput outputComProdutoNulo = new DeletaProdutoOutput(null, outputStatus);
        DeletaProdutoOutput outputComStatusDiferente = new DeletaProdutoOutput(null, statusDiferente);
        assertThat(outputComProdutoNulo).isNotEqualTo(outputComStatusDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNullEProdutoDiferente() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 20, CategoriaEnum.BEBIDA);
        produtoDiferente.setValor(200.0f);
        DeletaProdutoOutput outputComProdutoDiferente = new DeletaProdutoOutput(produtoDiferente, null);
        DeletaProdutoOutput outputComStatusNull = new DeletaProdutoOutput(produto, null);
        assertThat(outputComProdutoDiferente).isNotEqualTo(outputComStatusNull);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNullEOutputStatusNull() {
        DeletaProdutoOutput output1 = new DeletaProdutoOutput(null, null);
        DeletaProdutoOutput output2 = new DeletaProdutoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerDiferenteParaProdutoDiferenteEOutputStatusIgual() {
        ProdutoEntity produtoDiferente = new ProdutoEntity(UUID.randomUUID(), "Produto Diferente", 20, CategoriaEnum.BEBIDA);
        produtoDiferente.setValor(200.0f);
        DeletaProdutoOutput outputDiferente = new DeletaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(deletaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaProdutoIgualEOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        DeletaProdutoOutput outputDiferente = new DeletaProdutoOutput(produto, statusDiferente);
        assertThat(deletaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

}
