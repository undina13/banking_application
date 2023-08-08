package com.undina.deal.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Conveyor",
                description = "Conveyor application", version = "1.0.0",
                contact = @Contact(
                        name = "undina",
                        email = "foxundina@gmail.com"

                )
        )
)
public class SwaggerConfiguration {
}

