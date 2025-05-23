package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().info(new Info().title("User management").version("1.0.0").description("Документация API для управления юзерами")
                .contact(new Contact().name("Name").email("test@mail.ru")
                        .url("https://example.com")).license(new License().name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENCE-2.0.html")));
    }
}
