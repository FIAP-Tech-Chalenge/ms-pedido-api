package com.fiap.mspedidoapi.infra.adpter.repository.pedido;

import com.fiap.mspedidoapi.domain.entity.entrega.Entrega;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.domain.gateway.pedido.PedidoProntoInterface;
import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PedidoProntoRepository implements PedidoProntoInterface {
    public final PedidosMongoRepository pedidosMongoRepository;

    @Override
    public Entrega atualizaStatusPedido(UUID uuidPedido) {
        Optional<Pedido> optionalPedido = pedidosMongoRepository.findByUuidPedido(uuidPedido);
        if (optionalPedido.isPresent()) {
            Pedido existingPedido = optionalPedido.get();
            existingPedido.setStatusPedido(StatusPedido.PRONTO);
            pedidosMongoRepository.save(existingPedido);
            return new Entrega(existingPedido.getUuidPedido(), existingPedido.getNumeroPedido(), existingPedido.getStatusPedido());
        }

        return null;
    }
}
