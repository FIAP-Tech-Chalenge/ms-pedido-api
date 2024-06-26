package com.fiap.mspedidoapi.domain.entity.entrega;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Entrega {
    private UUID uuidPedido;
    private int numeroPedido;
    private StatusPedido statusPedido;

    public Entrega(UUID uuidPedido, int numeroPedido, StatusPedido statusPedido) {
        this.uuidPedido = uuidPedido;
        this.numeroPedido = numeroPedido;
        this.statusPedido = statusPedido;
    }
}
