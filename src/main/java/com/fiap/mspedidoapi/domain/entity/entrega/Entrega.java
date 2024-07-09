package com.fiap.mspedidoapi.domain.entity.entrega;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return numeroPedido == entrega.numeroPedido && Objects.equals(uuidPedido, entrega.uuidPedido) && statusPedido == entrega.statusPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuidPedido, numeroPedido, statusPedido);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "uuidPedido=" + uuidPedido +
                ", numeroPedido=" + numeroPedido +
                ", statusPedido=" + statusPedido +
                '}';
    }
}
