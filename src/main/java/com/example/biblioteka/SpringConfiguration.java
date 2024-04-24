package com.example.biblioteka;

import com.example.biblioteka.security.PasswordHasher;
import com.example.biblioteka.security.PasswordHasherImpl;
import com.example.biblioteka.validators.CommentValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfiguration {

    @Bean
    @Scope("prototype")
    public PasswordHasher getPasswordHasher(){
        return new PasswordHasherImpl();
    }

    @Bean
    @Scope("prototype")
    public CommentValidator getCommentValidator(){
        return new CommentValidator();
    }

}
