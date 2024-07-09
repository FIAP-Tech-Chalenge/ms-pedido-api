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

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(criaProdutoOutput).isEqualTo(criaProdutoOutput);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        CriaProdutoOutput output1 = new CriaProdutoOutput(produto, outputStatus);
        CriaProdutoOutput output2 = new CriaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        CriaProdutoOutput output1 = new CriaProdutoOutput(produto, outputStatus);
        CriaProdutoOutput output2 = new CriaProdutoOutput(produto, outputStatus);
        CriaProdutoOutput output3 = new CriaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(criaProdutoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(criaProdutoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutosDiferentes() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        CriaProdutoOutput outputDiferente = new CriaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(criaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        CriaProdutoOutput outputDiferente = new CriaProdutoOutput(produto, statusDiferente);
        assertThat(criaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNulo() {
        CriaProdutoOutput outputComProdutoNulo = new CriaProdutoOutput(null, outputStatus);
        assertThat(criaProdutoOutput).isNotEqualTo(outputComProdutoNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNulo() {
        CriaProdutoOutput outputComStatusNulo = new CriaProdutoOutput(produto, null);
        assertThat(criaProdutoOutput).isNotEqualTo(outputComStatusNulo);
    }

    @Test
    public void equalsDeveRetornarTrueParaTodosOsCamposNulos() {
        CriaProdutoOutput output1 = new CriaProdutoOutput(null, null);
        CriaProdutoOutput output2 = new CriaProdutoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int hashCode1 = criaProdutoOutput.hashCode();
        int hashCode2 = criaProdutoOutput.hashCode();
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        CriaProdutoOutput output1 = new CriaProdutoOutput(produto, outputStatus);
        CriaProdutoOutput output2 = new CriaProdutoOutput(produto, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        CriaProdutoOutput outputDiferente = new CriaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(criaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "CriaProdutoOutput(produto=" + produto + ", outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'})";
        assertThat(criaProdutoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        CriaProdutoOutput output1 = new CriaProdutoOutput(produto, outputStatus);
        assertThat(criaProdutoOutput.canEqual(output1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(criaProdutoOutput.canEqual(new Object())).isFalse();
    }
}
