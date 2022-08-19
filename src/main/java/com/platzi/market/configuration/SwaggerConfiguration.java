package com.platzi.market.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI springShopOpenAPI() {
        Info swaggerInfo = new Info()
                .title("Platzi Market API")
                .description("This is a spring sample application, developed with Plazi <3")
                .version("v0.0.1")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"));

        ExternalDocumentation swaggerExternalDocs = new ExternalDocumentation()
                .description("Platzi Market Wiki Documentation")
                .url("https://www.google.com/");

        return new OpenAPI()
                .info(swaggerInfo)
                .externalDocs(swaggerExternalDocs);
    }

    @Bean
    public GroupedOpenApi purchasesApi() {
        return GroupedOpenApi.builder()
                .group("Purchases")
                .pathsToMatch("/purchases/**")
                .build();
    }

    @Bean
    public GroupedOpenApi productsApi() {
        return GroupedOpenApi.builder()
                .group("Products")
                .pathsToMatch("/products/**")
                .build();
    }
}
