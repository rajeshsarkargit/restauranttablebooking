/*
package com.restaurants.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class Swagger2Config {
    @Value("${swagger.openapi.dev-url}")
    private String devUrl;

    @Value("${swagger.openapi.local-url}")
    private String localUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Server localServer = new Server();
        localServer.setUrl(localUrl);
        localServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("admin@gmail.com");
        contact.setName("admin");
        contact.setUrl("https://localhost.com");

        License mitLicense = new License().name("MIT License").url("https://localhost.com/licenses/mit/");

        Info info = new Info()
                .title("Table Booking Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.").termsOfService("https://localhost.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer, localServer));
    }
}
*/
