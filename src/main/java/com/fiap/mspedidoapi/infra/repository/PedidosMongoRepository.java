package com.fiap.mspedidoapi.infra.repository;

import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PedidosMongoRepository extends MongoRepository<Pedido, String> {
    Optional<Pedido> findByUuidPedido(UUID uuidPedido);
}
