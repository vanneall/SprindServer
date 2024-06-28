package ru.point.security.token;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
@Component
public class AuthorizationTokenFactory implements Function<UserDetails, String> {

    private final JWSSigner jwsSigner;

    private final JWSAlgorithm jwsAlgorithm;

    private final Duration TOKEN_LIFETIME = Duration.ofDays(7);

    @Override
    public String apply(UserDetails userDetails)  {
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Date dateOfTokenCreation = new Date();
        Date dateOfTokenExpires = new Date(dateOfTokenCreation.getTime() + TOKEN_LIFETIME.toMillis());

        JWSHeader jwsHeader = new JWSHeader(jwsAlgorithm);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userDetails.getUsername())
                .claim(AUTHORITY_KEY, authorities)
                .issueTime(dateOfTokenCreation)
                .expirationTime(dateOfTokenExpires)
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            signedJWT.sign(jwsSigner);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }

        return signedJWT.serialize();
    }

    private static final String AUTHORITY_KEY = "authority";

}
