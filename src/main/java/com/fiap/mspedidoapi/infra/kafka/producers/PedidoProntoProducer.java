package com.fiap.mspedidoapi.infra.kafka.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fiap.mspedidoapi.domain.gateway.producers.PedidoProntoProducerInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoProntoOutput;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.producers.KafkaProducerResolver;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.producers.KafkaSenderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PedidoProntoProducer extends KafkaSenderConfig implements PedidoProntoProducerInterface {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(PedidoProntoProducer.class);

    public PedidoProntoProducer(String servers) {
        super(servers, new KafkaProducerResolver().getEntregaProducer());
    }

    @Override
    public void send(PedidoProntoOutput pedidoProntoOutput) {
        try {
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("uuid_pedido", pedidoProntoOutput.getEntrega().getUuidPedido().toString());
            jsonNode.put("status_pedido", pedidoProntoOutput.getEntrega().getStatusPedido().toString());
            jsonNode.put("numero_pedido", pedidoProntoOutput.getEntrega().getNumeroPedido());
            String json = jsonNode.toString();
            send(UUID.randomUUID().toString(), json);
            logger.info("Mensagem enviada - UUID Pedido: {}, Status Pedido: {}, Número Pedido: {}",
                    pedidoProntoOutput.getEntrega().getUuidPedido(),
                    pedidoProntoOutput.getEntrega().getStatusPedido(),
                    pedidoProntoOutput.getEntrega().getNumeroPedido());
        } catch (Exception e) {
            logger.error("Erro ao enviar a mensagem: ", e);
        }
    }
}