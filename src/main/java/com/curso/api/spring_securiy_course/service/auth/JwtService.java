package com.curso.api.spring_securiy_course.service.auth;

import com.curso.api.spring_securiy_course.persistence.entity.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        byte[] key = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(key);
    }
}
