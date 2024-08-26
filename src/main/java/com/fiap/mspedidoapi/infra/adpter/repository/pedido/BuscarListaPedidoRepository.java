package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity;
import com.fiap.mspedidoapi.domain.entity.pedido.ProdutoEntity;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.mspedidoapi.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class BuscarListaPedidoRepository implements BuscaListaPedidoInterface {
    public final PedidosMongoRepository pedidosMongoRepository;

    @Override
    public Pedido encontraPedidoPorUuid(UUID pedidoUuid) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedidoEncontrado = pedidosMongoRepository.findByUuidPedido(pedidoUuid);
        if(pedidoEncontrado.isPresent()){
            return pedidoEncontrado.get();
        }else{
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
    }
    
    @Override
    public Entrega movePedidoParaEmPreparacao(UUID pedidoUuid, Integer tempoDePreparoEmMinutos) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedido = pedidosMongoRepository.findByUuidPedido(pedidoUuid);
        if(pedido.isPresent()){
            Pedido pedidoEncontrado = pedido.get();
            pedidoEncontrado.setTempoDePreparo(tempoDePreparoEmMinutos);
            pedidoEncontrado.setStatusPedido(StatusPedido.EM_PREPARACAO);
            pedidosMongoRepository.save(pedidoEncontrado);
            return new Entrega(pedidoEncontrado.getUuidPedido(), pedidoEncontrado.getNumeroPedido(), pedidoEncontrado.getStatusPedido());
        }else{
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
    }

    private static List<ProdutoEntity> getProdutoEntities(List<Produto>  produtosDoPedido) {
        List<ProdutoEntity> produtosList = new ArrayList<>();
        for (Produto produto : produtosDoPedido) {
            ProdutoEntity produtoEntity = new ProdutoEntity(
                    produto.getUuid(),
                    produto.getNome(),
                    produto.getQuantidade(),
                    produto.getCategoria()
            );
            produtoEntity.setValor(produto.getValor());
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
                    pedidoCollection.getTempoDePreparo(),
                    pedidoCollection.getTotal()
            );

            List<ProdutoEntity> produtosList = getProdutoEntities(pedidoCollection.getProdutos());

            pedidoEntity.setProdutos(produtosList);
            pedidoEntity.setPedidoId(pedidoCollection.getUuidPedido());
            pedidoEntity.setNumeroPedido(pedidoCollection.getNumeroPedido());
            pedidosEntities.add(pedidoEntity);
        }

        return pedidosEntities;
    }
}
