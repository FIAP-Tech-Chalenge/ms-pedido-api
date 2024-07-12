package com.fiap.mspedidoapi.bdd.steps;

import com.fiap.mspedidoapi.infra.collection.pedido.Pedido;
import com.fiap.mspedidoapi.infra.kafka.consumers.PedidoConsumer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoConsumerSteps {

    @Mock
    private KafkaConsumer<String, String> kafkaConsumer;

    @Mock
    private PedidosMongoRepository pedidoRepository;

    private PedidoConsumer pedidoConsumer;

    private CountDownLatch latch;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar propriedades do consumidor Kafka
        Properties kafkaConsumerProperties = new Properties();
        kafkaConsumerProperties.setProperty("bootstrap.servers", "localhost:9092");
        kafkaConsumerProperties.setProperty("group.id", "test-group");
        kafkaConsumerProperties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaConsumerProperties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // Criar instância de PedidoConsumer com propriedades simuladas
        pedidoConsumer = new PedidoConsumer(kafkaConsumerProperties, pedidoRepository);

        // Configurar o campo KafkaConsumer com o mock
        ReflectionTestUtils.setField(pedidoConsumer, "consumer", kafkaConsumer);

        latch = new CountDownLatch(1);
    }

    @Given("que uma mensagem de pedido está disponível no tópico {string}")
    public void que_uma_mensagem_de_pedido_está_disponível_no_tópico(String topic) throws Exception {
        String jsonMessage = "{\"pedido_uuid\":\"123e4567-e89b-12d3-a456-426614174000\",\"cliente_uuid\":\"123e4567-e89b-12d3-a456-426614174001\",\"status_pagamento\":\"PAGO\",\"numero_pedido\":123,\"total\":100.0,\"produtos\":\"[]\"}";
        ConsumerRecord<String, String> record = new ConsumerRecord<>(topic, 0, 0L, "key", jsonMessage);
        ConsumerRecords<String, String> records = new ConsumerRecords<>(Collections.singletonMap(new TopicPartition(topic, 0), List.of(record)));

        when(kafkaConsumer.poll(any(Duration.class))).thenAnswer(invocation -> {
            latch.countDown();
            return records;
        });
    }

    @When("o consumidor processa a mensagem")
    public void o_consumidor_processa_a_mensagem() throws Exception {
        Thread consumerThread = new Thread(() -> {
            pedidoConsumer.runConsumer();
        });
        consumerThread.start();

        latch.await(5, TimeUnit.SECONDS);

        consumerThread.interrupt();
        consumerThread.join();
    }

    @Then("o pedido deve ser salvo no banco de dados")
    public void o_pedido_deve_ser_salvo_no_banco_de_dados() {
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }
}
