package com.example.monolithtomicroservices.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customizeOpenAPI() {
        final String securitySchemeName = "Bearer Authentication";
        return new OpenAPI()
                .info(new Info()
                        .title("Webshop API")
                        .description("This API describes all the endpoints used in the webshop application.")
                )
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
    }
}
