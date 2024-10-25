package com.curso.api.spring_securiy_course.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${security.jwt.expiration-in-minutes}")
    private Long EXPIRATION_IN_MINUTE;

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;

    /**
     * Generates JWT according Claims, main Claims: subj, issuedAt, exp, moreover signatures it
     * @param user
     * @param extraClaims
     * @return String
     */
    public String generateToken(UserDetails user, Map<String, Object> extraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(((EXPIRATION_IN_MINUTE) * 60 * 1000) + issuedAt.getTime());
        // compact consists to generate JWT appropriately
        String jwt = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwt;
    }

    /**
     * We sign our server key
     * @return Key
     */
    private Key generateKey() {
        // We need to decode key because it's base 64 in the application.properties
        byte[] passwordDecoded = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(passwordDecoded);
    }

    /**
     * We extract the token and returning the subj
     * @param jwt
     * @return
     */
    public String extractUsername(String jwt) {
        return extractAllClaims(jwt).getSubject();
    }

    /**
     * We get server's signature and analyzing the token and finally returning body
     * @param jwt
     * @return
     */
    private Claims extractAllClaims(String jwt) {
        return  Jwts.parserBuilder().setSigningKey(generateKey()).build()
                .parseClaimsJws(jwt).getBody();
    }
}
