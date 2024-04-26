package ru.point.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import ru.point.security.authentication.JwtAuthenticationToken;
import ru.point.security.exception.TokenExpiredException;

import java.util.Date;

public class UserTokenAuthenticationProvider implements AuthenticationProvider {
    Date now = new Date();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var tokenExpiredDate = ((JwtAuthenticationToken) authentication).getTokenExpirationDate();
        if (tokenExpiredDate.before(now)) {
            throw new TokenExpiredException("Token expired at " + new Date(now.getTime() - tokenExpiredDate.getTime()));
        }
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}
