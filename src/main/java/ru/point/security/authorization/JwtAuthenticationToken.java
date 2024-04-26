package ru.point.security.authorization;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    Date tokenExpirationDate;

    public JwtAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities, Date tokenExpirationDate) {
        super(principal, null, authorities);
        this.tokenExpirationDate = tokenExpirationDate;
    }
}
