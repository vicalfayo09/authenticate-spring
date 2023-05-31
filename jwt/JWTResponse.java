/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.auth.jwt;

import com.example.auth.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author alfayo
 */
@Component
public class JWTResponse {
    @Value("${auth.jwt.issuer}")
    private String issuer;
    @Value("${auth.jwt.secret}")
    private String secret;
    @Value("${auth.jwt.audience}")
    private String audience;
    @Value("${auth.jwt.ttl-in-seconds}")
    private long timeToLiveInSeconds;
    
     
    
        private SecretKey secretKey;
    /*public String generateToken(String username) {
        
       Map<String, Object> claims = new HashMap<>();
       return createToken(claims, username);
    }Map<String, Object> claims, String username*/

    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(secretKey)
                .compact(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Claims parseJWT(String jwtString) {

        Jws<Claims> headerClaimsJwt =
                Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(jwtString);

        Claims claims = headerClaimsJwt.getBody();

        return claims;
    }
}
