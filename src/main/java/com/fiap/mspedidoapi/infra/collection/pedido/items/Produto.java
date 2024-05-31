package com.fiap.mspedidoapi.infra.collection.pedido.items;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Produto {
    private final UUID uuid;
    private final Float valor;
    private final CategoriaEnum categoria;
    private final Integer quantidade;
    private final String nome;

    public Produto(UUID uuid, String nome, Float valor, CategoriaEnum categoria, Integer quantidade) {
        this.nome = nome;
        this.valor = valor;
        this.uuid = uuid;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }
}
