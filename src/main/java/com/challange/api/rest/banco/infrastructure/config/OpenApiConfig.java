package com.challange.api.rest.banco.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API REST API de entidades bancarias.")
                        .description("Microservicio con arquitectura REST que expone un CRUD sobre entidades bancarias usando OpenAPI 3.")
                        .termsOfService("terms")
                        .contact(new Contact().email("@example.dev"))
                        .license(new License().name("GNU"))
                        .version("1.0")
                );
    }

}
