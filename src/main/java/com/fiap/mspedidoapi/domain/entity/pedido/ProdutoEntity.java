package com.fiap.mspedidoapi.domain.entity.pedido;

import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Data
@Getter
@Setter
public class ProdutoEntity {
    private final UUID uuid;
    private final Integer quantidade;
    private final String nome;
    private Float valor;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;


    public ProdutoEntity(UUID uuid, String nome, Integer quantidade, CategoriaEnum categoria) {
        this.uuid = uuid;
        this.nome = nome;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }
}
