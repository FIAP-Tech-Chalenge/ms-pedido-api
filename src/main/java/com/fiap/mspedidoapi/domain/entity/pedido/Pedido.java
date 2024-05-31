package com.fiap.mspedidoapi.domain.entity.pedido;

import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Pedido {
    private final UUID clienteUuid;
    private UUID pedidoId;
    private UUID uuid;
    private Long numeroPedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private List<Produto> produtos;
    private Float total;

    public Pedido(UUID clienteUuid) {
        this.clienteUuid = clienteUuid;
        this.produtos = new ArrayList<>();
    }

    public Pedido(UUID pedidoId, UUID clienteId, StatusPedido statusPedido, StatusPagamento statusPagamento, Float valorTotal) {
        this.pedidoId = pedidoId;
        this.clienteUuid = clienteId;
        this.statusPedido = statusPedido;
        this.statusPagamento = statusPagamento;
        this.total = valorTotal;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public float valorTotalDoPedido() {
        float total = (float) 0;
        for (Produto produto : produtos) {
            total += produto.getValor() * produto.getQuantidade();
        }

        return total;
    }

}
