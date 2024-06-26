package com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.consumers;

import com.fiap.mspedidoapi.infra.dependecy.kafka.KafkaTopicsEnum;

public class KafkaConsumerResolver {

    public String getPedidoConsumer() {
        return KafkaTopicsEnum.pedido.name();
    }
}
