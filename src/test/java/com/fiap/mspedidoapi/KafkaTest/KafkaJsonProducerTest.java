package com.fiap.mspedidoapi.KafkaTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.consumers.KafkaConsumerResolver;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaJsonProducerTest {
    @Test
    void testKafka() {
        String topicName = new KafkaConsumerResolver().getPedidoConsumer();
        String bootstrapServers = "localhost:9092"; // substitua pelo endereço do seu broker Kafka

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            for (int i = 0; i < 10; i++) {
                String key = "key-" + i;

                // Crie um mapa para simular o JSON
                List<Produto> produtosListProdutos = new ArrayList<>();
                produtosListProdutos.add(
                        new Produto(
                                UUID.randomUUID(),
                                "Nome" + i,
                                (float) (123.0 + i),
                                CategoriaEnum.LANCHE,
                                1 + i
                        )
                );
                Map<String, Object> valueMap = new HashMap<>();
                valueMap.put("pedido_uuid", UUID.randomUUID().toString());
                valueMap.put("cliente_uuid", UUID.randomUUID().toString());
                valueMap.put("status_pagamento", StatusPagamento.PAGO.toString());
                valueMap.put("numero_pedido", i);
                valueMap.put("total", 23423);
                valueMap.put("produtos", objectMapper.writeValueAsString(produtosListProdutos));

                // Converta o mapa para uma string JSON
                String value = objectMapper.writeValueAsString(valueMap);

                ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

                RecordMetadata metadata = producer.send(record).get();
                System.out.printf("Sent record with key %s and value %s to partition %d with offset %d%n",
                        key, value, metadata.partition(), metadata.offset());
            }
        } catch (ExecutionException | InterruptedException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }

    @Test
    void testKafka2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Produto> produtosListProdutos = new ArrayList<>();
        produtosListProdutos.add(
                new Produto(
                        UUID.randomUUID(),
                        "Nome" + 1,
                        (float) (123.0 + 2),
                        CategoriaEnum.LANCHE,
                        1 + 1
                )
        );
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("produtos", objectMapper.writeValueAsString(produtosListProdutos));

        String valueJsonString = objectMapper.writeValueAsString(valueMap);


        Map<String, Object> deserializedMap = objectMapper.readValue(valueJsonString, new TypeReference<Map<String, Object>>() {
        });
        String produtosJsonString = (String) deserializedMap.get("produtos");
        try {
            List<Object> deserializedProdutosList = objectMapper.readValue(produtosJsonString, new TypeReference<List<Object>>() {
            });

            for (Object produto : deserializedProdutosList) {
                java.util.Map<?, ?> produtoMap = (java.util.Map<?, ?>) produto;
                System.out.println("ID: " + produtoMap.get("uuid"));
                System.out.println("Nome: " + produtoMap.get("nome"));
                System.out.println("Preço: " + produtoMap.get("valor"));
                System.out.println("Categoria: " + produtoMap.get("categoria"));
                System.out.println("Quantidade: " + produtoMap.get("quantidade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
