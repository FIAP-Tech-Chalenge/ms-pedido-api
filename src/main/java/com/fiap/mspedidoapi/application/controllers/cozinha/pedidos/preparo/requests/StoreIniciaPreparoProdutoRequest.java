package com.fiap.mspedidoapi.application.controllers.cozinha.pedidos.preparo.requests;

import java.util.UUID;

public record StoreIniciaPreparoProdutoRequest(
        UUID uuid,
        Integer tempoDePreparo
){}
