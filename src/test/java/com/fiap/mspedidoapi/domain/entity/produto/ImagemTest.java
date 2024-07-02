package com.fiap.mspedidoapi.domain.entity.produto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ImagemTest {

    @Test
    public void deveInicializarImagemCorretamente() {
        String nome = "Imagem Teste";
        String url = "http://example.com/imagem.jpg";
        Imagem imagem = new Imagem(nome, url);

        assertThat(imagem.nome()).isEqualTo(nome);
        assertThat(imagem.url()).isEqualTo(url);
    }

    @Test
    public void deveTestarEqualsAndHashCode() {
        Imagem imagem1 = new Imagem("Imagem Teste", "http://example.com/imagem.jpg");
        Imagem imagem2 = new Imagem("Imagem Teste", "http://example.com/imagem.jpg");
        Imagem imagem3 = new Imagem("Outra Imagem", "http://example.com/outra_imagem.jpg");

        assertThat(imagem1).isEqualTo(imagem2);
        assertThat(imagem1).isNotEqualTo(imagem3);
        assertThat(imagem1.hashCode()).isEqualTo(imagem2.hashCode());
        assertThat(imagem1.hashCode()).isNotEqualTo(imagem3.hashCode());
    }

    @Test
    public void deveTestarToString() {
        Imagem imagem = new Imagem("Imagem Teste", "http://example.com/imagem.jpg");
        String esperado = "Imagem[nome=Imagem Teste, url=http://example.com/imagem.jpg]";

        assertThat(imagem.toString()).isEqualTo(esperado);
    }
}
