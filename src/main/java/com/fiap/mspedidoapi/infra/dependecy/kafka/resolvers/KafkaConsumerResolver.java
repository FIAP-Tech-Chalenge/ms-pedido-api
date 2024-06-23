package com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers;

import com.fiap.mspedidoapi.infra.dependecy.kafka.KafkaTopicsEnum;

public class KafkaConsumerResolver {

    public String getPedidoConsumer() {
        return KafkaTopicsEnum.pedido.name();
    }
}
