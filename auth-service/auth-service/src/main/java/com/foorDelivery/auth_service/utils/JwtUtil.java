package com.foorDelivery.auth_service.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;


    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }    public String generateToken(String username){
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date((now)))
                .setExpiration(new Date(now+3600000))
                .signWith(SignatureAlgorithm.HS256,getSigningKey())
                .compact();
    }
    public String extractUserName(String token){
        return Jwts.parser()
                .setSigningKey(getSigningKey()).parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateToken(String token, String username){
        String tokenUsername = extractUserName(token);
        return (tokenUsername.equals(username)&&!isTokenExpire(token));
    }

    private boolean isTokenExpire(String token) {
      Date ex = Jwts.parser()
              .setSigningKey(getSigningKey())
              .parseClaimsJws(token)
              .getBody()
              .getExpiration();
      return ex.before(new Date());
    }
}
