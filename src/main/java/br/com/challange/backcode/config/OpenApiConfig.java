package br.com.challange.backcode.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("API de Categorias e Produtos")
                        .version("1.0")
                        .description("Documentação da API para gerenciamento de categorias e produtos.")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
