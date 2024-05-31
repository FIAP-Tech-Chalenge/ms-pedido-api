package com.fiap.mspedidoapi.infra.collection.pedido;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.UUID;

@Document(collection = "pedido")
@Getter
@Setter
public class Pedido {
    @Id
    private String id;
    @Field("uuid_pedido")
    private UUID uuidPedido;
    @Field("uuid_cliente")
    private UUID clienteUuid;
    @Field("numero_pedido")
    private int numeroPedido;
    @Field("status_pedido")
    private StatusPedido statusPedido;
    @Field("status_pagamento")
    private StatusPagamento statusPagamento;
    private Float total;
    private List<Produto> produtos;
}
