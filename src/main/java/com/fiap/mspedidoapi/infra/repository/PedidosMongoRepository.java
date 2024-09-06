package com.fiap.mspedidoapi.infra.repository;

import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PedidosMongoRepository extends MongoRepository<Pedido, String> {
    
    @Query("{'uuid_pedido': ?0}")
    Optional<Pedido> findByUuidPedido(UUID uuidPedido);
}
