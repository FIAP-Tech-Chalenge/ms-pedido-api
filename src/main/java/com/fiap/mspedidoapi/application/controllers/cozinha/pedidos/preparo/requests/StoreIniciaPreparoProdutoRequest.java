package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Request para iniciar o preparo do produto")
public record StoreIniciaPreparoProdutoRequest(
        @Schema(description = "UUID do pedido", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID uuid,

        @Schema(description = "Tempo de preparo em minutos", example = "30")
        Integer tempoDePreparo
) {}