package com.example.TechnoMark.service;

import com.example.TechnoMark.model.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String MY_SECRET_KEY = "f28a9b9422f531ae82e2abedb4968150a5be310f2abf5276efc9436dad63eaf2";

    private SecretKey getSigningKey() {
        byte[] mySecretKey = Decoders.BASE64.decode(MY_SECRET_KEY);
        return Keys.hmacShaKeyFor(mySecretKey);
    }

    public String generateToken(Customer customer) {
        return Jwts
                .builder()
                .subject(customer.getUsername())
                .signWith(getSigningKey())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .compact();
    }

    private Claims extraxctAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extraxctAllClaims(token);
        return resolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, String username) {
        String currentUsername = extractUsername(token);

        return currentUsername.equals(username) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
