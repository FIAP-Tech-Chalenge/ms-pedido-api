package com.fiap.mspedidoapi.infra.dependecy.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

public class JWTDecodeAdapterTest {

    private JWTDecodeAdapter jwtDecodeAdapter;

    @BeforeEach
    public void setUp() {
        jwtDecodeAdapter = new JWTDecodeAdapter();
    }

    @Test
    public void deveRetornarClaimClienteUuidQuandoTokenEhValido() throws Exception {
        String payload = "{\"username\":\"12345678-1234-1234-1234-123456789012\"}";
        String encodedPayload = Base64.getUrlEncoder().encodeToString(payload.getBytes());
        String token = "header." + encodedPayload + ".signature";

        String result = jwtDecodeAdapter.claimClienteUuid(token);

        assertThat(result).isEqualTo("12345678-1234-1234-1234-123456789012");
    }

//    @Test
//    public void deveRetornarMensagemDeErroQuandoTokenEhInvalido() throws Exception {
//        String token = "invalid.token.format";
//
//        String result = jwtDecodeAdapter.claimClienteUuid(token);
//
//        assertThat(result).isEqualTo("Invalid token format");
//    }

    @Test
    public void deveRetornarNullQuandoTokenEhInvalidoEJsonInvalido() throws Exception {
        String payload = "invalid-json";
        String encodedPayload = Base64.getUrlEncoder().encodeToString(payload.getBytes());
        String token = "header." + encodedPayload + ".signature";

        String result = jwtDecodeAdapter.claimClienteUuid(token);

        assertThat(result).isNull();
    }
}
