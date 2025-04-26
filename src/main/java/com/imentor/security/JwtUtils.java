package com.imentor.security;

import io.jsonwebtoken.*;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    private static final String SECRET_KEY = "your_secret_key_here"; // Store securely (in environment variables in prod)
    private long expirationTime = 86400000; // 24 hours

    public String generateToken(String username) {

        /// Convert secret key to a Key object using SecretKeySpec
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), Jwts.SIG.HS256.toString());

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
