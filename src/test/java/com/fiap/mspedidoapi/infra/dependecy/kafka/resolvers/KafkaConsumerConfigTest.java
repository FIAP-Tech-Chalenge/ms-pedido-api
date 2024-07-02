package com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers;

import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.consumers.KafkaConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
public class KafkaConsumerConfigTest {

    @Autowired
    private KafkaConsumerConfig kafkaConsumerConfig;

    @Test
    public void deveConfigurarPropriedadesDoKafkaConsumerCorretamente() {
        Properties props = kafkaConsumerConfig.kafkaConsumerProperties();

        assertThat(props.getProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG)).isEqualTo("localhost:9092");
        assertThat(props.getProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG)).isEqualTo(StringDeserializer.class.getName());
        assertThat(props.getProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG)).isEqualTo(StringDeserializer.class.getName());
        assertThat(props.getProperty(ConsumerConfig.GROUP_ID_CONFIG)).isEqualTo("pedido");
        assertThat(props.getProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG)).isEqualTo("earliest");
    }

    @Test
    public void deveConfigurarProdutoDataQueueCorretamente() {
        BlockingQueue<Map.Entry<Long, Double>> queue = kafkaConsumerConfig.produtoDataQueue();
        assertThat(queue).isInstanceOf(LinkedBlockingQueue.class);
    }

    @Test
    public void deveRetornarPropriedadesComServidorBootstrapCorreto() {
        KafkaConsumerConfig config = new KafkaConsumerConfig();
        Properties propriedades = config.kafkaConsumerProperties();
        assertEquals("localhost:9092", propriedades.getProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        assertNotNull(propriedades.toString());
    }

    @Test
    public void deveRetornarPropriedadesQuandoNenhumaPropriedadeEstiverDefinida() {
        KafkaConsumerConfig config = new KafkaConsumerConfig();
        Properties propriedades = config.kafkaConsumerProperties();
        assertNotNull(propriedades);
        assertNotNull(propriedades.toString());
    }

    @Test
    public void deveTratarChavesDePropriedadesInvalidasGraciosamente() {
        KafkaConsumerConfig config = new KafkaConsumerConfig();
        Properties propriedades = config.kafkaConsumerProperties();
        propriedades.put("chave.invalida", "valorInvalido");
        assertEquals("valorInvalido", propriedades.getProperty("chave.invalida"));
        assertNotNull(propriedades.toString());
    }
}
