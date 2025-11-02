package com.workintech.FSWEB_s19_Challenge.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.workintech.FSWEB_s19_Challenge.user.model.User;
import com.workintech.FSWEB_s19_Challenge.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;

    @Value("${jwt.secret:MySuperSecretKeyForJwtTokenThatShouldBeLongEnoughAndStrongEnoughForHS512}")
    private String jwtSecret;

    @Value("${jwt.expirationMs:86400000}") // 1 gün
    private long jwtExpirationMs;

    private Key getSigningKey() {
        // Base64 encode/decode ile sabit bir key üret
        byte[] keyBytes = Base64.getEncoder().encode(jwtSecret.getBytes());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println("JWT validation failed: " + e.getMessage());
            return false;
        }
    }

    public User getCurrentUser(Authentication authentication) {
        if (authentication == null || authentication.getName() == null) {
            throw new RuntimeException("Authentication is failed");
        }

        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
