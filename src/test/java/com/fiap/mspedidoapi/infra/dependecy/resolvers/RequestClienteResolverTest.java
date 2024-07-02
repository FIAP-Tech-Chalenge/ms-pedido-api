package com.fiap.mspedidoapi.infra.dependecy.resolvers;

import com.fiap.mspedidoapi.infra.dependecy.jwt.JWTDecodeAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestClienteResolverTest {

    private JWTDecodeAdapter jwtDecodeAdapter;

    @BeforeEach
    public void setUp() {
        jwtDecodeAdapter = mock(JWTDecodeAdapter.class);
    }

//    @Test
//    public void deveRetornarClienteUuidDoAuthorizationHeader() throws Exception {
//        String token = "valid.token.jwt";
//        String expectedUuid = "12345678-1234-1234-1234-123456789012";
//
//        when(jwtDecodeAdapter.claimClienteUuid(token)).thenReturn(expectedUuid);
//
//        String result = RequestClienteResolver.resolve(token, null);
//
//        assertThat(result).isEqualTo(expectedUuid);
//    }

    @Test
    public void deveRetornarClienteUuidQuandoAuthorizationHeaderENull() throws Exception {
        UUID clienteUuid = UUID.randomUUID();

        String result = RequestClienteResolver.resolve(null, clienteUuid);

        assertThat(result).isEqualTo(clienteUuid.toString());
    }

    @Test
    public void deveRetornarNullQuandoAuthorizationHeaderEClienteUuidSaoNull() throws Exception {
        String result = RequestClienteResolver.resolve(null, null);

        assertThat(result).isNull();
    }
}
