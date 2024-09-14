package com.fiap.mspedidoapi.infra.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer customOpenApi() {
        return openApi -> openApi.info(new Info()
                .title("MS Pedido API")
                .description("API de Pedidos")
                .version("1.0.0")
                .termsOfService("Termos de Serviço")
                .contact(new Contact()
                        .name("Nome do Contato")
                        .url("URL do Contato")
                        .email("Email do Contato"))
                .license(new License()
                        .name("Nome da Licença")
                        .url("URL da Licença")));
    }
}
