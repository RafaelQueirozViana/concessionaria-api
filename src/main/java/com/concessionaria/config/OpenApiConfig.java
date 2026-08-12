package com.concessionaria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI concessionariaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Concessionária Marcelo Gomes API")
                        .description("API para controle de estoque de veículos e cadastro de clientes da concessionária.")
                        .version("1.0"));
    }
}