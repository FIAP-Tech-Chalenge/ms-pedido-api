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

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(buscaTodosProdutoOutput).isEqualTo(buscaTodosProdutoOutput);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        BuscaTodosProdutoOutput output1 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        BuscaTodosProdutoOutput output2 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        BuscaTodosProdutoOutput output1 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        BuscaTodosProdutoOutput output2 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        BuscaTodosProdutoOutput output3 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(buscaTodosProdutoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(buscaTodosProdutoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaListProdutosDiferentes() {
        List<Produto> produtosDiferentes = List.of(new Produto("Produto 3", 100.0f, "Descrição do Produto 3", CategoriaEnum.SOBREMESA, 15));
        BuscaTodosProdutoOutput outputDiferente = new BuscaTodosProdutoOutput(produtosDiferentes, outputStatus);
        assertThat(buscaTodosProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaTodosProdutoOutput outputDiferente = new BuscaTodosProdutoOutput(produtos, statusDiferente);
        assertThat(buscaTodosProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int hashCode1 = buscaTodosProdutoOutput.hashCode();
        int hashCode2 = buscaTodosProdutoOutput.hashCode();
        assertThat(hashCode1).isEqualTo(hashCode2);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        BuscaTodosProdutoOutput output1 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        BuscaTodosProdutoOutput output2 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaObjetosDiferentes() {
        List<Produto> produtosDiferentes = List.of(new Produto("Produto 3", 100.0f, "Descrição do Produto 3", CategoriaEnum.SOBREMESA, 15));
        BuscaTodosProdutoOutput outputDiferente = new BuscaTodosProdutoOutput(produtosDiferentes, outputStatus);
        assertThat(buscaTodosProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "BuscaTodosProdutoOutput(listProdutos=" + produtos + ", outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'})";
        assertThat(buscaTodosProdutoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void canEqualDeveRetornarTrueParaMesmoTipo() {
        BuscaTodosProdutoOutput output1 = new BuscaTodosProdutoOutput(produtos, outputStatus);
        assertThat(buscaTodosProdutoOutput.canEqual(output1)).isTrue();
    }

    @Test
    public void canEqualDeveRetornarFalseParaTipoDiferente() {
        assertThat(buscaTodosProdutoOutput.canEqual(new Object())).isFalse();
    }

    @Test
    public void deveInicializarComConstrutorSemArgumentos() {
        BuscaTodosProdutoOutput output = new BuscaTodosProdutoOutput();
        assertThat(output.getListProdutos()).isNull();
        assertThat(output.getOutputStatus()).isNull();
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputComListProdutosNulo() {
        BuscaTodosProdutoOutput outputComListProdutosNulo = new BuscaTodosProdutoOutput(null, outputStatus);
        assertThat(buscaTodosProdutoOutput).isNotEqualTo(outputComListProdutosNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputComOutputStatusNulo() {
        BuscaTodosProdutoOutput outputComOutputStatusNulo = new BuscaTodosProdutoOutput(produtos, null);
        assertThat(buscaTodosProdutoOutput).isNotEqualTo(outputComOutputStatusNulo);
    }

    @Test
    public void equalsDeveRetornarTrueParaObjetosComListProdutosENuloOutputStatusNulo() {
        BuscaTodosProdutoOutput output1 = new BuscaTodosProdutoOutput(null, null);
        BuscaTodosProdutoOutput output2 = new BuscaTodosProdutoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }
}
