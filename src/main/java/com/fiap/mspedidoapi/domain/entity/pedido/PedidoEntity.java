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
public class PedidoEntity {
    private final UUID clienteUuid;
    private UUID pedidoId;
    private String uuid;
    private int numeroPedido;
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    private List<ProdutoEntity> produtos;
    private Float total;

    public PedidoEntity(UUID clienteUuid) {
        this.clienteUuid = clienteUuid;
        this.produtos = new ArrayList<>();
    }

    public PedidoEntity(UUID pedidoId, UUID clienteId, StatusPedido statusPedido, StatusPagamento statusPagamento, Float valorTotal) {
        this.pedidoId = pedidoId;
        this.clienteUuid = clienteId;
        this.statusPedido = statusPedido;
        this.statusPagamento = statusPagamento;
        this.total = valorTotal;
        this.produtos = new ArrayList<>();
    }

    public void addProduto(ProdutoEntity produto) {
        produtos.add(produto);
    }

    public float valorTotalDoPedido() {
        float total = 0;
        for (ProdutoEntity produto : produtos) {
            total += produto.getValor() * produto.getQuantidade();
        }
        return total;
    }
}
