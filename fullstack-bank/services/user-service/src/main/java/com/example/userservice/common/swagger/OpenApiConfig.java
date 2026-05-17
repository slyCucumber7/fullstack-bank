package com.example.userservice.common.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
    info = @Info(
            title = "user service",
            description = "service responsible for managing user data",
            version = " v1.0"
    ),
    servers = @Server(
            description = "Local dev environment",
            url = "http://localhost:8080"
            )
)
public class OpenApiConfig {


}
