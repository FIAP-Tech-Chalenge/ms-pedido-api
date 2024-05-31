package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuscarListaPedidoRepository implements BuscaListaPedidoInterface {
    public final PedidosMongoRepository pedidosMongoRepository;

    private static List<ProdutoEntity> getProdutoEntities(Pedido pedidoCollection) {
        List<Produto> pedidosDoProduto = pedidoCollection.getProdutos();
        List<ProdutoEntity> produtosList = new ArrayList<>();
        for (Produto pedidoProdutoCollection : pedidosDoProduto) {
            ProdutoEntity produtoEntity = new ProdutoEntity(
                    pedidoProdutoCollection.getUuid(),
                    pedidoProdutoCollection.getNome(),
                    pedidoProdutoCollection.getQuantidade(),
                    pedidoProdutoCollection.getCategoria()
            );
            produtoEntity.setValor(pedidoProdutoCollection.getValor());
            produtosList.add(produtoEntity);
        }
        return produtosList;
    }

    @Override
    public List<PedidoEntity> findListaPedidos() {
        List<com.fiap.mspedidoapi.infra.collection.pedido.Pedido> pedidosModels = pedidosMongoRepository.findAll();
        List<PedidoEntity> pedidosEntities = new ArrayList<>();

        for (com.fiap.mspedidoapi.infra.collection.pedido.Pedido pedidoCollection : pedidosModels) {
            PedidoEntity pedidoEntity = new PedidoEntity(
                    pedidoCollection.getUuidPedido(),
                    pedidoCollection.getClienteUuid(),
                    pedidoCollection.getStatusPedido(),
                    pedidoCollection.getStatusPagamento(),
                    pedidoCollection.getTotal()
            );
            pedidoEntity.setPedidoId(pedidoCollection.getUuidPedido());
            pedidoEntity.setUuid(pedidoCollection.getId());
            pedidoEntity.setNumeroPedido(pedidoCollection.getNumeroPedido());

            List<ProdutoEntity> produtosList = getProdutoEntities(pedidoCollection);
            pedidoEntity.setProdutos(produtosList);

            pedidoEntity.setNumeroPedido(pedidoCollection.getNumeroPedido());
            pedidosEntities.add(pedidoEntity);
        }

        return pedidosEntities;
    }
}
