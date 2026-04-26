package com.example.uberprojectauthservice.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements CommandLineRunner {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    private String createToken(Map<String, Object> payload, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiry * 1000L);

        return Jwts.builder()
                .claims(payload)
                .issuedAt(now)
                .expiration(expiryDate)
                .subject(email)
                .signWith(getSigningKey())
                .compact();
    }

    private <T> T extractPayload(String token, Function<Claims, T> resolverFunction) {
        final Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return resolverFunction.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractPayload(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private String extractEmail(String token) {
        return extractPayload(token, Claims::getSubject);
    }

    private Boolean validateToken(String token, String email) {
        final String userEmailFetchedFromToken = extractEmail(token);
        return userEmailFetchedFromToken.equals(email) && !isTokenExpired(token);
    }

    private String extractPhoneNumber(String token) {
        return extractPayload(token, claims -> (String) claims.get("phoneNumber"));
    }

    private Object extractPayload(String token, String payloadKey) {
        return extractPayload(token, claims -> claims.get(payloadKey));
    }

    @Override
    public void run(String... args) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("email", "a@b.com");
        mp.put("phoneNumber", "9999999999");

        String token = createToken(mp, "a@b.com");

        System.out.println("Generated token is: " + token);
        System.out.println("Is token expired? " + isTokenExpired(token));
        System.out.println("Extracted email: " + extractEmail(token));
        System.out.println("Extracted phone: " + extractPhoneNumber(token));
        System.out.println("Is token valid? " + validateToken(token, "a@b.com"));
        System.out.println(extractPayload(token,"email").toString() );
    }
}