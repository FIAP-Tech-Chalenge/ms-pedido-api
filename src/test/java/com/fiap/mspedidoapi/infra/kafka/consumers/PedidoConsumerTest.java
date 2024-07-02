//package com.fiap.mspedidoapi.infra.kafka.consumers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
//import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
//import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
//import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
//import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
//import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.TopicPartition;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mockito;
//import org.springframework.kafka.test.context.EmbeddedKafka;
//
//import java.time.Duration;
//import java.util.Collections;
//import java.util.List;
//import java.util.Map;
//import java.util.Properties;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@EmbeddedKafka
//public class PedidoConsumerTest {
//
//    private PedidoConsumer pedidoConsumer;
//    private KafkaConsumer<String, String> kafkaConsumer;
//    private PedidosMongoRepository pedidosMongoRepository;
//    private ObjectMapper objectMapper;
//
//    @BeforeEach
//    public void setUp() {
//        Properties kafkaConsumerProperties = new Properties();
//        kafkaConsumerProperties.put("bootstrap.servers", "localhost:9092");
//        kafkaConsumerProperties.put("group.id", "pedido");
//        kafkaConsumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        kafkaConsumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//
//        kafkaConsumer = mock(KafkaConsumer.class);
//        pedidosMongoRepository = mock(PedidosMongoRepository.class);
//        objectMapper = new ObjectMapper();
//
//        pedidoConsumer = new PedidoConsumer(kafkaConsumerProperties, pedidosMongoRepository);
//    }
//
//    @Test
//    public void deveProcessarMensagemComSucesso() throws Exception {
//        String uuidPedido = UUID.randomUUID().toString();
//        String uuidCliente = UUID.randomUUID().toString();
//        String message = String.format("{\"pedido_uuid\":\"%s\",\"cliente_uuid\":\"%s\",\"status_pagamento\":\"PAGO\",\"numero_pedido\":12345,\"total\":35.0,\"produtos\":\"[{\\\"uuid\\\":\\\"%s\\\",\\\"nome\\\":\\\"Produto 1\\\",\\\"valor\\\":10.0,\\\"categoria\\\":\\\"LANCHE\\\",\\\"quantidade\\\":2}]\"}",
//                uuidPedido, uuidCliente, UUID.randomUUID().toString());
//
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("pedido", 0, 0, null, message);
//        TopicPartition topicPartition = new TopicPartition("pedido", 0);
//        ConsumerRecords<String, String> records = new ConsumerRecords<>(Map.of(topicPartition, List.of(record)));
//
//        when(kafkaConsumer.poll(Duration.ofMillis(100))).thenReturn(records);
//
//        pedidoConsumer.runConsumer();
//
//        ArgumentCaptor<Pedido> pedidoCaptor = ArgumentCaptor.forClass(Pedido.class);
//        verify(pedidosMongoRepository, times(1)).save(pedidoCaptor.capture());
//        Pedido pedidoSalvo = pedidoCaptor.getValue();
//
//        assertThat(pedidoSalvo.getUuidPedido().toString()).isEqualTo(uuidPedido);
//        assertThat(pedidoSalvo.getClienteUuid().toString()).isEqualTo(uuidCliente);
//        assertThat(pedidoSalvo.getStatusPedido()).isEqualTo(StatusPedido.RECEBIDO);
//        assertThat(pedidoSalvo.getStatusPagamento()).isEqualTo(StatusPagamento.PAGO);
//        assertThat(pedidoSalvo.getNumeroPedido()).isEqualTo(12345);
//        assertThat(pedidoSalvo.getTotal()).isEqualTo(35.0f);
//
//        Produto produto = pedidoSalvo.getProdutos().get(0);
//        assertThat(produto.getNome()).isEqualTo("Produto 1");
//        assertThat(produto.getValor()).isEqualTo(10.0f);
//        assertThat(produto.getCategoria()).isEqualTo(CategoriaEnum.LANCHE);
//        assertThat(produto.getQuantidade()).isEqualTo(2);
//    }
//
//    @Test
//    public void deveIgnorarMensagemInvalida() throws Exception {
//        String invalidMessage = "invalid message";
//
//        ConsumerRecord<String, String> record = new ConsumerRecord<>("pedido", 0, 0, null, invalidMessage);
//        TopicPartition topicPartition = new TopicPartition("pedido", 0);
//        ConsumerRecords<String, String> records = new ConsumerRecords<>(Map.of(topicPartition, List.of(record)));
//
//        when(kafkaConsumer.poll(Duration.ofMillis(100))).thenReturn(records);
//
//        pedidoConsumer.runConsumer();
//
//        verify(pedidosMongoRepository, never()).save(any());
//    }
//}
