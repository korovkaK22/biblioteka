package com.example.biblioteka;

import com.example.biblioteka.security.PasswordHasher;
import com.example.biblioteka.security.PasswordHasherImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext run = SpringApplication.run(BibliotekaApplication.class, args);
        String password = "123456";

        PasswordHasher ph = run.getBean(PasswordHasher.class);
        String hashedPassword = ph.getHashedPassword(password);

        // System.out.println(ph.checkPasswords(password, hashedPassword));
    }

}
