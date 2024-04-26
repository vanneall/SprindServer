package ru.point.security;

import com.nimbusds.jose.JWEDecrypter;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class JwtUtils {

    private JwtUtils() {
    }

    private static String SIGNATURE_KEY = "{\"kty\":\"oct\",\"k\":\"hi7S5RX5ZRZooHA0RKGctZ-KtR9FoESgCnH-3BNg5XI\"}";

    private static final Duration DURATION = Duration.ofDays(1);

    public static String generateToken(UserDetails userDetails) {
        List<String> authorities = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        Date createdDate = new Date();
        Date expiredDate = new Date(createdDate.getTime() + DURATION.toMillis());

        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(userDetails.getUsername())
                .claim(AUTHORITY_KEY, authorities)
                .issueTime(createdDate)
                .expirationTime(expiredDate)
                .build();
        JWSSigner signer;

        try {
            signer = new MACSigner(OctetSequenceKey.parse(SIGNATURE_KEY));
        } catch (Exception ex) {
            System.out.println("JWSSigner ex " + Arrays.toString(ex.getStackTrace()));
            return "";
        }

        SignedJWT signedJWT = new SignedJWT(jwsHeader, jwtClaimsSet);
        try {
            signedJWT.sign(signer);
        } catch (Exception ex) {
            System.out.println("SignedJWT ex " + Arrays.toString(ex.getStackTrace()));
        }


        return signedJWT.serialize();
    }

    public static Set<String> getAuthorities(Collection<?> collection) {
        return collection.stream().map(mapper -> (String) mapper).collect(Collectors.toSet());
    }

    private static String AUTHORITY_KEY = "authority";
}
