package com.gmorodz.test.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Usuarios - Memordz")
                        .version("1.0")
                        .description("Documentación interactiva de la API de Usuarios con JWT y AES256.")
                        .contact(new Contact()
                                .name("Guillermo Rodríguez")
                                .email("gmo.rodriguez@gmail.com")));
    }
}
