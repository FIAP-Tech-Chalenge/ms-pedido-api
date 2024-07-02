package com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers;

import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.consumers.KafkaConsumerResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KafkaConsumerResolverTest {

    private KafkaConsumerResolver kafkaConsumerResolver;

    @BeforeEach
    public void setUp() {
        kafkaConsumerResolver = new KafkaConsumerResolver();
    }

    @Test
    public void deveRetornarPedidoConsumerCorretamente() {
        String topic = kafkaConsumerResolver.getPedidoConsumer();
        assertThat(topic).isEqualTo("pedido");
    }
}
