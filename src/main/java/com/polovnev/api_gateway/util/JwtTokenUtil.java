package com.polovnev.api_gateway.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.lang.String.format;

@Component
public class JwtTokenUtil {

    private final String jwtSecret = "aYEFtKMCn0xCg5caH1nnFuHfdAB0lBOvdonxq80VqOGNnG6QcyagXWOLrUdqJnzexUXYceMhGNFNYsA";

    public String extractUsername(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder().setClaims(claims).setSubject(subject).setExpiration(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + 1000L * 60 * 60 * 24 * 356))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        final boolean isTokenExpired = isTokenExpired(token);
        return userDetails.getUsername().equals(username) && !isTokenExpired;
    }

}