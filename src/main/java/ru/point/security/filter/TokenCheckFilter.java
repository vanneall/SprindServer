package ru.point.security.filter;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.point.security.authorization.JwtAuthenticationToken;
import ru.point.security.token.JwtUtils;

import java.io.IOException;

@AllArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

    AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            try {
                SignedJWT signedJWT = SignedJWT.parse(header);

                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
                JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(
                        claimsSet.getSubject(),
                        JwtUtils.getAuthorities(claimsSet.getListClaim("authority")).stream().map(SimpleGrantedAuthority::new).toList(),
                        claimsSet.getExpirationTime()
                );

                var auth = authenticationManager.authenticate(jwtAuthenticationToken);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (Exception ex) {
                SecurityContextHolder.clearContext();
                ex.printStackTrace();
            }
        }

        filterChain.doFilter(request, response);
    }
}
