package com.jaroso.trazabilidad_productos.security;



import com.jaroso.trazabilidad_productos.entities.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class JwtService {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Integer jwtDurationSeconds;

    // GENERAR token al hacer login
    public String generateToken(Authentication authentication) {
        Usuario user = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), Jwts.SIG.HS256)
                .header()
                .add("typ", "JWT").and()
                .subject(Long.toString(user.getId()))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (jwtDurationSeconds * 1000)))
                .claim("username", user.getUsername())
                .claim("email", user.getEmail())
                .compact();

    }


    //Validar Token
    public boolean isValidToken(String token) {
        if (!StringUtils.hasLength(token))
            return false;

        try {
            JwtParser validator = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build();

            //El validador comprueba el token
            validator.parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            log.info("Error en la firma del token", e);
        } catch (MalformedJwtException | UnsupportedJwtException e) {
            log.info("Token incorrecto", e);
        } catch (ExpiredJwtException e) {
            log.info("Token expirado", e);
        }
        return false;

    }

    public String getUsernameFromToken(String token) {
        JwtParser validator = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes())).build();
        Jws<Claims> claims = validator.parseSignedClaims(token);
        return claims.getPayload().get("username").toString();
    }



}
