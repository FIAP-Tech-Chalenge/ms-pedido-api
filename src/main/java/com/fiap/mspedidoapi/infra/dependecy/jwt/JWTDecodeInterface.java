package com.fiap.mspedidoapi.infra.dependecy.jwt;

public interface JWTDecodeInterface {
    String claimClienteUuid(String token) throws Exception;
}
