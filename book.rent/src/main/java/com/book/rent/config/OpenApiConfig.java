package com.book.rent.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Toma Daniel",
                        email = "cucutomadaniel@gmail.com"
                ),
                description = "OpenApi doc for Spring app",
                title = "OpenApi specification",
                version = "1.0",
                license = @License(
                        name = "Licence name"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8088/api/v1"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "http://localhost:8088/api/v1"
                )
        }, security = {
        @SecurityRequirement(
                name = "bearerAuth"
        )
}
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth desc",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
