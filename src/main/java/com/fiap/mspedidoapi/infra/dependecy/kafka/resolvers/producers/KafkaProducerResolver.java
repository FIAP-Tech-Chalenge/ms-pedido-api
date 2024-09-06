package com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.producers;


import com.fiap.mspedidoapi.infra.dependecy.kafka.KafkaTopicsEnum;

public class KafkaProducerResolver {
    public String getEntregaProducer() {
        return KafkaTopicsEnum.entrega.name();
    }
    public String getPedidoProducer() {
        return KafkaTopicsEnum.pedido.name();
    }
}
