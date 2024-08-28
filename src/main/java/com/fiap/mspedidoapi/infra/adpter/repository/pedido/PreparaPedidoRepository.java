package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.mspedidoapi.domain.gateway.pedido.PreparaPedidoInterface;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PreparaPedidoRepository implements PreparaPedidoInterface{
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
}
