package com.fiap.mspedidoapi.infra.repository;

import com.fiap.mspedidoapi.infra.collection.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidosMongoRepository extends MongoRepository<Pedido, String> {
}
