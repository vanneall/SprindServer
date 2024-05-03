package ru.point.security.config;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import ru.point.security.provider.UserTokenAuthenticationProvider;

import java.text.ParseException;

@Configuration
@EnableWebSecurity
@Component
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorizeHttpRequests ->
                authorizeHttpRequests
                    .requestMatchers("sprind/auth", "sprind/auth/reg").permitAll()
                    .requestMatchers("sprind/cart/**").authenticated()
                    .requestMatchers("sprind/favorites/**").authenticated()
                    .anyRequest().permitAll()
            )
            .with(new FilterChainConfigurer(), Customizer.withDefaults())
            .authenticationProvider(new UserTokenAuthenticationProvider())
            .build();
    }

    @Bean
    public OctetSequenceKey createBeanKey(@Value("${values.signature}") String signature) throws ParseException {
        return OctetSequenceKey.parse(signature);
    }

    @Bean
    public JWSSigner createBeanJWSSigner(OctetSequenceKey octetSequenceKey) throws KeyLengthException {
        return new MACSigner(octetSequenceKey);
    }

    @Bean
    public JWSAlgorithm createBeanJWSAlgorithm() {
        return JWSAlgorithm.HS256;
    }
}
