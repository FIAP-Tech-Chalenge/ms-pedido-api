package com.fiap.mspedidoapi.infra.kafka.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fiap.mspedidoapi.domain.gateway.producers.PreparaPedidoProducerInterface;
import com.fiap.mspedidoapi.domain.output.pedido.PedidoEmPreparacaoOutput;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.producers.KafkaProducerResolver;
import com.fiap.mspedidoapi.infra.dependecy.kafka.resolvers.producers.KafkaSenderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class PreparaPedidoProducer extends KafkaSenderConfig implements PreparaPedidoProducerInterface {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = LoggerFactory.getLogger(PreparaPedidoProducer.class);

    public PreparaPedidoProducer(String servers) {
        super(servers, new KafkaProducerResolver().getTempoDeEsperaProducer());
    }

    @Override
    public void send(PedidoEmPreparacaoOutput pedidoEmPreparacaoOutput) {
        try {
            ObjectNode jsonNode = objectMapper.createObjectNode();
            jsonNode.put("uuid_pedido", pedidoEmPreparacaoOutput.getPedido().getPedidoId().toString());
            jsonNode.put("status_pedido", pedidoEmPreparacaoOutput.getPedido().getStatusPedido().toString());
            jsonNode.put("numero_pedido", pedidoEmPreparacaoOutput.getPedido().getNumeroPedido());
            jsonNode.put("tempo_preparo", pedidoEmPreparacaoOutput.getPedido().getTempoDePreparoEmMinutos());
            String json = jsonNode.toString();
            send(UUID.randomUUID().toString(), json);
            logger.info("Mensagem enviada - UUID Pedido: {}, Status Pedido: {}, Número Pedido: {}, Tempo Preparo: {}",
                    pedidoEmPreparacaoOutput.getPedido().getPedidoId(),
                    pedidoEmPreparacaoOutput.getPedido().getStatusPedido(),
                    pedidoEmPreparacaoOutput.getPedido().getNumeroPedido(),
                    pedidoEmPreparacaoOutput.getPedido().getTempoDePreparoEmMinutos());
        } catch (Exception e) {
            logger.error("Erro ao enviar a mensagem: ", e);
        }
    }
}