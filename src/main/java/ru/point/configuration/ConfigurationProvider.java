package ru.point.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ConfigurationProvider {

    @Bean
    BCryptPasswordEncoder provideBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
