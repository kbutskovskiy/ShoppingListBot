package com.example.shopping_list.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("com.example.shopping_list")
@EnableJpaRepositories(basePackages = "com.example.shopping_list.repository")
@Data
@PropertySource("application.properties")
public class ConfigurationBot {
    @Value("${BOT_NAME}") String botName;
    @Value("${BOT_TOKEN}") String token;
}
