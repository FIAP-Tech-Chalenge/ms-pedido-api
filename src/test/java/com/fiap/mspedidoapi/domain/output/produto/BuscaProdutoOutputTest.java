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
    public void deveInicializarComConstrutorSemArgumentos() {
        BuscaProdutoOutput output = new BuscaProdutoOutput();
        assertThat(output.getProduto()).isNull();
        assertThat(output.getOutputStatus()).isNull();
    }

    @Test
    public void deveUsarLombokGettersESetters() {
        BuscaProdutoOutput output = new BuscaProdutoOutput();
        Produto produto = new Produto("Produto Teste", 100.0f, "Descrição", CategoriaEnum.LANCHE, 10);
        OutputStatus status = new OutputStatus(200, "SUCCESS", "Operação bem-sucedida");

        output.setProduto(produto);
        output.setOutputStatus(status);

        assertThat(output.getProduto()).isEqualTo(produto);
        assertThat(output.getOutputStatus()).isEqualTo(status);
    }

    @Test
    public void deveCompararObjetosCorretamente() {
        Produto produto1 = new Produto("Produto Teste", 100.0f, "Descrição", CategoriaEnum.LANCHE, 10);
        Produto produto2 = new Produto("Produto Teste", 100.0f, "Descrição", CategoriaEnum.LANCHE, 10);
        OutputStatus status1 = new OutputStatus(200, "SUCCESS", "Operação bem-sucedida");
        OutputStatus status2 = new OutputStatus(200, "SUCCESS", "Operação bem-sucedida");

        BuscaProdutoOutput output1 = new BuscaProdutoOutput(produto1, status1);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produto2, status2);

        assertThat(output1).isEqualTo(output2);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
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

    @Test
    public void deveTerToStringCorreto() {
        String expectedToString = "BuscaProdutoOutput(produto=Produto(uuid=null, nome=Produto Teste, valor=100.0, descricao=Descrição do Produto Teste, categoria=LANCHE, quantidade=10, imagens=null), outputStatus=OutputStatus{code=200, codeName='SUCCESS', message='Operação realizada com sucesso'})";
        assertThat(buscaProdutoOutput.toString()).isEqualTo(expectedToString);
    }

    @Test
    public void equalsDeveSerSimetrico() {
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(produto, outputStatus);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output1);
    }

    @Test
    public void equalsDeveSerReflexivo() {
        assertThat(buscaProdutoOutput).isEqualTo(buscaProdutoOutput);
    }

    @Test
    public void equalsDeveSerTransitivo() {
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(produto, outputStatus);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produto, outputStatus);
        BuscaProdutoOutput output3 = new BuscaProdutoOutput(produto, outputStatus);
        assertThat(output1).isEqualTo(output2);
        assertThat(output2).isEqualTo(output3);
        assertThat(output1).isEqualTo(output3);
    }

    @Test
    public void equalsDeveRetornarFalseParaObjetosDiferentes() {
        Produto outroProduto = new Produto("Outro Produto", 200.0f, "Outra descrição", CategoriaEnum.BEBIDA, 5);
        OutputStatus outroStatus = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(outroProduto, outroStatus);
        assertThat(buscaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void hashCodeDeveSerConsistente() {
        int initialHashCode = buscaProdutoOutput.hashCode();
        assertThat(buscaProdutoOutput.hashCode()).isEqualTo(initialHashCode);
    }

    @Test
    public void hashCodeDeveSerIgualParaObjetosIguais() {
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(produto, outputStatus);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produto, outputStatus);
        assertThat(output1.hashCode()).isEqualTo(output2.hashCode());
    }

    @Test
    public void equalsDeveRetornarFalseParaNulo() {
        assertThat(buscaProdutoOutput.equals(null)).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaClasseDiferente() {
        assertThat(buscaProdutoOutput.equals(new Object())).isFalse();
    }

    @Test
    public void equalsDeveRetornarFalseParaDiferentesCampos() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Mensagem de erro");
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produtoDiferente, statusDiferente);

        assertThat(buscaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNulo() {
        BuscaProdutoOutput outputComProdutoNulo = new BuscaProdutoOutput(null, outputStatus);
        assertThat(buscaProdutoOutput).isNotEqualTo(outputComProdutoNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNulo() {
        BuscaProdutoOutput outputComStatusNulo = new BuscaProdutoOutput(produto, null);
        assertThat(buscaProdutoOutput).isNotEqualTo(outputComStatusNulo);
    }

    @Test
    public void hashCodeDeveSerDiferenteParaCamposDiferentes() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Mensagem de erro");
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produtoDiferente, statusDiferente);

        assertThat(buscaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoDiferente() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(buscaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produto, statusDiferente);
        assertThat(buscaProdutoOutput).isNotEqualTo(outputDiferente);
    }

    @Test
    public void hashCodeDeveSerDiferenteParaProdutoDiferente() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produtoDiferente, outputStatus);
        assertThat(buscaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void hashCodeDeveSerDiferenteParaOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaProdutoOutput outputDiferente = new BuscaProdutoOutput(produto, statusDiferente);
        assertThat(buscaProdutoOutput.hashCode()).isNotEqualTo(outputDiferente.hashCode());
    }

    @Test
    public void equalsDeveRetornarTrueParaMesmoObjeto() {
        assertThat(buscaProdutoOutput).isEqualTo(buscaProdutoOutput);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNuloEOutputStatusNulo() {
        BuscaProdutoOutput outputComProdutoNulo = new BuscaProdutoOutput(null, outputStatus);
        BuscaProdutoOutput outputComStatusNulo = new BuscaProdutoOutput(produto, null);
        assertThat(outputComProdutoNulo).isNotEqualTo(outputComStatusNulo);
    }

    @Test
    public void equalsDeveRetornarFalseParaCamposNulosDiferentes() {
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(null, outputStatus);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produto, null);
        assertThat(output1).isNotEqualTo(output2);
    }

    @Test
    public void hashCodeDeveSerConsistenteParaCamposNulos() {
        BuscaProdutoOutput outputComProdutoNulo = new BuscaProdutoOutput(null, outputStatus);
        BuscaProdutoOutput outputComStatusNulo = new BuscaProdutoOutput(produto, null);

        int hash1 = outputComProdutoNulo.hashCode();
        int hash2 = outputComProdutoNulo.hashCode();
        int hash3 = outputComStatusNulo.hashCode();

        assertThat(hash1).isEqualTo(hash2);
        assertThat(hash1).isNotEqualTo(hash3);
    }

    @Test
    public void equalsDeveRetornarFalseParaProdutoNullEOutputStatusDiferente() {
        OutputStatus statusDiferente = new OutputStatus(500, "ERROR", "Erro ao realizar a operação");
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(null, outputStatus);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(null, statusDiferente);
        assertThat(output1).isNotEqualTo(output2);
    }

    @Test
    public void equalsDeveRetornarFalseParaOutputStatusNullEProdutoDiferente() {
        Produto produtoDiferente = new Produto("Produto Diferente", 200.0f, "Descrição Diferente", CategoriaEnum.BEBIDA, 5);
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(produto, null);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(produtoDiferente, null);
        assertThat(output1).isNotEqualTo(output2);
    }

    @Test
    public void equalsDeveRetornarFalseParaObjetosDeClassesDiferentes() {
        assertThat(buscaProdutoOutput).isNotEqualTo(new Object());
    }

    @Test
    public void equalsDeveRetornarTrueParaTodosOsCamposNulos() {
        BuscaProdutoOutput output1 = new BuscaProdutoOutput(null, null);
        BuscaProdutoOutput output2 = new BuscaProdutoOutput(null, null);
        assertThat(output1).isEqualTo(output2);
    }
}
