package com.nequi.franquicias.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        title = "API - Franquicias",
        description = "API para manejar una lista de franquicias, Sucursales y Productos",
        version = "1.0.0",
        contact = @Contact(
                name = "Fernando Rojas",
                email = "juanrojas.jfrc@gmail.com"
        )
),
servers = {
        @Server(
                description = "DEV SERVER",
                url = "http://localhost:8080"
        ),
        @Server(
                description = "PROD SERVER",
                url = "http://domain:8080"
        )
})
public class SwaggerConfig { }
