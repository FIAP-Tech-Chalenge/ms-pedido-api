package com.fiap.mspedidoapi.infra.kafka.consumers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPagamento;
import com.fiap.mspedidoapi.domain.enums.pedido.StatusPedido;
import com.fiap.mspedidoapi.infra.collection.pedido.items.Produto;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.consumers.KafkaConsumerResolver;
import com.fiap.mspedidoapi.infra.kafka.producers.PreparaPedidoProducer;
import com.fiap.mspedidoapi.infra.repository.PedidosMongoRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;


@Component
public class PedidoConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final ObjectMapper objectMapper;
    private final PedidosMongoRepository pedidoRepository;
    private final Logger logger = LoggerFactory.getLogger(PreparaPedidoProducer.class);

    public PedidoConsumer(
            Properties kafkaConsumerProperties,
            PedidosMongoRepository pedidoRepository
    ) {
        this.consumer = new KafkaConsumer<>(kafkaConsumerProperties);
        this.consumer.subscribe(Collections.singletonList(new KafkaConsumerResolver().getPedidoConsumer()));
        this.objectMapper = new ObjectMapper();
        this.pedidoRepository = pedidoRepository;
    }

    public void runConsumer() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Mensagem recebida - Tópico: %s, Chave: %s, Valor: %s%n", record.topic(), record.key(), record.value());
                    try {
                        JsonNode messageJson = objectMapper.readTree(record.value());
                        String uuidPedido = messageJson.get("pedido_uuid").asText();
                        String uuidCliente = messageJson.get("cliente_uuid").asText();
                        String statusPagamento = messageJson.get("status_pagamento").asText();
                        int numeroPedido = messageJson.get("numero_pedido").asInt();
                        double total = messageJson.get("total").asDouble();

                        com.fiap.mspedidoapi.infra.collection.pedido.Pedido pedidoModel = new com.fiap.mspedidoapi.infra.collection.pedido.Pedido();
                        pedidoModel.setUuidPedido(UUID.fromString(uuidPedido));
                        pedidoModel.setClienteUuid(UUID.fromString(uuidCliente));
                        pedidoModel.setStatusPedido(StatusPedido.RECEBIDO);
                        pedidoModel.setStatusPagamento(StatusPagamento.valueOf(statusPagamento));
                        pedidoModel.setTotal((float) total);
                        pedidoModel.setNumeroPedido(numeroPedido);
                        List<Produto> produtosList = new ArrayList<>();
                        Map<String, Object> deserializedMap = objectMapper.readValue(messageJson.toString(), new TypeReference<Map<String, Object>>() {
                        });
                        String produtosJsonString = (String) deserializedMap.get("produtos");
                        List<Object> deserializedProdutosList = objectMapper.readValue(
                                produtosJsonString,
                                new TypeReference<List<Object>>() {}
                        );

                        for (Object produto : deserializedProdutosList) {
                            java.util.Map<?, ?> produtoMap = (java.util.Map<?, ?>) produto;
                            produtosList.add(
                                    new Produto(
                                            UUID.fromString((String) produtoMap.get("uuid")),
                                            (String) produtoMap.get("nome"),
                                            Float.parseFloat(produtoMap.get("valor").toString()),
                                            com.fiap.mspedidoapi.domain.enums.produto.CategoriaEnum.valueOf((String) produtoMap.get("categoria")),
                                            (Integer) produtoMap.get("quantidade")
                                    )
                            );
                        }
                        System.out.println(produtosList);
                        pedidoModel.setProdutos(produtosList);
                        pedidoRepository.save(pedidoModel);

                    } catch (Exception e) {
                        logger.error("Erro ao processar a mensagem: " + e.getMessage());
                    }
                }
            }
        } finally {
            this.consumer.close();
            logger.info("Consumidor Kafka fechado.");
        }
    }
}
