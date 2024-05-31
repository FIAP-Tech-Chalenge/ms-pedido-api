package com.fiap.mspedidoapi.infra.collection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pedido")
@Getter
@Setter
public class Pedido {
    @Id
    private String id;
    private String name;
}
