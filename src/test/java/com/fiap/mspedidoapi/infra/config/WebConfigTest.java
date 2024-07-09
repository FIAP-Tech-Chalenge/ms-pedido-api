package com.fiap.mspedidoapi.infra.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import static org.mockito.Mockito.*;

public class WebConfigTest {

    private WebConfig webConfig;
    private CorsRegistry corsRegistry;
    private CorsRegistration corsRegistration;

    @BeforeEach
    public void setUp() {
        webConfig = new WebConfig();
        corsRegistry = mock(CorsRegistry.class);
        corsRegistration = mock(CorsRegistration.class);

        when(corsRegistry.addMapping(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedOrigins(anyString(), anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedMethods(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowedHeaders(anyString())).thenReturn(corsRegistration);
        when(corsRegistration.allowCredentials(anyBoolean())).thenReturn(corsRegistration);
    }

    @Test
    public void deveAdicionarCorsMappingsCorretamente() {
        webConfig.addCorsMappings(corsRegistry);

        verify(corsRegistry).addMapping("/**");
        verify(corsRegistration).allowedOrigins("http://localhost:8000", "http://localhost:8082");
        verify(corsRegistration).allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
        verify(corsRegistration).allowedHeaders("*");
        verify(corsRegistration).allowCredentials(true);
    }
}
