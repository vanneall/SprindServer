package ru.point.security.config;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.point.security.filter.TokenCheckFilter;

public class FilterChainConfigurer extends AbstractHttpConfigurer<FilterChainConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity builder) throws Exception {
        builder.exceptionHandling(exceptionHandler -> {
            exceptionHandler.authenticationEntryPoint((request, response, authException) -> {
                response.sendError(HttpStatus.FORBIDDEN.value(), "Token expired");
            });
        });
    }

    @Override
    public void configure(HttpSecurity builder) {
        builder
                .addFilterBefore(
                        new TokenCheckFilter(builder.getSharedObject(AuthenticationManager.class)),
                        UsernamePasswordAuthenticationFilter.class
                );
    }
}
