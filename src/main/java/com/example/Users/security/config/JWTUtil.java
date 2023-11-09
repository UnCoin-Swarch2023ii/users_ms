package com.example.Users.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.example.Users.entity.User;
import com.example.Users.entity.Company;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtil {

    private static String SECRET_KEY = "empanada"; // Replace with your own secret key
    public static String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String tok =  String.valueOf(((User) userDetails).getDocument());
        return createToken(claims,tok);
    }
    public static String generateTokenC(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        String tok =  String.valueOf(((Company) userDetails).getNIT());
        return createToken(claims,tok);
    }
    private static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }
    public static Boolean validToken(String token) {
        if (token == null) {
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            return !expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}

