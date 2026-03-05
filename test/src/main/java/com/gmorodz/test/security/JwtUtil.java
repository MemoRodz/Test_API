package com.gmorodz.test.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String SECRET_KEY = "miClaveSecreta32bytes123";

    public String generateToken(String taxId, String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", name); // Podemos guardar datos extra en el token

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(taxId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
