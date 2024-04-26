package ru.point.security.token;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    public static Set<String> getAuthorities(Collection<?> collection) {
        return collection.stream().map(mapper -> (String) mapper).collect(Collectors.toSet());
    }
}
