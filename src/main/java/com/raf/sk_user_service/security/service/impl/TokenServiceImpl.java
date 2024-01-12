package com.raf.sk_user_service.security.service.impl;

import com.raf.sk_user_service.security.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${oauth.jwt.secret}")
    private String base64Secret;

    @Override
    public String generate(Claims claims) {
        byte[] decodedKey = Base64.getDecoder().decode(base64Secret); // dekodira secret
        return Jwts.builder()
                .setClaims(claims)
                .signWith(Keys.hmacShaKeyFor(decodedKey), SignatureAlgorithm.HS512) // HS512 je algoritam za enkripciju, Keys.hmacShaKeyFor(decodedKey) je kljuc za enkripciju
                .compact();
    }

    @Override
    public Claims parseToken(String jwt) {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(base64Secret);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(decodedKey)) // Ključ za verifikaciju
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();


            return claims;
        } catch (Exception e) {
            // Logujte ili obradite izuzetak kako smatrate da je potrebno
            return null;
        }
    }
}
