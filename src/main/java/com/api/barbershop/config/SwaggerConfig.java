package com.api.barbershop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Barber Shop")              // Nome da tua API
                        .description("Documentação da API REST com Spring Boot 3.4 e Swagger UI")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Deyvison Dev")
                                .email("deyvison@example.com")
                                .url("https://github.com/deyvison"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
