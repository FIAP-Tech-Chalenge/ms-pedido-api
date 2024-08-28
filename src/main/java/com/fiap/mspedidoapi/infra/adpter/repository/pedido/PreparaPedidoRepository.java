package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

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
    public com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity encontraPedidoPorUuid(UUID pedidoUuid) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedidoEncontrado = pedidosMongoRepository.findByUuidPedido(pedidoUuid);
        if(pedidoEncontrado.isPresent()){
            Pedido pedidoModel = pedidoEncontrado.get();
            return new com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity(
                pedidoModel.getUuidPedido(),
                pedidoModel.getClienteUuid(),
                pedidoModel.getStatusPedido(),
                pedidoModel.getStatusPagamento(),
                pedidoModel.getTempoDePreparo(),
                pedidoModel.getTotal()
            );
        }else{
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
    }
    
    @Override
    public com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity movePedidoParaEmPreparacao(UUID pedidoUuid, Integer tempoDePreparoEmMinutos) throws PedidoNaoEncontradoException {
        Optional<Pedido> pedido = pedidosMongoRepository.findByUuidPedido(pedidoUuid);
        if(pedido.isPresent()){
            Pedido pedidoModel = pedido.get();
            pedidoModel.setTempoDePreparo(tempoDePreparoEmMinutos);
            pedidoModel.setStatusPedido(StatusPedido.EM_PREPARACAO);
            pedidosMongoRepository.save(pedidoModel);

            return new com.fiap.mspedidoapi.domain.entity.pedido.PedidoEntity(
                pedidoModel.getUuidPedido(),
                pedidoModel.getClienteUuid(),
                pedidoModel.getStatusPedido(),
                pedidoModel.getStatusPagamento(),
                pedidoModel.getTempoDePreparo(),
                pedidoModel.getTotal()
            );
        }else{
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
    }
}
