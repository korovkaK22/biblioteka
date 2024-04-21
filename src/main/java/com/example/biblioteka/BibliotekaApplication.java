package com.example.biblioteka;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
        info = @Info(
                title = "Online writings library",
                contact = @Contact(
                        name = "Sereda Andrii",
                        email = "email@gmail.com"
                ),
                description = "MyOpen",
                version = "2.2.0"
        ),
        servers = {
                @Server(url = "http://176.104.52.49:8050", description = "test server")
        }
)

@SpringBootApplication
public class BibliotekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotekaApplication.class, args);
    }

}
